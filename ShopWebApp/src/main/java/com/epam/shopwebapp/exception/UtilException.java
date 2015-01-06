package com.epam.shopwebapp.exception;

/**
 * The UtilException class is used for indicating exceptions from the com.epam.shopwebapp.util package.
 * @author Yuliya_Shydlouskaya
 */
public class UtilException extends Exception{

	private static final long serialVersionUID = 1L;

	public UtilException() {
		super();
	}

	public UtilException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
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
