package com.hexaware.payxpert.dao.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.payxpert.model.FinancialRecord;

public interface IFinancialRecordService {
    void addFinancialRecord(FinancialRecord financialRecord);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate);
}