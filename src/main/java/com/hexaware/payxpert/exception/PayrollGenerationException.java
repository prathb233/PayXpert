package com.hexaware.payxpert.exception;

public class PayrollGenerationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PayrollGenerationException() {
        super("Error generating payroll.");

    }

}
