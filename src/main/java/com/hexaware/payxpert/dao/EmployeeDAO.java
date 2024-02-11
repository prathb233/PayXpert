package com.hexaware.payxpert.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.payxpert.Constants;
import com.hexaware.payxpert.dao.service.IEmployeeService;
import com.hexaware.payxpert.exception.EmployeeNotFoundException;
import com.hexaware.payxpert.model.Employee;
import com.hexaware.payxpert.util.DBConnection;

public class EmployeeDAO extends DBConnection implements IEmployeeService{

	@Override
	public void getConn() {
		con = getDBConn();
	}
	
    @Override //Get an employee by ID
    public Employee getEmployeeById(int employeeId) {
	Employee employee = null;
	try {
	    String sqlQuery = "SELECT * FROM employee WHERE Employee_ID = ?";

		ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, employeeId);
		rs = ps.executeQuery();

		if (rs.next()) {
			// Convert Date to LocalDate
		    LocalDate dateOfBirth = rs.getDate("Date_of_Birth").toLocalDate();
		    LocalDate joiningDate = rs.getDate("Joining_Date").toLocalDate();
		    Date terminationDateSQL = rs.getDate("Termination_Date");
		    LocalDate terminationDate = (terminationDateSQL != null) ? terminationDateSQL.toLocalDate() : null;
		    
		    // Create Employee object from ResultSet
		    employee = new Employee(
			    rs.getInt("Employee_Id"),
			    rs.getString("First_Name"),
			    rs.getString("Last_Name"),
			    dateOfBirth,
			    rs.getString("Gender"),
			    rs.getString("Email"),
			    rs.getString("Phone_Number"),
			    rs.getString("Address"),
			    rs.getString("Position"),
			    joiningDate,
			    terminationDate);
		    
		} else {
            // Employee not found
            throw new EmployeeNotFoundException(employeeId);
        }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	    // Handle SQL exception
	} 
	return employee;
	}

    @Override //Get all employees
    public List<Employee> getAllEmployees() {
	List<Employee> employees = new ArrayList<>();

	try {
	    String query = "SELECT * FROM employee";
	    stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		while (rs.next()) {
			
			// Convert Date to LocalDate
		    LocalDate dateOfBirth = rs.getDate("Date_of_Birth").toLocalDate();
		    LocalDate joiningDate = rs.getDate("Joining_Date").toLocalDate();
		    Date terminationDateSQL = rs.getDate("Termination_Date");
		    LocalDate terminationDate = (terminationDateSQL != null) ? terminationDateSQL.toLocalDate() : null;
		    //LocalDate terminationDate = rs.getDate("Termination_Date").toLocalDate();
		    
		    // Create Employee objects from ResultSet and add to the list
		    Employee employee = new Employee(
				    rs.getInt("Employee_Id"),
				    rs.getString("First_Name"),
				    rs.getString("Last_Name"),
				    dateOfBirth,
				    rs.getString("Gender"),
				    rs.getString("Email"),
				    rs.getString("Phone_Number"),
				    rs.getString("Address"),
				    rs.getString("Position"),
				    joiningDate,
				    terminationDate
				    );
		    employees.add(employee);
		}
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	    // Handle SQL exception
	}
	return employees;
    }

    @Override //Add an employee
    public void addEmployee(Employee employee) {
		try {
		    String query = "INSERT INTO employee (First_Name, Last_Name,"
		    		+ "Date_of_Birth, Gender, Email, Phone_Number, Address, Position,"
		    		+ "Joining_Date, Termination_Date)"
		    		+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	
		    ps = con.prepareStatement(query);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setObject(3, employee.getDateOfBirth());
			ps.setString(4, employee.getGender());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getPhoneNumber());
			ps.setString(7, employee.getAddress());
			ps.setString(8, employee.getPosition());
			ps.setObject(9, employee.getJoiningDate());
			ps.setObject(10, employee.getTerminationDate());
			
			ps.executeUpdate();
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		    // Handle SQL exception
		}
    }

    @Override //Update an employee
    public void updateEmployee(Employee employee) {
	try {
	    String query = "UPDATE employee SET First_Name = ?, Last_Name = ?, "
	    		+ "Date_of_Birth = ?, Gender = ?, Email = ?, Phone_Number = ?, "
	    		+ "Address = ?, Position = ?, Termination_Date = ? "
	    		+ "WHERE Employee_ID = ?";
	    ps = con.prepareStatement(query);
		ps.setString(1, employee.getFirstName());
		ps.setString(2, employee.getLastName());
		ps.setObject(3, employee.getDateOfBirth());
		ps.setString(4, employee.getGender());
		ps.setString(5, employee.getEmail());
		ps.setString(6, employee.getPhoneNumber());
		ps.setString(7, employee.getAddress());
		ps.setString(8, employee.getPosition());
		ps.setObject(9, employee.getTerminationDate());

		ps.setInt(10, employee.getEmployeeID());

		ps.executeUpdate();
		
	} catch (SQLException e) {
	    e.printStackTrace();
	    // Handle SQL exception
	}
    }

    @Override //Remove an employee
    public void removeEmployee(int employeeId) {
	try {
	    String query = "DELETE FROM employee WHERE Employee_ID = ?";
	    ps = con.prepareStatement(query);
		ps.setInt(1, employeeId);

		int rowsAffected = ps.executeUpdate();
		
		if(rowsAffected == 0) {
			System.out.println(Constants.RED 
					+ "Sorry employee with ID: " + employeeId + " not found!" + Constants.RESET);
		} else {
		    System.out.println(Constants.GREEN 
		    		+ "Employee with ID: " + employeeId + " removed successfully!"  
		    		+ Constants.RESET);
		}
	 
	} catch (SQLException e) {
	    e.printStackTrace();
	    // Handle SQL exception
	}
    }

    @Override
    public void callCloseCon() {
    	closeConnection();
    }
}

