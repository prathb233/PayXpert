package com.hexaware.payxpert.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.Constants;
import com.hexaware.payxpert.dao.PayrollDAO;
import com.hexaware.payxpert.exception.PayrollGenerationException;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.Payroll;

public class PayrollController {
	PayrollDAO payrollDAO = new PayrollDAO();
	
	/**
	 * It displays the Payroll Menu and operations which can be performed in it
	 * @param scanner
	 */
	public void payrollOperations(Scanner scanner) {
		
	try {
	//Initializing the DB connection to carry out Operations
	payrollDAO.getConn();
	
  	int payrollOption;
  	do {
      System.out.println(Constants.CYAN + "\n---Payroll Menu---" + Constants.RESET);
      System.out.println("1. Generate Payroll");
      System.out.println("2. Get Payroll by ID");
      System.out.println("3. Get Payrolls for Employee");
      System.out.println("4. Get Payrolls for Period");
      System.out.println("0. Back to Main Menu");
      System.out.print("Choose an option: ");

      payrollOption = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (payrollOption) {
    	  case 1:
		  System.out.print("\nEnter employee ID: ");
		  int employeeId = scanner.nextInt();
		  payrollDAO.getLatestPayroll(employeeId);

		  System.out.print("\nBase Salary for the Employee: ");
		  double baseSalary = scanner.nextDouble();
		  System.out.print("Overtime Pay: ");
		  double overtimePay = scanner.nextDouble();
		  System.out.print("Loan/ Prof. Tax/ EPF Deductions: ");
		  double deductions = scanner.nextDouble();
		  
		  payrollDAO.generatePayroll(employeeId, baseSalary, overtimePay, deductions);
		  break;
		  
    	  case 2:
			try {
				System.out.print("\nEnter payroll ID: ");
				int payrollId = scanner.nextInt();
				Payroll payrollById = payrollDAO.getPayrollById(payrollId);
				System.out.println(Constants.GREEN + "\n---Payroll Details---" + Constants.RESET
									+ payrollById);
				break;
			} catch (PayrollGenerationException p) {
				System.out.println(Constants.RED + "Error: " + Constants.RESET + p.getMessage());
				break;
			}
          
    	  case 3:
			try {
			  System.out.print("\nEnter employee ID: ");
			  int employeeIdForPayrolls = scanner.nextInt();
			  List<Payroll> payrollsForEmployee = payrollDAO.getPayrollsForEmployee(employeeIdForPayrolls);
			  System.out.println(Constants.GREEN + "\n---Payrolls for Employee---\n" + Constants.RESET
					  			+ payrollsForEmployee);
			  break;
			} catch (PayrollGenerationException p) {
				System.out.println("Error: " + p.getMessage());
		        break;
			}
          
    	  case 4:
          System.out.print("\nFrom (YYYY-MM-DD): ");
          LocalDate startDateForPeriod = LocalDate.parse(scanner.nextLine());
          System.out.print("To (YYYY-MM-DD): ");
          LocalDate endDateForPeriod = LocalDate.parse(scanner.nextLine());
          
          List<Payroll> payrollsForPeriod = payrollDAO.getPayrollsForPeriod(startDateForPeriod, endDateForPeriod);
          System.out.println(Constants.GREEN + "\n---Payrolls for the given Period---\n" + Constants.RESET 
        		  			+ payrollsForPeriod);
          break;
          
    	  case 0:
          System.out.println("Returning to Main Menu...\n");
          Main.displayMenu(scanner);
          break;
          
    	  default:
          System.out.println(Constants.YELLOW + "Invalid option. Please try again." + Constants.RESET);
          break;
      	}
      	} while (payrollOption != 0);

	} 
	finally {
		payrollDAO.callCloseCon();
	}
	} 

}
