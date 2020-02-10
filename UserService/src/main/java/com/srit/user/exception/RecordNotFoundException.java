package com.srit.user.exception;

public class RecordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public RecordNotFoundException(String exception) {
        super(exception);
    }
}
