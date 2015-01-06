package com.epam.jsfnews.util;

import java.util.Locale;

/**
 * The SupportedLocales enum is the supported by the application locals' holder.
 * @author Yuliya_Shydlouskaya
 *
 */
public enum SupportedLocales {
	ru_RU(new Locale("ru", "RU")), 
	en_US(Locale.US);
	
	/**
	 * Constructs the SupportedLocales object.
	 * @param locale
	 */
	private SupportedLocales(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Returns the locale object.
	 * @return Locale
	 */
	public Locale getLocale() {
		return locale;
	}

	private Locale locale;
	
	/**
	 * Figures out, if the string language parameter is supported by the Locals enumeration.
	 * @param language String
	 * @return Locale locale
	 */
	public static Locale identifyLocale(String language) {
		try{
			return SupportedLocales.valueOf(language).getLocale();	
		}catch(IllegalArgumentException e){
			return SupportedLocales.en_US.getLocale();
		}	
	}
}
