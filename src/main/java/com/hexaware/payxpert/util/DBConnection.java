package com.hexaware.payxpert.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.payxpert.exception.DatabaseConnectionException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/payxpert";
    private static final String USER = "root";
    private static final String PASSWORD = "abc123";
    
    protected static Connection con;
    protected PreparedStatement ps;
    protected Statement stmt;
    protected ResultSet rs;
    
    
    protected static Connection getDBConn() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error establishing database connection", e);

        }
        return con;
    }

    protected void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }
}
