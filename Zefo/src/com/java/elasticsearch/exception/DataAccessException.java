package com.java.elasticsearch.exception;

public class DataAccessException extends Exception {
	private static final long serialVersionUID = 4384423262373314133L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(final String message) {
		super(message);
	}

	public DataAccessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(final Throwable cause) {
		super(cause);
	}
}
