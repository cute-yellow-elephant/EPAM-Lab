package com.epam.jsfnews.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jsfnews.exception.DAOException;
import com.epam.jsfnews.model.News;
import com.epam.jsfnews.util.Constants;

/**
 * The NewDAOJpa class implements the {@link INewsDAO} interface on the base of JPA with EclipseLink
 * as a vendor and a JPA Transaction manager as a transaction provider. 
 */
@Repository(Constants.DAO_BEAN_JPA)
@Transactional
public class NewsDAOJpa implements INewsDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int insert(News news) throws DAOException {
		entityManager.persist(news);
		return news.getId();
	}
	
	@Override
	public int update(News news) throws DAOException {
		news = entityManager.merge(news);
		return news.getId();
	}
	
	@Transactional(readOnly = true)
	@Override
	public News fetchById(int id) throws DAOException {
		return entityManager.find(News.class, id);
	}
	
	/**
	 * Removes the news item by the named query, defined using the annotations in the {@link News} class.
	 */
	@Override
	public void remove(List<Integer> idList) throws DAOException {
		Query query = entityManager.createNamedQuery(Constants.NAMED_QUERY_DELETE_NEWSLIST_BY_IDS);
		query.setParameter(Constants.DELETE_NEWSLIST_BY_IDS_PARAM, idList);
		query.executeUpdate();
	}
	
	/**
	 * Gets the list of news using JPA Criteria API.
	 */
	@Transactional(readOnly = true)
	@Override
	public List<News> getList() throws DAOException {
		CriteriaBuilder criteriaBuilder = entityManager.getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
		Root<News> root = criteriaQuery.from(News.class);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Constants.SELECT_ORDER_BY_WHAT_FIELD_PARAM)));
		TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	

}
