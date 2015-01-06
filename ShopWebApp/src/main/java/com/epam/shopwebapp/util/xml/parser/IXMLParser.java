package com.epam.shopwebapp.util.xml.parser;

import java.io.File;
import java.util.List;

import com.epam.shopwebapp.exception.UtilException;

/**
 * The IXMLParser interface provides general methods for parsing XML documents.
 * @author Yuliya_Shydlouskaya
 * 
 * @param <T> target parser class 
 */
public interface IXMLParser<T>{
	
	public List<T> parse(File xmlFile) throws UtilException;

}
