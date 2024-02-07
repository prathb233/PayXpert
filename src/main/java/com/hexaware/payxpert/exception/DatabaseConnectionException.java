package com.hexaware.payxpert.exception;

import com.hexaware.payxpert.Constants;

public class DatabaseConnectionException extends RuntimeException {
	private static final long serialVersionUID = 1L;


    public DatabaseConnectionException() {
        super(Constants.YELLOW + "⚠Error connecting to the database!" + Constants.RESET
        		+ "\nPlease check your credentials and try again.");
    }
}