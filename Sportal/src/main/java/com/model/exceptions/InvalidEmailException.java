package com.model.exceptions;

public class InvalidEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4505259228052920670L;

	@Override
	public String getMessage() {
		return "Invalid email format.";
	}
	
}
