package com.epam.jsfnews.database;

import java.util.List;

import com.epam.jsfnews.exception.DAOException;
import com.epam.jsfnews.model.News;

/**
 * The INewsDAO interface is used for indicating CRUD operations with the database.
 */
public interface INewsDAO {
	public List<News> getList() throws DAOException;
	public int insert(News news) throws DAOException;
	public int update(News news) throws DAOException;
	public void remove(List<Integer> idList) throws DAOException;
	public News fetchById(int id) throws DAOException;
}
