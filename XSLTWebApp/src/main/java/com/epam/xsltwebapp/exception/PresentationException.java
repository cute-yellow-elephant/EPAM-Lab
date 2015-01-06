package com.epam.xsltwebapp.exception;

/**
 * The PresentationException class is used to indicate the exceptions, that occur in the controller.
 * @author Yuliya_Shydlouskaya
 *
 */
public class PresentationException extends Exception{

	private static final long serialVersionUID = -879734739805432028L;

	public PresentationException() {
		super();
	}

	public PresentationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PresentationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PresentationException(String message) {
		super(message);
	}

	public PresentationException(Throwable cause) {
		super(cause);
	}

	
}
