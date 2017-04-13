package com.model.exceptions;

public class InvalidUsernameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 414193338903366984L;

	@Override
	public String getMessage() {
		return "Invalid username";
	}
}
