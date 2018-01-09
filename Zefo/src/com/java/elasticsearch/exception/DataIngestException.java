package com.java.elasticsearch.exception;

public class DataIngestException extends Exception {
	private static final long serialVersionUID = 4384423262373314133L;

	public DataIngestException() {
		super();
	}

	public DataIngestException(final String message) {
		super(message);
	}

	public DataIngestException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DataIngestException(final Throwable cause) {
		super(cause);
	}
}
