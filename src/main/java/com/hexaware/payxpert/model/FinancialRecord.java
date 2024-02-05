package com.hexaware.payxpert.model;

import java.time.LocalDate;

public class FinancialRecord {
    // Properties
    private int recordID;
    private int employeeID;
    private LocalDate recordDate;
    private String description;
    private double amount;
    private String recordType;

    //default constr.
    public FinancialRecord() {
	
    }
    
    // Constructor
    public FinancialRecord(int recordID, int employeeID, LocalDate recordDate,
                           String description, double amount, String recordType) {
        this.recordID = recordID;
        this.employeeID = employeeID;
        this.recordDate = recordDate;
        this.description = description;
        this.amount = amount;
        this.recordType = recordType;
    }

    public FinancialRecord(int employeeId2, LocalDate transactionDate,
			String description2, double amount2) {
		// TODO Auto-generated constructor stub
	}

	public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

	@Override
	public String toString() {
		return "\n[Record ID = " + recordID + "\nEmployee ID = "
				+ employeeID + "\nRecord Date = " + recordDate + "\nDescription = "
				+ description + "\nAmount = " + amount + "\nRecord Type = "
				+ recordType + "]\n";
	}
    
    
    
}