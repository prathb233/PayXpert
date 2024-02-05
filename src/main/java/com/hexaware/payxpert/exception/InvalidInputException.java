package com.hexaware.payxpert.exception;

public class InvalidInputException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public InvalidInputException() {
		System.err.println("Input type not as expected");
	}
	
}
