package com.epam.jsfnews.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Application Lifecycle Listener implementation class NewsServletContextListener
 * Serves for registering the bridge between log4j and slf4j for the application needs.
 *
 */
@WebListener
public class NewsServletContextListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * Registers the slf4j-log4j bridge.
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 

    }
	
}
