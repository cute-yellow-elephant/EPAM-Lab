package com.epam.jsfnews.util;

/**
 * The Constants class holds the essential for the app constants.
 * @author Yuliya_Shydlouskaya
 *
 */
public class Constants {
		
	private Constants() {}
	
	//dao bean names
	public final static String DAO_BEAN_JDBC = "jdbcDAOBean";
	public final static String DAO_BEAN_HIBERNATE = "hibernateDAOBean";
	public final static String DAO_BEAN_JPA = "jpaDAOBean";
	
	//com.epam.jsfnews.presentation.action package constants
	
	public final static String SCOPE_SESSION = "session";
	public final static String NEWS_SESSION_BEAN_NAME = "newsManager";
	
	public final static String PAGE_NEWS_LIST = "newslist";
	public final static String PAGE_VIEW_NEWS = "view_news";
	public final static String PAGE_EDIT_NEWS = "edit_news";
	public final static String PAGE_ERROR = "error";	
	
	public final static String FLAG_MAKE_REDIRECT = "?faces-redirect=true";
	public final static String FLAG_SAVE_VIEW_PARAMS = "&amp;includeViewParams=true";
	
	//com.epam.jsfnews.database package constants
	
	public final static String NEWSLIST_TABLE_NAME = "NEWSLIST";
	public final static String NEWSLIST_ID_COLUMN_NAME = "NEWS_ID";
	public final static String NEWSLIST_TITLE_COLUMN_NAME = "TITLE";
	public final static String NEWSLIST_BRIEF_COLUMN_NAME = "BRIEF";
	public final static String NEWSLIST_CONTENT_COLUMN_NAME = "CONTENT";
	public final static String NEWSLIST_DATE_COLUMN_NAME = "NEWS_DATE";
	public final static String NAMED_QUERY_DELETE_NEWSLIST_BY_IDS = "deleteNewslistByIds";
	public final static String DELETE_NEWSLIST_BY_IDS_PARAM = "idList";
	public final static String SELECT_ORDER_BY_WHAT_FIELD_PARAM = "date";
	

}
