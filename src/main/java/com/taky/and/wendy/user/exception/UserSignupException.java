package com.taky.and.wendy.user.exception;

@SuppressWarnings("serial")
public class UserSignupException extends RuntimeException {
	public UserSignupException() {
		super();
	}

	public UserSignupException(String message) {
		super(message);
	}
	
	public UserSignupException(Throwable e) {
		super(e);
	}
	
	public UserSignupException(String message, Throwable e) {
		super(message, e);
	}
}
