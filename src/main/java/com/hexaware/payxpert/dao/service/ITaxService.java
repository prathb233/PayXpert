package com.hexaware.payxpert.dao.service;

import java.util.List;

import com.hexaware.payxpert.model.Tax;

public interface ITaxService {
	/**
	 * We dont require an implementation of this 
	 * since its getting calculated in the database itself using Triggers!
	 * @param employeeId
	 * @param taxYear
	 */
    void calculateTax(int employeeId, int taxYear);
    
    /**
     * Retrieves the Income Tax for the given TaxID 
     * @param taxId for which the record is being retrieved
     * @return Tax type object containing the complete details of the given TaxID
     * (i.e To whom this TaxID belongs to (EmployeeID), Tax Year, Taxable Amount, Calculated Tax)
     */
    Tax getTaxById(int taxId);
    
    /**
     * Retrives the Income Tax for the given employee
     * @param employeeId for which the Tax details are being retrieved
     * @return Tax type ArrayList containing the list of Tax details for the given employee
     */
    List<Tax> getTaxesForEmployee(int employeeId);
    
    /**
     * Retrives the Income Tax for the given Financial year
     * @param taxYear for which the Tax details are being retrieved
     * @return Tax type ArrayList containing the list of Tax details for the given Fin. year
     */
    List<Tax> getTaxesForYear(int taxYear);

	void displayUpdatedTax(int employeeId, int taxYear);
}