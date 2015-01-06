package com.epam.shopwebapp.util.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.shopwebapp.exception.UtilException;

/**
 * The DateConverter static class is used for manipulation with date/date strings.
 * @author Yuliya_Shydlouskaya
 */
public class DateConverter {
	
	private static final Logger LOGGER = LogManager.getLogger(DateConverter.class);
	
	private DateConverter(){}
	
	/**
	 * Standard format date string for the application.
	 */
	private static final String XML_DATE_FORMAT = "dd-MM-yyyy";
	
	/**
	 * Parses the incoming string to the date object on the base of {@link #XML_DATE_FORMAT}
	 * @param dateString String
	 * @return Date
	 * @throws UtilException 
	 */
	public static Date parse(String dateString) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(XML_DATE_FORMAT);
		format.setLenient(false);		
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			LOGGER.error("Date parsing error.", e.getCause());
		}
		return date;		
	}

}
