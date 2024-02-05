package com.hexaware.payxpert.exception;

public class EmployeeNotFoundException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException() {
        super("Employee not found.");
    }

    public EmployeeNotFoundException(int employeeId) {
        super("Employee not found with ID: " + employeeId);
    }
}