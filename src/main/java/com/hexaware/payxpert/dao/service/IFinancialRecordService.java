package com.hexaware.payxpert.dao.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.payxpert.model.FinancialRecord;

public interface IFinancialRecordService {
	/**
	 * We dont really need an implementation for adding Financial Records </br> since it has been handeled in the Database itself using Triggers!!
	 * </br>still I have added the implementation just for demonstration
	 * @param financialRecord containg appropriate attributes of the Financial Record
	 */
    void addFinancialRecord(FinancialRecord financialRecord);
    
    /**
     * Retrieves the Financial Record for the given Financial Record ID
     * @param recordId for which the record is intended to retrieve
     * @return FinancialRecord type object containing the record
     */
    FinancialRecord getFinancialRecordById(int recordId);
    
    /**
     * Retrieves the Financial Record for the given employee
     * @param employeeId for whom the record is being retrieved
     * @return FinancialRecord type object containing the record
     */
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    
    /**
     * Retrieves the Financial Record for the given date
     * @param recordDate for which the record is being retrieved
     * @return FinancialRecord type object containing the record
     */
    List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate);
}