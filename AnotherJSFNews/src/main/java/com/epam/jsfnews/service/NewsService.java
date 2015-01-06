package com.epam.jsfnews.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epam.jsfnews.database.INewsDAO;
import com.epam.jsfnews.exception.DAOException;
import com.epam.jsfnews.exception.ServiceException;
import com.epam.jsfnews.model.News;
import com.epam.jsfnews.util.Constants;

/**
 * The NewsService class implements the {@link INewsService}. At the current moment it is almost just a duplicate
 * of the dao classes and their methods.
 */
@Service
public class NewsService implements INewsService, Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 *  The object, implementing the {@link INewsDAO}, container. Is injected by Spring using spring-module.xml. 
	 */
	@Autowired
	@Qualifier(Constants.DAO_BEAN_JDBC)
	private INewsDAO newsDao;

	public INewsDAO getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(INewsDAO newsDao) { 
		this.newsDao = newsDao;
	}

	@Override
	public List<News> getList() throws ServiceException {
		try {
			return getNewsDao().getList();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int save(News news) throws ServiceException {
		try {
			if(news.getId() != null && news.getId() != 0){
				return getNewsDao().update(news);
			} 
			else{
				return getNewsDao().insert(news);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void remove(List<Integer> idList) throws ServiceException {
		try {
			if(idList == null || idList.size() == 0){
				throw new ServiceException("No items for removal.");
			}
			getNewsDao().remove(idList);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public News fetchById(int id) throws ServiceException {
		try {
			News news = getNewsDao().fetchById(id);
			if(news != null){ return news;}
			else{
				throw new ServiceException(String.format("No news with id %s was found.", id));
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
