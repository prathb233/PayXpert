package com.hexaware.payxpert.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hexaware.payxpert.model.Tax;

public class TaxDAOTest {

	private TaxDAO taxDAO;
	
    @Before
    public void setUp() {
    	//establishing connection with DB before testing
    	taxDAO = new TaxDAO();
    	taxDAO.getConn();
    }
    
    @Test
    public void testGetPayrollById() {
        // Creating test variables to compare with actual values
        int testTaxId = 4760;
        double expectedTaxAmount = 335061.70;

        // Call the method to get the actual values.
        Tax tax = taxDAO.getTaxById(testTaxId);

        // Assert that the actual values match the expected values.
        assertEquals(expectedTaxAmount, tax.getTaxAmount(), 0.001);
    }
}
