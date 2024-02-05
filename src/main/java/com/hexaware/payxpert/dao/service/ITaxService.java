package com.hexaware.payxpert.dao.service;

import java.util.List;

import com.hexaware.payxpert.model.Tax;

public interface ITaxService {
    void calculateTax(int employeeId, int taxYear);
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}