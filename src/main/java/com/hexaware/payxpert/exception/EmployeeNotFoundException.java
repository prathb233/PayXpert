package com.hexaware.payxpert.exception;

import com.hexaware.payxpert.Constants;

public class EmployeeNotFoundException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;


    public EmployeeNotFoundException(int employeeId) {
        super(Constants.YELLOW + "⚠Error! " + Constants.RESET + "Employee not found with ID: " + employeeId);
    }
}