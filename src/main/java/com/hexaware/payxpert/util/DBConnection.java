package com.hexaware.payxpert.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.hexaware.payxpert.exception.DatabaseConnectionException;

public class DBConnection {	
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    protected static Connection con;
    protected PreparedStatement ps;
    protected Statement stmt;
    protected ResultSet rs;
    
    /**
     * Fetches the DB credentials from property file
     */
    private static void loadDbProperties() {
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("db.properties");
            prop.load(input);
            
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
        }
    }
    
    /**
     * Establishes the connection with Database
     * @return connection as con 
     */
    public static Connection getDBConn() {
        try {
            if (con == null || con.isClosed()) {
            	loadDbProperties();
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException();

        }
        //System.out.println(con);
        return con; 
    }
    

    /**
     * Closes the connection with Database
     */
    protected void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
//                rs.close();
//                stmt.close();
//                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }
}
