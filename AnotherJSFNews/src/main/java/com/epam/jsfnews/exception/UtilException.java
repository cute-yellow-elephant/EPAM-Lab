package com.epam.jsfnews.exception;

/**
 * The UtilExcepton class is used to define exceptions for the util package.
 * @author Yuliya_Shydlouskaya
 *
 */
public class UtilException extends Exception{

	private static final long serialVersionUID = 1L;

	public UtilException() {
		super();
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(String message) {
		super(message);
	}

	public UtilException(Throwable cause) {
		super(cause);
	}

	
}