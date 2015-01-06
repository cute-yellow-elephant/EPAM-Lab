package com.epam.shopwebapp.util.xml.parser.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
public class ShopSTAXParser implements IXMLParser<Category>{
	
	/** A name attribute index for tags category and subcategory. */
	private static final int NAME_ATTRIBUTE_INDEX = 0;

	@Override
	public List<Category> parse(File xmlFile) throws UtilException {
		
		try{
			XMLInputFactory factory = XMLInputFactory.newInstance();
	        XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(xmlFile));
	        int event = reader.getEventType();
	        
			List<Category> categories = new ArrayList<Category>();
			List<Subcategory> subcategories = null;
			List<Product> products = null;
			Category category = null;
			Subcategory subcategory = null;
			Product product = null;
			String eventName = null;
	        String content = null;
			
	        while (reader.hasNext()) {
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                    	eventName = reader.getLocalName();
                        switch (eventName){
	                        case CATEGORY:
		                    	category = new Category();
		                    	category.setName(reader.getAttributeValue(NAME_ATTRIBUTE_INDEX));
		                    	break;
		                    case SUBCATEGORIES:
		                    	subcategories = new ArrayList<Subcategory>();
		                    	break;
		                    case SUBCATEGORY:
		                    	subcategory = new Subcategory();
		                    	subcategory.setName(reader.getAttributeValue(NAME_ATTRIBUTE_INDEX));
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
	                     break;
                    case XMLStreamConstants.CHARACTERS:
                        if (reader.isWhiteSpace()){ 
                        	break; 
                        }
                        content = reader.getText();
    	            	switch (eventName) {
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
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                    	eventName = reader.getLocalName();	                         
                        switch (eventName){
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
                        break;
                    default:
                    	break;
                }
                event = reader.next();
            }        
	        reader.close();
	        return categories;	        
		} catch (XMLStreamException | FileNotFoundException e) {
           throw new UtilException(e.getCause());
        }
	}

	@Override
	public String toString() {
		return "ShopSTAXParser []";
	}

}
