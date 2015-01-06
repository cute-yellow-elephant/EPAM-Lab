package com.epam.newswebapp.database;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.epam.newswebapp.exception.DAOException;
import com.epam.newswebapp.model.News;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * The NewsDAOTester class tests DAO implementation with the help of Spring framework and DBUnit.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testSpringBeans.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    					  DirtiesContextTestExecutionListener.class,
    					  TransactionalTestExecutionListener.class,
    					  DbUnitTestExecutionListener.class })
@DatabaseSetup("/testNewsData.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NewsDAOTester {
	
	private final static String HIBERNATE_DAO_BEAN = "newsDAOHibernate";
	private final static Integer TEST_NEWS_ID = 200;
	private final static String TEST_NEWS_TITLE = "test title 1";
	private final static String TEST_NEWS_UPDATE_TITLE = "yyyyyyy";
	private final static Integer TEST_DATABASE_SIZE = 6;
	
	@Autowired
	@Qualifier(HIBERNATE_DAO_BEAN)
	private INewsDAO dao;
	
	@Transactional(readOnly = true)
	@Test
	public void getList() throws Exception {
		try{
			List<News> list = dao.getList();
			Assert.assertTrue("The lists' sizes are not equal.", list.size() == TEST_DATABASE_SIZE);
		}catch(DAOException e){
			Assert.fail("Get list failed.");
		}
	}
	
	@Transactional(readOnly = true)
	@Test
	public void fetchById() throws Exception {	
		try{
			News news = dao.fetchById(TEST_NEWS_ID);
			Assert.assertNotNull(String.format("No news with id %d was found.", TEST_NEWS_ID), news);
			Assert.assertEquals("Not equal news items' titles, fetch failed.", TEST_NEWS_TITLE, news.getTitle());
		}catch(DAOException e){
			Assert.fail(String.format("The fetch of the news with id %d failed.", TEST_NEWS_ID));
		}
	}
	
	@Test
	public void add() throws Exception {	
		News news = new News(TEST_NEWS_UPDATE_TITLE, new Date(),
				TEST_NEWS_UPDATE_TITLE, TEST_NEWS_UPDATE_TITLE);
		News addedNews = null;
		try {
			Integer id = dao.insert(news);
			Assert.assertNotNull("The id hasn't been returned by the insert function.",id);
			addedNews = dao.fetchById(id);
			Assert.assertNotNull("The added news item hasn't been returned.", addedNews);
			Assert.assertEquals("Not equal news items' titles, insert failed.", news.getTitle(), addedNews.getTitle());
		}catch(DAOException e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void remove() throws Exception {		
		try{
			dao.remove(Arrays.asList(TEST_NEWS_ID));
			News new_news = dao.fetchById(TEST_NEWS_ID);
			Assert.assertNull("The news item is returned, removal failed.", new_news);
		}catch(DAOException e){
			Assert.fail(String.format("The removal of the news with id %d failed.", TEST_NEWS_ID));
		}
	}
	
	@Test
	public void update() throws Exception {	
		try{
			News news = dao.fetchById(TEST_NEWS_ID), updatedNews = null;
			Assert.assertNotNull("The news item for update hasn't been returned.", news);
			news.setTitle(TEST_NEWS_UPDATE_TITLE);
			dao.update(news);
			updatedNews = dao.fetchById(news.getId());
			Assert.assertNotNull("The updated news item hasn't been returned.", updatedNews);
			Assert.assertEquals("Not equal news items' titles, update failed.", news.getTitle(), updatedNews.getTitle());
		}catch(DAOException e){
			Assert.fail(e.getMessage());
		}
	}

	public INewsDAO getDao() {
		return dao;
	}

	public void setDao(INewsDAO dao) {
		this.dao = dao;
	}

	

}
