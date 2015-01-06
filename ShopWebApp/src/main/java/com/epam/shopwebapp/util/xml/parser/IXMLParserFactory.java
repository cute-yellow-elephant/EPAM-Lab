package com.epam.shopwebapp.util.xml.parser;

import com.epam.shopwebapp.exception.UtilException;

/**
 * The IXMLParserFactory interface provides base methods for getting parsers.
 * @author Yuliya_Shydlouskaya
 *
 * @param <T> Parser Type
 */
public interface IXMLParserFactory<T>{

	public IXMLParser<T> getParser(String parserType) throws UtilException;

}
