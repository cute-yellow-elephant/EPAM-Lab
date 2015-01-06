package com.epam.newswebapp.database;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import com.epam.newswebapp.exception.DAOException;
import com.epam.newswebapp.model.News;
import com.epam.newswebapp.util.Constants;

/**
 * The <b>NewsDAOHibernate</b> class is a Hibernate-based implementation of the {@link INewsDAO} interface. It 
 * uses session-per-request unit of work strategy and lazy loading, so there is no need to open/close sessions, 
 * it's done automatically.
 */
public class NewsDAOHibernate implements INewsDAO{
	
	/**
	 * Private field, that contains sessionFactory object, properties of which are defined in the spring-beans.xml. 
	 */
	private SessionFactory sessionFactory;

	/**
	 * Gets the list of news objects with the help of the Criteria API.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> getList() throws DAOException {
		Session session = null;
		Transaction transaction = null;
		try{
			session = sessionFactory.getCurrentSession(); 
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(News.class);
			criteria.addOrder(Order.desc(Constants.SELECT_ORDER_BY_WHAT_FIELD_PARAM));			
			List<News> news = (List<News>) criteria.list();
			transaction.commit();
			return news;
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			throw new DAOException(e.getMessage(),e.getCause());
		}
	}

	/**
	 * Removes the given news object with the help of the named query, defined in the news.hbm.xml.
	 */
	@Override
	public void remove(List<Integer> idList) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		try{
			session = sessionFactory.getCurrentSession(); 
			transaction = session.beginTransaction();
			Query query = session.getNamedQuery(Constants.NAMED_QUERY_DELETE_NEWSLIST_BY_IDS);
			query.setParameterList(Constants.DELETE_NEWSLIST_BY_IDS_PARAM, idList);
			query.executeUpdate();
			session.getTransaction().commit();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			throw new DAOException(e.getMessage(),e.getCause());
		}
	}

	/**
	 * Fetches the news item by id, using session.get(...) in order to get the real object from the database,
	 * not a proxy one.
	 */
	@Override
	public News fetchById(int id) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		try{
			session = sessionFactory.getCurrentSession(); 
			transaction = session.beginTransaction();
			News news = (News) session.get(News.class, id);
			session.getTransaction().commit();
			return news;
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			throw new DAOException(e.getMessage(),e.getCause());
		}
	}

	/**
	 * Inserts the news object by session.persist(...) and make the instance, passed to the method, managed.
	 */
	@Override
	public int insert(News news) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		try{
			session = sessionFactory.getCurrentSession(); 
			transaction = session.beginTransaction();
			session.persist(news);
			session.getTransaction().commit();
			return news.getId();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			throw new DAOException(e.getMessage(),e.getCause());
		}	
	}

	/**
	 * Updates the news item by session.merge(...), because we made all changes earlier and there is no need
	 * to make changes in the instance passed to the method.
	 */
	@Override
	public int update(News news) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		try{
			session = sessionFactory.getCurrentSession(); 
			transaction = session.beginTransaction();
			news = (News) session.merge(news);
			session.getTransaction().commit();
			return news.getId();
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			throw new DAOException(e.getMessage(),e.getCause());
		}		
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
