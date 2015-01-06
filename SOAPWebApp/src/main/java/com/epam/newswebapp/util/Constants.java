package com.epam.newswebapp.util;

public class Constants {
		
	private Constants() {}
	
	//com.epam.newswebapp.presentation.action package constants
	
	public final static String NEWSLIST_PAGE = "newsList";
	public final static String NEWSVIEW_PAGE = "newsView";
	public final static String SUCCESS_PAGE = "success";
	public final static String FAILURE_PAGE = "failure";
	public final static String LANGUAGE_PARAM_ID = "lang";
	public final static String PAGE_PARAM_ID = "page";
	public final static String ID_PARAM = "id";
	public final static String DATE_INPUT_PARAM_ID = "newsMessage.date";	
	public final static String CANCEL_PAGE_PARAM = "cancel";	
	
	//com.epam.newswebapp.database package constants
	
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
