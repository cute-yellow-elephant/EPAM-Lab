package com.epam.shopwebapp.util.xml.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.epam.shopwebapp.exception.UtilException;
import com.epam.shopwebapp.model.Category;
import com.epam.shopwebapp.util.xml.parser.impl.ShopXMLParserFactory;

public class ParsersTester {
	
	private static final Logger LOGGER = LogManager.getLogger(ParsersTester.class);
	private static IXMLParserFactory<Category> parserFactory;
	private static File xmlFile;
	
	private static final String FIRST_CATEGORY_NAME = "food";
	private static final int CATEGORIES_SIZE = 2;
	
	@BeforeClass 
	public static void loadSpring() {
		new DOMConfigurator().doConfigure("src/main/resources/log4j.xml", LogManager.getLoggerRepository());
		xmlFile = new File("src/main/resources/categories.xml");
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("testSpringBeans.xml");
		context.refresh();		
		parserFactory = (ShopXMLParserFactory) context.getBean("xmlParserFactory");
		context.close();
	}
	
	@Test
	public void parseDOM() {		
		try {			
			IXMLParser<Category> parser = parserFactory.getParser("DOM");
			List<Category> categories = parser.parse(xmlFile);
			LOGGER.info("DOM: " + categories);
			Assert.assertNotNull("The categories list is null", categories);
			Assert.assertEquals("The first category name doesn't match the expected one.", 
						FIRST_CATEGORY_NAME, categories.get(0).getName());
			Assert.assertEquals("The parsed category size doesn't match the expected.", CATEGORIES_SIZE, categories.size());
		} catch (UtilException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void parseSAX() {		
		try {			
			IXMLParser<Category> parser = parserFactory.getParser("SAX");
			List<Category> categories = parser.parse(xmlFile);
			LOGGER.info("SAX: " + categories);
			Assert.assertNotNull("The categories list is null", categories);
			Assert.assertEquals("The first category name doesn't match the expected one.", 
					FIRST_CATEGORY_NAME, categories.get(0).getName());
			Assert.assertEquals("The parsed category size doesn't match the expected.", CATEGORIES_SIZE, categories.size());
		} catch (UtilException e) {
			
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void parseSTAX() {		
		try {			
			IXMLParser<Category> parser = parserFactory.getParser("STAX");
			List<Category> categories = parser.parse(xmlFile);
			LOGGER.info("STAX: " + categories);
			Assert.assertNotNull("The categories list is null", categories);
			Assert.assertEquals("The first category name doesn't match the expected one.", 
					FIRST_CATEGORY_NAME, categories.get(0).getName());
			Assert.assertEquals("The parsed category size doesn't match the expected.", CATEGORIES_SIZE, categories.size());
		} catch (UtilException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private void printFileContents(String filePath) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = br.readLine()) != null) {
				LOGGER.info(line);
			}
			Assert.assertNotNull(br);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
