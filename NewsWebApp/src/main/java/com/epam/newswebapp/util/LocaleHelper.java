package com.epam.newswebapp.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;

/**
 * The LocaleHelper class for vacillating the work with locals in the application.
 */
public class LocaleHelper {
	
	/**
	 * The enumeration Locals with supported by the application locals (en-US, ru-RU)
	 */
	public enum Locals{ru_RU(new Locale("ru", "RU")), en_US(Locale.US);
	
		private Locals(Locale locale) {
			this.locale = locale;
		}
	
		public Locale getLocale() {
			return locale;
		}

		private Locale locale;
	}
	
	private LocaleHelper(){}
	
	/**
	 * Figures out, if the string language parameter is supported by the Locals enumeration.
	 * @param language
	 * @return Locale
	 */
	public static Locale identifyLocale(String language) {
		try{
			return Locals.valueOf(language).getLocale();	
		}catch(IllegalArgumentException e){
			return Locals.en_US.getLocale();
		}	
	}
	
	/**
	 * Gets the current locale in the session.
	 * @param request
	 * @return Locale
	 */
	public static Locale getCurrentLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
	}
	
	/**
	 * Sets the current locale in the session.
	 * @param request
	 * @param locale
	 */
	public static void setCurrentLocale(HttpServletRequest request, Locale locale) {
		request.getSession().setAttribute(Globals.LOCALE_KEY, locale);
	}

}
