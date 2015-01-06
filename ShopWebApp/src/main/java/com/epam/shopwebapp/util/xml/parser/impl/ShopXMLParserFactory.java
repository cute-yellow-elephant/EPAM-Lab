package com.epam.shopwebapp.util.xml.parser.impl;

import java.util.HashMap;
import java.util.Map;

import com.epam.shopwebapp.exception.UtilException;
import com.epam.shopwebapp.model.Category;
import com.epam.shopwebapp.util.xml.parser.IXMLParser;
import com.epam.shopwebapp.util.xml.parser.IXMLParserFactory;

/**
 * The ShopXMLParserFactory class implements {@link IXMLParser} for {@link Category}. 
 * @author Yuliya_Shydlouskaya
 *
 */
public class ShopXMLParserFactory implements IXMLParserFactory<Category>{
	
	/**
	 * The map of parsers, available by the parser names, defined in springBeans.xml. The map
	 * in this application is injected into the constructor by Spring.
	 */
	private Map<String,IXMLParser<Category>> parsers = new HashMap<>();
	
	/**
	 * Constructs the factory, in this application as singleton because of Spring settings.
	 * @param parsers Map of parser objects.
	 */
	public ShopXMLParserFactory(Map<String,IXMLParser<Category>> parsers) {
		this.parsers = parsers;
	}
	
	@Override
	public IXMLParser<Category> getParser(String parserType) throws UtilException {
		IXMLParser<Category> parser = parsers.get(parserType);
		if(parser == null){
            throw new UtilException(String.format("$s is not found in ShopXMLParserFactory.parsers.", parserType));
        }
        return parser;		
	}

	@Override
	public String toString() {
		return "ShopXMLParserFactory:" + parsers.toString();
	}

}
