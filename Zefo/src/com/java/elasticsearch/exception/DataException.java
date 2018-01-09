package com.java.elasticsearch.exception;

public class DataException extends Exception {
	private static final long serialVersionUID = 4384423262373314133L;

	public DataException() {
		super();
	}

	public DataException(final String message) {
		super(message);
	}

	public DataException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DataException(final Throwable cause) {
		super(cause);
	}
}
