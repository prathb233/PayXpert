package com.hexaware.payxpert.dao.service;

import java.util.List;

import com.hexaware.payxpert.model.Employee;

public interface IEmployeeService {
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void removeEmployee(int employeeId);
}
