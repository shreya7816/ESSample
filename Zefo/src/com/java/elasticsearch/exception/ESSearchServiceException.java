package com.java.elasticsearch.exception;

public class ESSearchServiceException extends Exception {
	private static final long serialVersionUID = 4384423262373314133L;

	public ESSearchServiceException() {
		super();
	}

	public ESSearchServiceException(final String message) {
		super(message);
	}

	public ESSearchServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ESSearchServiceException(final Throwable cause) {
		super(cause);
	}
}
