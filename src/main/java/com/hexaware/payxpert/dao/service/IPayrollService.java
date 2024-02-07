package com.hexaware.payxpert.dao.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.payxpert.model.Payroll;

public interface IPayrollService {
	/**
	 * Generates the payroll using below parameters
	 * @param employeeId for which the Payroll is being generated
	 * @param basicSalary the Base salary amount
	 * @param overtimePay additional pay amount for overtime
	 * @param deductions total of amount deductions levied by the employer on account of (Prof. Tax, Consumer Loans, EPF etc)
	 */
    void generatePayroll(int employeeId, double basicSalary, double overtimePay, double deductions);
    
    /**
     * Retrieves the payroll details for the given Payroll ID
     * @param payrollId for which the payroll is being retrieved
     * @return Payroll object containing the payroll details 
     */
    Payroll getPayrollById(int payrollId);
    
    /**
     * Retrieves the list of payrolls for a given employee
     * @param employeeId for which the payrolls are being retrieved
     * @return Payroll type ArrayList conatining all the available payrolls for the given employee
     */
    List<Payroll> getPayrollsForEmployee(int employeeId);
    
    /**
     * Retrieves the list of payrolls for a given range of date
     * @param startDate start of range (inclusive)
     * @param endDate end of range (inclusive)
     * @return Payroll type ArrayList conatining all the available payrolls for the given range of date
     */
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
    
    /**
     * Its a helper method for generating Payroll, it mentions user about when the last payroll was generated
     * </br>for the given employee and notifies that they will be generating the payroll for the next month
     * @param employeeId for which the recent payroll is being fetched
     * @return last payroll generated for the given employee
     */
    LocalDate getLatestPayroll(int employeeId);
}