package com.epam.jsfnews.database;

import static com.epam.jsfnews.util.Constants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jsfnews.exception.DAOException;
import com.epam.jsfnews.model.News;
import com.epam.jsfnews.util.Constants;


/**
 * The NewsDAOJdbc implements {@link INewsDAO} on the base of JDBC (Oracle database).
 */
@Repository(Constants.DAO_BEAN_JDBC)
public class NewsDAOJdbc implements INewsDAO{
	
	/** The dataSource container. Is injected by Spring using spring-module.xml. */
	@Autowired
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/** Select all news from the database in descending order constant string*/
    public static final String SQL_SELECT_ALL = "SELECT * FROM NEWSLIST ORDER BY NEWS_DATE DESC";
    /** Select all news from the database in descending order constant string*/
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM NEWSLIST WHERE NEWSLIST.NEWS_ID = ?";
    /** Delete the news from the database by id constant string*/
    public static final String SQL_DELETE = "DELETE FROM NEWSLIST WHERE NEWSLIST.NEWS_ID = ?";
    /** Insert the news to the database constant string*/
    public static final String SQL_INSERT = "INSERT INTO NEWSLIST(TITLE, NEWS_DATE, BRIEF, CONTENT) "
    									  + "values(?,?,?,?)";
    /** Update the news in the database constant string*/
    public static final String SQL_UPDATE = "UPDATE NEWSLIST "
    									  + "SET TITLE = ?, NEWS_DATE = ?, BRIEF = ?, CONTENT = ? "
    									  + "WHERE NEWSLIST.NEWS_ID = ?";
    
    /**
     * Parses the result set from the database to the new News object.
     * The date field is set in the us-locale format.
     * @param rs
     * @param list
     * @return List<News>
     * @throws SQLException
     */
    protected List<News> parseResultSet(ResultSet rs, List<News> list) throws SQLException {
        News news;
        while (rs.next()) {
            news = new News();
            news.setId(rs.getInt(NEWSLIST_ID_COLUMN_NAME));
            news.setTitle(rs.getString(NEWSLIST_TITLE_COLUMN_NAME));
            news.setBrief(rs.getString(NEWSLIST_BRIEF_COLUMN_NAME));
            news.setContent(rs.getString(NEWSLIST_CONTENT_COLUMN_NAME));
            news.setDate(new Date(rs.getDate(NEWSLIST_DATE_COLUMN_NAME).getTime()));
            list.add(news);
        }
        return list;
    }

    /**
     * Prepares the news object for inserting or saving the object to the database.
     * @param statement
     * @param entity
     * @return int counter for the next value input to the prepared statement
     * @throws SQLException
     */
    protected int prepareStatement(PreparedStatement statement, News entity) throws SQLException {
    	int counter = 1;
        statement.setString(counter++, entity.getTitle());
    	statement.setDate(counter++, new java.sql.Date(entity.getDate().getTime()));
        statement.setString(counter++, entity.getBrief());
        statement.setString(counter++, entity.getContent());
        return counter;
    }
    
	@Override
	public int insert(News news) throws DAOException {
		try(Connection connection = dataSource.getConnection()){
        	try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"NEWS_ID"})) {
    			prepareStatement(statement, news);
	            statement.executeUpdate();
	            try(ResultSet rs = statement.getGeneratedKeys()){
		            if(rs == null || !rs.next()){
		                throw new DAOException(String.format("The news {1} hasn't been created.", news));
		            }
		            return rs.getInt(1);
	            }	                			
    		}
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }	
	}

	@Override
	public int update(News news) throws DAOException {
		try(Connection connection = dataSource.getConnection()){
        	try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE, new String[]{"NEWS_ID"})) {
    			int count = prepareStatement(statement, news);
                statement.setInt(count, news.getId());
                count = statement.executeUpdate();
                return news.getId();   			
            } 
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }	
	}
    
	@Override
	public List<News> getList() throws DAOException {
        try(Connection connection = dataSource.getConnection()){
        	try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
                try(ResultSet rs = statement.executeQuery()){
                    return parseResultSet(rs, new ArrayList<News>());
                }
            } 
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
	}

	@Override
	public void remove(List<Integer> idList) throws DAOException {
		try(Connection connection = dataSource.getConnection()){
        	try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
        		for(Integer id: idList){
        			statement.setInt(1, id);
        			statement.addBatch();
        		}
        		int[] results = statement.executeBatch();
        		List<Integer> mistakeIdList = new ArrayList<Integer>();
        		for(int i = 0; i < results.length; i++ ){
        			if(results[i] == Statement.EXECUTE_FAILED){ 
        				mistakeIdList.add(idList.get(i));
        			}
        		}  
        		if(mistakeIdList.size() > 0){
        			throw new DAOException(String.format("Removal operation for %s failed.", mistakeIdList));
        		}
            } 
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }	
	}

	@Override
	public News fetchById(int id) throws DAOException {		
		try(Connection connection = dataSource.getConnection()){
        	try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
        		statement.setInt(1, id);  		
                try(ResultSet rs = statement.executeQuery()){
                	List<News> list = parseResultSet(rs, new ArrayList<News>());
                	return (list.size() != 0) ? list.get(0) : null;
                }               
            } 
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }	
	}

}
