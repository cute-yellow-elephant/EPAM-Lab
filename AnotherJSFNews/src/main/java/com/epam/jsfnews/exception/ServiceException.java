package com.epam.jsfnews.exception;

/**
 * The ServiceExcepton class is used to define exceptions for the service package.
 * @author Yuliya_Shydlouskaya
 *
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	
}
