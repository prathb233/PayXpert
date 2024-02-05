package com.hexaware.payxpert.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.payxpert.dao.service.IFinancialRecordService;
import com.hexaware.payxpert.model.FinancialRecord;
import com.hexaware.payxpert.util.DBConnection;

public class FinancialRecordDAO extends DBConnection implements IFinancialRecordService {

    //Get the database connection from DBConnection
	public void getConn() {
		con = getDBConn();
	}
	
    @Override
	/*
	 * We dont need an implementation for adding Financial Records since
	 * it has been handeled in the Database itself using Triggers!!
	 * still I have added just for demonstration
	 */
    public void addFinancialRecord(FinancialRecord financialRecord) {
        try {
        	con = getDBConn();
            String query = "INSERT INTO Financial_Record (Employee_ID, Record_Date, Description, Amount, Record_Type) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, financialRecord.getEmployeeID());
            ps.setDate(2, Date.valueOf(financialRecord.getRecordDate()));
            ps.setString(3, financialRecord.getDescription());
            ps.setDouble(4, financialRecord.getAmount());
            ps.setString(5, financialRecord.getRecordType());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    @Override //Get financial record by ID
    public FinancialRecord getFinancialRecordById(int financialRecordId) {
        FinancialRecord financialRecord = null;

        try {
            String sqlQuery = "SELECT * FROM Financial_Record WHERE Record_ID = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, financialRecordId);
            rs = ps.executeQuery();

            if (rs.next()) {
                financialRecord = new FinancialRecord(
                        rs.getInt("Record_Id"),
                        rs.getInt("Employee_ID"),
                        rs.getDate("Record_Date").toLocalDate(),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("Record_Type")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }

        return financialRecord;
    }

    @Override //Get financial records for an employee
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> financialRecords = new ArrayList<>();

        try {
            String query = "SELECT * FROM Financial_Record WHERE Employee_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                FinancialRecord financialRecord = new FinancialRecord(
                        rs.getInt("Record_Id"),
                        rs.getInt("Employee_ID"),
                        rs.getDate("Record_Date").toLocalDate(),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("Record_Type")
                );
                financialRecords.add(financialRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }

        return financialRecords;
    }

    @Override //Get financial records for a specific date
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate date) {
        List<FinancialRecord> financialRecords = new ArrayList<>();

        try {
            String query = "SELECT * FROM Financial_Record WHERE Record_Date = ?";
            ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(date));
            rs = ps.executeQuery();

            while (rs.next()) {
                FinancialRecord financialRecord = new FinancialRecord(
                        rs.getInt("Record_Id"),
                        rs.getInt("Employee_ID"),
                        rs.getDate("Record_Date").toLocalDate(),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("Record_Type")
                );
                financialRecords.add(financialRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }

        return financialRecords;
    }

    //Close the database connection from DBConnection
    public void callCloseCon() {
    	closeConnection();
    }
    
}
