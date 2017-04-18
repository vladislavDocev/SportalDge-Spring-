package com.model.exceptions;

public class InvalidNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6609310674801614987L;

	@Override
	public String getMessage() {
		return "Invalid name";
	}
	
}
