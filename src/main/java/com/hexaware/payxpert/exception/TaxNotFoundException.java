package com.hexaware.payxpert.exception;

import com.hexaware.payxpert.Constants;

public class TaxNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TaxNotFoundException (int employeeId, int taxYear) {
		super(Constants.YELLOW + "⚠Error! " + Constants.RESET 
				+ "Tax details for Employee: " + employeeId +  "for Financial Year: " + taxYear);
	}

}
