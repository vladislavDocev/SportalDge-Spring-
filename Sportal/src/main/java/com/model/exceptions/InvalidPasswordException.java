package com.model.exceptions;

public class InvalidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2271495033153776859L;

	@Override
	public String getMessage() {
		return "Not a strong enough password.";
	}
	
}
