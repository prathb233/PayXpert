package com.hexaware.payxpert.exception;

public class InvalidInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
        super("Invalid input");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}

