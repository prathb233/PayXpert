package com.hexaware.payxpert.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.payxpert.dao.service.IPayrollService;
import com.hexaware.payxpert.exception.PayrollGenerationException;
import com.hexaware.payxpert.model.Payroll;
import com.hexaware.payxpert.util.DBConnection;

public class PayrollDAO extends DBConnection implements IPayrollService {

   //Get the database connection from DBConnection
	public void getConn() {
		con = getDBConn();
	}
	
	
    @Override //Generate payroll for an employee
    public void generatePayroll(int employeeId, double basicSalary, double overtimePay, double deductions) {
    	try {
            String query = "INSERT INTO payroll "
            		+ "(Employee_Id, Basic_Salary, Overtime_Pay, Deductions) "
            		+ "VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, employeeId);
            ps.setDouble(2, basicSalary);
            ps.setDouble(3, overtimePay);
            ps.setDouble(4, deductions);
            
            ps.executeUpdate();
            
			// Retrieve auto-generated keys (in this case, PayrollID)
			ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
				    int generatedPayrollID = generatedKeys.getInt(1);
		            System.out.println("\nPayroll generated successfully with ID: " + generatedPayrollID);
				}

        } catch (SQLIntegrityConstraintViolationException  e) {
            System.out.println("Employee with ID: " + employeeId + " not found");

        } catch (SQLException e) {
        	// Handle general SQL exception
            e.printStackTrace();            
        } 
    }

    @Override //Get payroll by ID
    public Payroll getPayrollById(int payrollId) {
        Payroll payroll = null;

        try {
            String sqlQuery = "SELECT * FROM payroll WHERE Payroll_Id = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, payrollId);
            rs = ps.executeQuery();

            if (rs.next()) {
            	// Convert Date to LocalDate
    		    LocalDate startDate = rs.getDate("Pay_Period_Start_Date").toLocalDate();
    		    LocalDate endDate = rs.getDate("Pay_Period_End_Date").toLocalDate();
    		    //LocalDate endDate = (endDate != null) ? endDate.toLocalDate() : null;
    		    
                payroll = new Payroll(
                        rs.getInt("Payroll_Id"),
                        rs.getInt("Employee_Id"),
                        startDate,
                        endDate,
                        rs.getDouble("Basic_Salary"),
                        rs.getDouble("Overtime_Pay"),
                        rs.getDouble("Gross_Salary"),
                        rs.getDouble("Deductions"),
                        rs.getDouble("Net_Salary")
                );
            } else {
            	throw new PayrollGenerationException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }

        return payroll;
    }

    @Override //Get payrolls for an employee
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrolls = new ArrayList<>();

        try {
            String query = "SELECT * FROM payroll WHERE Employee_Id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();

            while (rs.next()) {
            	// Convert Date to LocalDate
    		    LocalDate startDate = rs.getDate("Pay_Period_Start_Date").toLocalDate();
    		    LocalDate endDate = rs.getDate("Pay_Period_End_Date").toLocalDate();
    		    //LocalDate endDate = (endDate != null) ? endDate.toLocalDate() : null;
    		    
                Payroll payroll = new Payroll(
                        rs.getInt("Payroll_Id"),
                        rs.getInt("Employee_Id"),
                        startDate,
                        endDate,
                        rs.getDouble("Basic_Salary"),
                        rs.getDouble("Overtime_Pay"),
                        rs.getDouble("Gross_Salary"),
                        rs.getDouble("Deductions"),
                        rs.getDouble("Net_Salary")
                );
                payrolls.add(payroll);
            } if(!rs.next()) {
            	throw new PayrollGenerationException(employeeId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }

        return payrolls;
    }

    @Override //Get payrolls for a specific period
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> payrolls = new ArrayList<>();

        try {
            String query = "SELECT * FROM payroll WHERE Pay_Period_Start_Date >= ? AND Pay_Period_End_Date <= ?";
            ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            rs = ps.executeQuery();

            while (rs.next()) {
            	// Convert Date to LocalDate
    		    LocalDate startDate2 = rs.getDate("Pay_Period_Start_Date").toLocalDate();
    		    LocalDate endDate2 = rs.getDate("Pay_Period_End_Date").toLocalDate();
    		    //LocalDate endDate = (endDate != null) ? endDate.toLocalDate() : null;
    		    
                Payroll payroll = new Payroll(
                        rs.getInt("Payroll_Id"),
                        rs.getInt("Employee_Id"),
                        startDate2,
                        endDate2,
                        rs.getDouble("Basic_Salary"),
                        rs.getDouble("Overtime_Pay"),
                        rs.getDouble("Gross_Salary"),
                        rs.getDouble("Deductions"),
                        rs.getDouble("Net_Salary")
                );
                payrolls.add(payroll);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }

        return payrolls;
    }

    //Close the database connection from DBConnection
    public void callCloseCon() {
    	closeConnection();
    }
}
