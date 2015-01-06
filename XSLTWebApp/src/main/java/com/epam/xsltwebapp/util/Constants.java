package com.epam.xsltwebapp.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * The Constants class is a helper class with the application constants.
 * @author Yuliya_Shydlouskaya
 */
public class Constants {
	
	private Constants(){}
	
	public static final Resource XML_FILE_RESOURCE = new ClassPathResource("categories.xml");
	public static final Resource XSL_CATEGORIES_PAGE_RESOURCE = new ClassPathResource("/xsl/categories_page.xsl");
	public static final Resource XSL_SUBCATEGORIES_PAGE_RESOURCE = new ClassPathResource("/xsl/subcategories_page.xsl");
	public static final Resource XSL_PRODUCTS_PAGE_RESOURCE = new ClassPathResource("/xsl/products_page.xsl");
	public static final Resource XSL_ADD_PRODUCT_PAGE_RESOURCE = new ClassPathResource("/xsl/add_product_page.xsl");
	public static final Resource XSL_SAVE_PRODUCT_PAGE_RESOURCE = new ClassPathResource("/xsl/save_product_page.xsl");
	
	public static final String CATEGORY_PARAM = "category";
	public static final String SUBCATEGORY_PARAM = "subcategory";
	public static final String NAME_PARAM = "name";
	public static final String PROVIDER_PARAM = "provider";
	public static final String MODEL_PARAM = "model";
	public static final String DATE_OF_ISSUE_PARAM = "date-of-issue";
	public static final String COLOR_PARAM = "color";
	public static final String PRICE_PARAM = "price";
	public static final String ERROR_PARAM = "error";
	
	/** Waiting for lock freeing time in seconds. */
	public static final int LOCK_WAITING_TIME = 1;
	
	public static final String VIEW_ERROR = "error";
	
	public static final String URL_ERROR = "/error";
	public static final String URL_CATEGORIES_PAGE = "/categories";
	public static final String URL_SUBCATEGORIES_PAGE = "/categories[{category}]/subcategories";
	public static final String URL_PRODUCTS_PAGE = "/categories[{category}]/subcategories[{subcategory}]/products";
	public static final String URL_ADD_PRODUCT_PAGE = "/categories[{category}]/subcategories[{subcategory}]/products/add";
	public static final String URL_SAVE_PRODUCT_PAGE = "/categories[{category}]/subcategories[{subcategory}]/products/save";
	
	public static final String ERROR_LOCK_WAITING = "Waiting for lock freeing error occurred.";

}
