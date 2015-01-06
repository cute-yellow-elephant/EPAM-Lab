package com.epam.newswebapp.service;

import java.util.List;

import com.epam.newswebapp.model.News;
import com.epam.newswebapp.exception.ServiceException;

/**
 * The INewsService represents the service layer interface of the application. 
 */
public interface INewsService {
	public List<News> getList() throws ServiceException;
	public int save(News news) throws ServiceException;
	public void remove(List<Integer> idList) throws ServiceException;
	public News fetchById(int id) throws ServiceException;
}
