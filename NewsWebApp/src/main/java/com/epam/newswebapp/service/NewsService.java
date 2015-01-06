package com.epam.newswebapp.service;

import java.util.List;

import org.springframework.transaction.TransactionSystemException;

import com.epam.newswebapp.database.INewsDAO;
import com.epam.newswebapp.exception.DAOException;
import com.epam.newswebapp.exception.ServiceException;
import com.epam.newswebapp.model.News;

/**
 * The NewsService class implements the {@link INewsService}. At the current moment it is almost just a duplicate
 * of the dao classes and their methods.
 */
public class NewsService implements INewsService{
	
	/**
	 *  The object, implementing the {@link INewsDAO}, container. Is injected by Spring using spring-module.xml. 
	 */
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
		} catch (DAOException|TransactionSystemException e) {
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
		} catch (DAOException|TransactionSystemException e) {
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
		} catch (DAOException|TransactionSystemException e) {
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
		} catch (DAOException|TransactionSystemException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
