package com.epam.jsfnews.exception;

/**
 * The DAOExcepton class is used to define exceptions for the database package.
 * @author Yuliya_Shydlouskaya
 *
 */
public class DAOException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public DAOException() {
		super();
	}
	
	public DAOException(String message){
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
