package com.hexaware.payxpert.main;

import java.util.Scanner;

import com.hexaware.payxpert.controller.EmployeeController;
import com.hexaware.payxpert.controller.FinancialRecordController;
import com.hexaware.payxpert.controller.PayrollController;
import com.hexaware.payxpert.controller.TaxController;

public class Main {
	static int option;
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do { displayMenu(scanner);
        } while(option != 0);
	}
	
    public static void displayMenu(Scanner scanner){

		EmployeeController empCtrl = new EmployeeController(); 
		PayrollController payCtrl = new PayrollController(); 
		TaxController taxCtrl = new TaxController();
		FinancialRecordController finCtrl = new FinancialRecordController();
			     		
		int spacesCount = (150 - "Welcome to PayXpert 🚀".length()) / 2;
		String spaces = " ".repeat(spacesCount);
		int spacesCount2 = (150 - "Your own Payroll Management System! 🌟💸".length()) / 2;
		String spaces2 = " ".repeat(spacesCount2);
	
		System.out.println();
		System.out.println(spaces + "Welcome to PayXpert ðŸš€" + spaces);
		System.out.println(spaces2 + "Your own Payroll Management System! ðŸŒŸ\n" + spaces2);
		System.out.println("-".repeat(145));
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
	            System.out.println(spaces + "Thanks for using PayXpert!" + spaces);
	            break;
	        default:
	            System.out.println("Invalid option. Please try again.");
	            break;
	    }
    } 
}



