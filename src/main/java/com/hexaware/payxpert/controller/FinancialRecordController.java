package com.hexaware.payxpert.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.Constants;
import com.hexaware.payxpert.dao.FinancialRecordDAO;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.FinancialRecord;


public class FinancialRecordController {

	FinancialRecordDAO financialRecordDAO = new FinancialRecordDAO();
	/**
	 * It displays the Financial Records Menu and operations which can be performed in it
	 * @param scanner
	 */
	public void financialRecordOperations(Scanner scanner) {
		//Initializing the DB connection to carry out Operations
		financialRecordDAO.getConn();
        int financialRecordOption;
        do {
            System.out.println(Constants.CYAN + "\n---Financial Records Menu---" + Constants.RESET);
            System.out.println("1. Add Financial Record");
            System.out.println("2. Get Financial Record by ID");
            System.out.println("3. Get Financial Records for Employee");
            System.out.println("4. Get Financial Records for Date");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            financialRecordOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (financialRecordOption) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int employeeId = scanner.nextInt();
                    System.out.print("Enter transaction date (yyyy-mm-dd): ");
                    LocalDate transactionDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    FinancialRecord financialRecord = new FinancialRecord(employeeId, transactionDate, description, amount);
                    financialRecordDAO.addFinancialRecord(financialRecord);
                    System.out.println(Constants.GREEN 
                    				+ "\nFinancial Record added successfully" 
                    				+ Constants.RESET);
                    break;
                    
                case 2:
                    System.out.print("Enter financial record ID: ");
                    int financialRecordId = scanner.nextInt();
                    FinancialRecord financialRecordById = financialRecordDAO.getFinancialRecordById(financialRecordId);
                    System.out.println(Constants.GREEN + "\n---Financial Records---" + Constants.RESET 
                    				  + financialRecordById);
                    break;
                case 3:
                    System.out.print("Enter employee ID: ");
                    int employeeIdForFinancialRecords = scanner.nextInt();
                    List<FinancialRecord> financialRecordsForEmployee = financialRecordDAO.getFinancialRecordsForEmployee(employeeIdForFinancialRecords);
                    System.out.println(Constants.GREEN + "\n---Financial Records for Employee---" + Constants.RESET
                    				  + financialRecordsForEmployee);
                    break;
                case 4:
                    System.out.print("Enter transaction date (yyyy-mm-dd): ");
                    LocalDate dateForFinancialRecords = LocalDate.parse(scanner.nextLine());
                    List<FinancialRecord> financialRecordsForDate = financialRecordDAO.getFinancialRecordsForDate(dateForFinancialRecords);
                    System.out.println(Constants.GREEN + "\n---Financial Records for given Date---" + Constants.RESET 
                    				  + financialRecordsForDate);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...\n");
                    Main.displayMenu(scanner);
                    break;
                default:
                    System.out.println(Constants.YELLOW + "Invalid option. Please try again." + Constants.RESET);
                    break;
        	}
    	} while (financialRecordOption != 0);
	}
}
