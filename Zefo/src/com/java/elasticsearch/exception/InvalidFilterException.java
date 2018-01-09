package com.java.elasticsearch.exception;

public class InvalidFilterException extends Exception {
	private static final long serialVersionUID = 4384423262373314133L;

	public InvalidFilterException() {
		super();
	}

	public InvalidFilterException(final String message) {
		super(message);
	}

	public InvalidFilterException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidFilterException(final Throwable cause) {
		super(cause);
	}
}
