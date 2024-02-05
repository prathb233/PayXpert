package com.hexaware.payxpert.dao.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.payxpert.model.Payroll;

public interface IPayrollService {
    void generatePayroll(int employeeId, double basicSalary, double overtimePay, double deductions);
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
}