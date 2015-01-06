package com.epam.shopwebapp.util.xml.parser.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.shopwebapp.exception.UtilException;
import com.epam.shopwebapp.model.Category;
import com.epam.shopwebapp.model.Product;
import com.epam.shopwebapp.model.Subcategory;
import com.epam.shopwebapp.util.converter.DateConverter;
import com.epam.shopwebapp.util.xml.parser.IXMLParser;
import static com.epam.shopwebapp.util.constants.XMLTagsConstants.*;
/**
 * The ShopSAXParser class implements {@link IXMLParser} interface for {@link Category} class with
 * the facilities, provided by SAX-oriented parsing. In this application it's a singleton because of 
 * Spring settings.
 * @author Yuliya_Shydlouskaya
 *
 */
public class ShopSAXParser implements IXMLParser<Category>{

	@Override
	public List<Category> parse(File xmlFile) throws UtilException {		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ShopHandler handler = this.new ShopHandler();
            saxParser.parse(xmlFile, handler);
            return handler.getCategories();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new UtilException(String.format("SAX parsing has been finished with mistakes : %s", e));
        }
	}
	
	/**
	 * The ShopHandler class is a private class for SAX parsing handling.
	 * @author Yuliya_Shydlouskaya
	 *
	 */
	private class ShopHandler extends DefaultHandler {
		
		private List<Category> categories = new ArrayList<Category>();
		private List<Subcategory> subcategories;
		private List<Product> products;
		private Category category;
		private Subcategory subcategory;
		private Product product;
		private String qName;
		
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
			this.qName = qName;
            switch (qName) {
                case CATEGORY:
                	category = new Category();
                	category.setName(attributes.getValue(NAME));
                	break;
                case SUBCATEGORIES:
                	subcategories = new ArrayList<Subcategory>();
                	break;
                case SUBCATEGORY:
                	subcategory = new Subcategory();
                	subcategory.setName(attributes.getValue(NAME));
                	break;
                case PRODUCTS: 
                	products = new ArrayList<Product>();
                	break;
                case PRODUCT:
                	product = new Product();
                	break;                   
                default:
                    break;
            }
	           
        }
		
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case CATEGORY:
                	categories.add(category);
                	category = null;
                	break;
                case SUBCATEGORIES:
                	category.setSubcategories(subcategories);
                	subcategories = null;
                	break;
                case SUBCATEGORY:
                	subcategories.add(subcategory);
                	subcategory = null;
                	break;
                case PRODUCTS:
                	subcategory.setProducts(products);
                	products = null;
                	break;
                case PRODUCT:
                	products.add(product);
                	product = null;
                	break;
                default:
                	break;
            }	               
        }

        public void characters(char[] buffer, int start, int length) {
        	String content = new String(buffer, start, length);
        	switch (qName) {
				case NAME:
					product.setName(content);
					break;
				case PROVIDER:
					product.setProvider(content);
					break;
				case MODEL:
					product.setModel(content);
					break;
				case DATE_OF_ISSUE:
					product.setDateOfIssue(DateConverter.parse(content));							
					break;
				case COLOR:
					product.setColor(content);
					break;
				case NOT_IN_STOCK:
					product.setInStock(false);
					break;
				case PRICE:
					product.setInStock(true);
					product.setPrice(Double.valueOf(content));
					break;
				default:
					break;
			}
        }

		public List<Category> getCategories() {
			return categories;
		}	
	}

	@Override
	public String toString() {
		return "ShopSTAXParser []";
	}	

}
