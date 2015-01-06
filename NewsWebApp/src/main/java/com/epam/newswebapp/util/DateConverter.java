package com.epam.newswebapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The DateConverter class implements {@link Converter} and is used to validate the input date string and 
 * make conversions between Date date and String date in both ends. 
 *
 */
public class DateConverter implements Converter {
	
	private final static Logger LOGGER = LogManager.getLogger(DateConverter.class);	
	private final static String CONVERTER_GLOBAL_DATE_FORMAT = "MM/dd/yyyy";
	
	/**
	 * Converts Date to String.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class arg0, Object arg1) {
			Date date = null;
			try {
				date = new SimpleDateFormat(CONVERTER_GLOBAL_DATE_FORMAT).parse((String) arg1);
			} catch (ParseException e) {
				LOGGER.error("Date conversion failed.");
			}
			return date;		
	}	

}
