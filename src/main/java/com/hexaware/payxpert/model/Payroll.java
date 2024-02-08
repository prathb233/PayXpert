package com.hexaware.payxpert.model;

import java.time.LocalDate;

public class Payroll {
    // Properties
    private int payrollID;
    private int employeeID;
    private LocalDate payPeriodStartDate;
    private LocalDate payPeriodEndDate;
    private double basicSalary;
    private double overtimePay;
    private double grossSalary;
    private double deductions;
    private double netSalary;
    
    //default constr
    public Payroll() {
	
    }

    // Constructor
    public Payroll(int payrollID, int employeeID, 
	    LocalDate payPeriodStartDate, LocalDate payPeriodEndDate, 
	    double basicSalary, double overtimePay, double grossSalary,
	    double deductions, double netSalary) {
	
	this.payrollID = payrollID;
	this.employeeID = employeeID;
	this.payPeriodStartDate = payPeriodStartDate;
	this.payPeriodEndDate = payPeriodEndDate;
	this.basicSalary = basicSalary;
	this.overtimePay = overtimePay;
	this.grossSalary = grossSalary;
	this.deductions = deductions;
	this.netSalary = netSalary;
    }

    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setPayPeriodStartDate(LocalDate payPeriodStartDate) {
        this.payPeriodStartDate = payPeriodStartDate;
    }

    public LocalDate getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setPayPeriodEndDate(LocalDate payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }
    
    public double getGrossSalary() {
        return grossSalary;
    }
    
    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }
    
	@Override
	public String toString() {
		return "\nPayroll ID = " + payrollID + "\nEmployee ID = " + employeeID
				+ "\nPay Period Start Date = " + payPeriodStartDate
				+ "\nPay Period End Date = " + payPeriodEndDate 
				+ "\nBasic Salary = " + basicSalary + "\nOvertime Pay = " + overtimePay
				+ "\nGross Salary = " + grossSalary + "\nDeductions = " + deductions
				+ "\nNet Salary = " + netSalary + "\n";
	}
    
    
}