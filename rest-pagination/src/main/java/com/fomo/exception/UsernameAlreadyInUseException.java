package com.fomo.exception;

public class UsernameAlreadyInUseException extends RuntimeException{

	public UsernameAlreadyInUseException() {
		// TODO Auto-generated constructor stub
	}

	public UsernameAlreadyInUseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameAlreadyInUseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameAlreadyInUseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameAlreadyInUseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
