package com.epam.shopwebapp.util.xml.parser.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.shopwebapp.exception.UtilException;
import com.epam.shopwebapp.model.Category;
import com.epam.shopwebapp.model.Product;
import com.epam.shopwebapp.model.Subcategory;
import com.epam.shopwebapp.util.converter.DateConverter;
import com.epam.shopwebapp.util.xml.parser.IXMLParser;
import static com.epam.shopwebapp.util.constants.XMLTagsConstants.*;

/**
 * The ShopDOMParser class implements {@link IXMLParser} interface for {@link Category} class with
 * the facilities, provided by DOM-oriented parsing. In this application it's a singleton because of 
 * Spring settings.
 * @author Yuliya_Shydlouskaya
 *
 */
public class ShopDOMParser implements IXMLParser<Category> {

	@Override
	public List<Category> parse(File xmlFile) throws UtilException {

		if(!xmlFile.exists() || !xmlFile.isFile()){
			throw new UtilException(String.format("%s doesn't exist or is not a file.", xmlFile.getAbsolutePath()));
		}
	        	
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            
            Category category;
            Subcategory subcategory;
            Product product;
            Element element;
            List<Category> categories = new ArrayList<Category>();
                       
            NodeList categoryNodes = document.getDocumentElement().getElementsByTagName(CATEGORY);	
            if(categoryNodes != null){
	            for (int i = 0; i < categoryNodes.getLength(); i++) {
	                element = (Element) categoryNodes.item(i);	
	                category = parseCategoryElement(element);      
	                element = (Element) element.getElementsByTagName(SUBCATEGORIES).item(0);
	                    
	                NodeList subcategoryNodes = element.getElementsByTagName(SUBCATEGORY);	
	                if(subcategoryNodes != null){
		            	for (int j = 0; j < subcategoryNodes.getLength(); j++) {
		            		element = (Element) subcategoryNodes.item(j);	
	    	                subcategory = parseSubcategoryElement(element);         
	    	                element = (Element) element.getElementsByTagName(PRODUCTS).item(0);
	    	                
	    	                NodeList productNodes = element.getElementsByTagName(PRODUCT);
	    	                if(productNodes != null){
		    	                for (int k = 0; k < productNodes.getLength(); k++) {
		                    		element = (Element) productNodes.item(k);
			                        product = parseProductElement(element);
			                        subcategory.getProducts().add(product);                		
		    	                }  
	    	                }
	    	                category.getSubcategories().add(subcategory);
		            	}    	                    
	                }
	            	categories.add(category);               
	             }
            }
            return categories;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new UtilException("Problems with DOMParser.", e.getCause());
        }	        
	}
	
	/**
	 * Parses the contents of the category element to the new {@link Category} object.
	 */
	private Category parseCategoryElement(Element categoryElement) {
		Category category = new Category();
		String content = categoryElement.getAttribute(NAME);
		category.setName(content);
		return category;
	}
	
	/**
	 * Parses the contents of the subcategory element to the new {@link Subcategory} object.
	 */
	private Subcategory parseSubcategoryElement(Element subcategoryElement) {           	                            	
    	Subcategory subcategory = new Subcategory();
		String content = subcategoryElement.getAttribute(NAME);
		subcategory.setName(content);
		return subcategory;
	}
	
	/**
	 * Parses the contents of the product element to the new {@link Product} object.
	 */
	private Product parseProductElement(Element element){
		Product product = new Product();
		String content = element.getElementsByTagName(NAME).item(0).getTextContent();
		product.setName(content);
		content = element.getElementsByTagName(PROVIDER).item(0).getTextContent();
    	product.setProvider(content);
    	content = element.getElementsByTagName(MODEL).item(0).getTextContent();
    	product.setModel(content);
    	content = element.getElementsByTagName(COLOR).item(0).getTextContent();
    	product.setColor(content);
    	content = element.getElementsByTagName(DATE_OF_ISSUE).item(0).getTextContent();
		product.setDateOfIssue(DateConverter.parse(content));
    	product.setInStock(element.getElementsByTagName(NOT_IN_STOCK) == null);
    	if(product.isInStock()){
    		content = element.getElementsByTagName(PRICE).item(0).getTextContent();
    		product.setPrice(Double.valueOf(content));	
    	}
		return product;
	}

	@Override
	public String toString() {
		return "ShopDOMParser []";
	}
	
	

}
