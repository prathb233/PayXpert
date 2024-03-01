package com.hexaware.payxpert.model;

public class Tax {
    // Properties
    private int taxID;
    private int employeeID;
    private int taxYear;
    private double taxableIncome;
    private double taxAmount;

    //default constr
    public Tax() {

    }

    // Constructor
    public Tax(int taxID, int employeeID, int taxYear, 
	    double taxableIncome, double taxAmount) {
	this.taxID = taxID;
	this.employeeID = employeeID;
	this.taxYear = taxYear;
	this.taxableIncome = taxableIncome;
	this.taxAmount = taxAmount;
    }

    public int getTaxID() {
        return taxID;
    }

    public void setTaxID(int taxID) {
        this.taxID = taxID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

	@Override
	public String toString() {
		return 
				"\nTax ID: " + taxID + 
				"\nEmployee ID: " + employeeID + 
				"\nTax Year: " + taxYear + 
				"\nTaxable Income: " + taxableIncome + 
				"\nTax Amount: " + taxAmount + "\n";
	}
    
    
}