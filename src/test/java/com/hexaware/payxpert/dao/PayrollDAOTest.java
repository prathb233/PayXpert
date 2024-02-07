package com.hexaware.payxpert.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hexaware.payxpert.model.Payroll;
import com.hexaware.payxpert.util.DBConnection;

public class PayrollDAOTest extends DBConnection{
	
    private PayrollDAO payrollDAO;

    @Before
    public void setUp() {
    	//establishing connection with DB before testing
    	payrollDAO = new PayrollDAO();
    	payrollDAO.getConn();
    }
    
    @Test
    public void testGetPayrollById() {
        // Creating test variables to compare with actual values
        int testPayrollId = 1001;
        double expectedGrossSalary = 77900.0;
        double expectedNetSalary = 75900.0;

        // Call the method to get the actual values.
        Payroll payroll = payrollDAO.getPayrollById(testPayrollId);

        // Assert that the actual values match the expected values.
        assertEquals(expectedGrossSalary, payroll.getGrossSalary(), 0.001);
        assertEquals(expectedNetSalary, payroll.getNetSalary(), 0.001); // 0.001 is the delta for double comparison.
    }

}
