package com.hexaware.payxpert.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.dao.PayrollDAO;
import com.hexaware.payxpert.exception.PayrollGenerationException;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.Payroll;

public class PayrollController {
	PayrollDAO payrollDAO = new PayrollDAO();
	
	public void payrollOperations(Scanner scanner) {
	//Initializing the DB Conn b4 carring out Operations
	payrollDAO.getConn();
	
	  int payrollOption;
      do {
          System.out.println("\n---Payroll Menu---");
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
                  try {
					System.out.print("Enter employee ID: ");
					  int employeeId = scanner.nextInt();
					  System.out.print("Base Salary for the Employee: ");
					  double baseSalary = scanner.nextDouble();
					  System.out.print("Overtime Pay: ");
					  double overtimePay = scanner.nextDouble();
					  System.out.print("Loan/ Prof. Tax/ EPF Deductions: ");
					  double deductions = scanner.nextDouble();
					  
					  payrollDAO.generatePayroll(employeeId, baseSalary, overtimePay, deductions);
					} catch (PayrollGenerationException e) {
			            System.out.println("Error generating the Payroll");
					}
                  break;
              case 2:
                  System.out.print("Enter payroll ID: ");
                  int payrollId = scanner.nextInt();
                  Payroll payrollById = payrollDAO.getPayrollById(payrollId);
                  System.out.println("Payroll: " + payrollById);
                  break;
              case 3:
                  System.out.print("Enter employee ID: ");
                  int employeeIdForPayrolls = scanner.nextInt();
                  List<Payroll> payrollsForEmployee = payrollDAO.getPayrollsForEmployee(employeeIdForPayrolls);
                  System.out.println("\n---Payrolls for Employee---\n" + payrollsForEmployee);
                  break;
              case 4:
                  System.out.print("Enter start date (yyyy-mm-dd): ");
                  LocalDate startDateForPeriod = LocalDate.parse(scanner.nextLine());
                  System.out.print("Enter end date (yyyy-mm-dd): ");
                  LocalDate endDateForPeriod = LocalDate.parse(scanner.nextLine());
                  List<Payroll> payrollsForPeriod = payrollDAO.getPayrollsForPeriod(startDateForPeriod, endDateForPeriod);
                  System.out.println("Payrolls for Period: " + payrollsForPeriod);
                  break;
              case 0:
                  System.out.println("Returning to Main Menu...");
                  Main.displayMenu(scanner);
                  break;
              default:
                  System.out.println("Invalid option. Please try again.");
                  break;
      		}
      	} while (payrollOption != 0);

		payrollDAO.callCloseCon();
	
	}

}
