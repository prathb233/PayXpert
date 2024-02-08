package com.hexaware.payxpert.exception;

import com.hexaware.payxpert.Constants;

public class PayrollGenerationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    //Throws error while fetching Payroll with wrong payrollID
    public PayrollGenerationException() {
        super(Constants.YELLOW + "Invalid payroll ID!" + Constants.RESET
        		+ "\nPayroll not found in the database.");

    }

    //Throws error while fetching Payroll for a non-existing employee
	public PayrollGenerationException(int employeeId) {
        super(Constants.YELLOW + "No Payrolls generated for the given employee!" + Constants.RESET
        		+ "\nOR Emplyoee with ID: " + employeeId + " not found.");
	}

}
