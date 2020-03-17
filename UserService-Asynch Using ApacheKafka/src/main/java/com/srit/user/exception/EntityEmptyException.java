package com.srit.user.exception;

public class EntityEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityEmptyException (String exception) {
		super(exception);
	}
}
