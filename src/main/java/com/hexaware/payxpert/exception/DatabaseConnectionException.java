package com.hexaware.payxpert.exception;

public class DatabaseConnectionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private String customMessage;

    public DatabaseConnectionException(String customMessage, Throwable cause) {
        super(cause);
        this.customMessage = customMessage;
    }

    @Override
    public String getMessage() {
        return "Database Connection Error: " + customMessage;
    }
}