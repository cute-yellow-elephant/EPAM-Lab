package com.epam.newswebapp.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.newswebapp.util.DateConverter;

/**
 * Application Lifecycle Listener implementation class WebAppContextListener
 * Registers the date converter on the context initializing event.
 */
public class WebAppContextListener implements ServletContextListener {
	
	private final static Logger LOGGER = LogManager.getLogger(WebAppContextListener.class);	

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {   	
	    ConvertUtils.register(new DateConverter(), java.util.Date.class);
	    LOGGER.info("Custom date converter has been registered.");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
	
}
