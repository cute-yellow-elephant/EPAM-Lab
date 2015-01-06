package com.epam.jsfnews.service;

import java.util.List;

import com.epam.jsfnews.exception.ServiceException;
import com.epam.jsfnews.model.News;

/**
 * The INewsService represents the service layer interface of the application. 
 */
public interface INewsService {
	public List<News> getList() throws ServiceException;
	public int save(News news) throws ServiceException;
	public void remove(List<Integer> idList) throws ServiceException;
	public News fetchById(int id) throws ServiceException;
}
