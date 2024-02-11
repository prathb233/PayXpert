package com.hexaware.payxpert.dao.service;

import java.util.List;

import com.hexaware.payxpert.model.Employee;

public interface IEmployeeService {
	
	//Get the database connection from DBConnection
	public void getConn();

	/**
	 * Retrieves employee details from the database using SQL queries
	 * @param employeeId for whom the data is being retrieved
	 * @return Employee type object containg information of the requested employee
	 * @throws EmployeeNotFound Exception if a invalid employee id is entered
	 */
    Employee getEmployeeById(int employeeId);
    
    /**
     * Fetches the information of all the employee present in database and stores it in a list
     * @return Employee type ArrayList containing details of all the employees
     */
    List<Employee> getAllEmployees();
    
    /**
     * Adds a new employee to the database using SQL query
     * @param employee which stores the employee details enterd by user
     */
    void addEmployee(Employee employee);
    
    /**
     * Updates employee details for given employee
     * @param employee stores the updated details enterd by user
     */
    void updateEmployee(Employee employee);
    
    /**
     * Removes the given employee from DB
     * @param employeeId employee to remove
     */
    void removeEmployee(int employeeId);
    
	//Close the database connection from DBConnection
	public void callCloseCon();
}
