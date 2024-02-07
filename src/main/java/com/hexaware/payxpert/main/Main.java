package com.hexaware.payxpert.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.hexaware.payxpert.Constants;
import com.hexaware.payxpert.controller.EmployeeController;
import com.hexaware.payxpert.controller.FinancialRecordController;
import com.hexaware.payxpert.controller.PayrollController;
import com.hexaware.payxpert.controller.TaxController;
import com.hexaware.payxpert.exception.DatabaseConnectionException;

/**
 * @author Pratham Bhoite
 * @since 2024-01-12
 * @version 1.0
 */

public class Main {
	static int option;
	static int spacesCount = (150 - "Welcome to PayXpert 🚀".length()) / 2;
	static String spaces = " ".repeat(spacesCount);
	static int spacesCount2 = (150 - "Your own Payroll Management System! 🌟".length()) / 2;
	static String spaces2 = " ".repeat(spacesCount2);
	
	public static void main(String[] args) {
		System.out.println();
		System.out.println(spaces + Constants.GREEN + "Welcome to PayXpert 🚀" + spaces);
		System.out.println(spaces2 + "Your own Payroll Management System! 🌟\n" + Constants.RESET + spaces2);
		System.out.println("-".repeat(145));
		
        Scanner scanner = new Scanner(System.in);        
        do { 
        	try {
				displayMenu(scanner);
			} catch (DatabaseConnectionException e) {
	            System.out.println(e.getMessage());
	        } catch (InputMismatchException e) {
				 System.out.println(Constants.RED + "⚠Error! " + Constants.RESET 
						+ "Invalid input."
				 		+ "\nPlease enter a valid integer.\n"
				 		+ "-".repeat(145));
	             scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
	             option = -1;
			}
        } while(option != 0);
	}
	
	/**
	 * This method is the Main Menu which displays all the functions which can be performed in this application
	 * @param scanner to reduce code redundancy we are creating scanner instance only once and passing it to every method which requires input
	 */
    public static void displayMenu(Scanner scanner){
		EmployeeController empCtrl = new EmployeeController(); 
		PayrollController payCtrl = new PayrollController(); 
		TaxController taxCtrl = new TaxController();
		FinancialRecordController finCtrl = new FinancialRecordController();
		
		System.out.println("1. Employee Management");
		System.out.println("2. Payroll Management");
		System.out.println("3. Tax Management");
		System.out.println("4. Financial Records");
		System.out.println("0. Exit");
		System.out.print("Choose an option: ");
		
	    option = scanner.nextInt();
	    scanner.nextLine(); // Consume newline
	
	    switch (option) {
	        case 1:
	            empCtrl.employeeOperations(scanner);
	            break;
	        case 2:
	            payCtrl.payrollOperations(scanner);
	            break;
	        case 3:
	            taxCtrl.taxOperations(scanner);
	            break;    
	        case 4:
	        	finCtrl.financialRecordOperations(scanner);
	        	break;
	        case 0:
	            System.out.println("Exiting...");
	            System.out.println(spaces + Constants.CYAN + "Thanks for using PayXpert!" + spaces);
	            break;
	        default:
	            System.out.println(Constants.YELLOW + "Invalid option. Please try again." + Constants.RESET);
	            break;
	    }   
    } 
}



