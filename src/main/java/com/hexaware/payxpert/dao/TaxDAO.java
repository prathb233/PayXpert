package com.hexaware.payxpert.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.payxpert.dao.service.ITaxService;
import com.hexaware.payxpert.exception.TaxNotFoundException;
import com.hexaware.payxpert.model.Tax;
import com.hexaware.payxpert.util.DBConnection;

public class TaxDAO extends DBConnection implements ITaxService{
	
    //Get the database connection from DBConnection
	public void getConn() {
		con = getDBConn();
	}
	
    @Override //Get Tax for an employee for the given Year
    public Tax getTaxByYear(int employeeId, int taxYear) {
    	Tax tax = null;
    	try {
			
			String query2 = "SELECT * FROM Tax WHERE Tax_Year = ? AND Employee_ID = ?";
			ps = con.prepareStatement(query2);
			ps.setInt(1, taxYear);
			ps.setInt(2, employeeId);
			rs = ps.executeQuery();
			
            if (rs.next()) {
                tax = new Tax(
                        rs.getInt("Tax_ID"),
                        rs.getInt("Employee_ID"),
                        rs.getInt("Tax_Year"),
                        rs.getDouble("Taxable_Income"),
                        rs.getDouble("Tax_Amount")
                );
            } else {
            	throw new TaxNotFoundException(employeeId, taxYear);
            }
	
			rs.next();
			Double taxAmount = rs.getDouble(5);
			System.out.println("Your new Tax Amount for the Fin. year: " 
					+ taxYear + " is: " + taxAmount);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return tax;
    }

    @Override //Get tax by ID
    public Tax getTaxById(int taxId) {
        Tax tax = null;
        try {
            String sqlQuery = "SELECT * FROM Tax WHERE Tax_ID = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, taxId);
            rs = ps.executeQuery();

            if (rs.next()) {
                tax = new Tax(
                        rs.getInt("Tax_ID"),
                        rs.getInt("Employee_ID"),
                        rs.getInt("Tax_Year"),
                        rs.getDouble("Taxable_Income"),
                        rs.getDouble("Tax_Amount")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return tax;
    }

    @Override //Get taxes for an employee
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxes = new ArrayList<>();

        try {
            String query = "SELECT * FROM Tax WHERE Employee_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tax tax = new Tax(
                        rs.getInt("Tax_Id"),
                        rs.getInt("Employee_Id"),
                        rs.getInt("Tax_Year"),
                        rs.getDouble("Taxable_Income"),
                        rs.getDouble("Tax_Amount")
                );
                taxes.add(tax);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return taxes;
    }

    @Override //Get taxes for a specific year
    public List<Tax> getTaxesForYear(int year) {
        List<Tax> taxes = new ArrayList<>();

        try {
            String query = "SELECT * FROM Tax WHERE Tax_Year = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, year);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tax tax = new Tax(
                        rs.getInt("Tax_Id"),
                        rs.getInt("Employee_Id"),
                        rs.getInt("Tax_Year"),
                        rs.getDouble("Taxable_Income"),
                        rs.getDouble("Tax_Amount")
                );
                taxes.add(tax);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return taxes;
    }

    //Close the database connection from DBConnection
    public void callCloseCon() {
    	closeConnection();
    }

}
