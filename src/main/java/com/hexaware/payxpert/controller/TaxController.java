package com.hexaware.payxpert.controller;

import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.Constants;
import com.hexaware.payxpert.dao.TaxDAO;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.Tax;

public class TaxController {
	
	TaxDAO taxDAO = new TaxDAO();
	/**
	 * It displays the Employee Menu and operations which can be performed in it
	 * @param scanner
	 */
	public void taxOperations(Scanner scanner) {
	//Initializing the DB connection to carryout Operations
	taxDAO.getConn();
	  int taxOption;
      do {
          System.out.println(Constants.CYAN + "\n---Tax Menu---" + Constants.RESET);
          System.out.println("1. Get Tax for a Financial Year");
          System.out.println("2. Get Tax by ID");
          System.out.println("3. Get Taxes for all existing Financial years of an Employee");
          System.out.println("4. Get Taxes for Year");
          System.out.println("0. Back to Main Menu");
          System.out.print("Choose an option: ");

          taxOption = scanner.nextInt();
          scanner.nextLine(); // Consume newline

          switch (taxOption) {
              case 1:
                  System.out.print("Enter employee ID: ");
                  int employeeId = scanner.nextInt();
                  System.out.print("Enter the Tax Year (yyyy): ");
                  int taxYear = scanner.nextInt();
                  Tax taxForYear = taxDAO.getTaxByYear(employeeId, taxYear);
                  System.out.println(Constants.GREEN + "\n---Tax details---" + Constants.RESET + taxForYear); 
                  break;
              case 2:
                  System.out.print("Enter Tax ID: ");
                  int taxId = scanner.nextInt();
                  Tax taxById = taxDAO.getTaxById(taxId);
                  System.out.println(Constants.GREEN + "\n---Tax details---" + Constants.RESET 
                		  			+ taxById);
                  break;
              case 3:
                  System.out.print("Enter employee ID: ");
                  int employeeIdForTaxes = scanner.nextInt();
                  List<Tax> taxesForEmployee = taxDAO.getTaxesForEmployee(employeeIdForTaxes);
                  System.out.println(Constants.GREEN + "\n---Tax details---" + Constants.RESET 
                		  			+ taxesForEmployee);
                  break;
              case 4:
                  System.out.print("Enter year: ");
                  int yearForTaxes = scanner.nextInt();
                  List<Tax> taxesForYear = taxDAO.getTaxesForYear(yearForTaxes);
                  System.out.println(Constants.GREEN + "\n---Tax details---" + Constants.RESET 
                		  			+ taxesForYear);
                  break;
              case 0:
                  System.out.println("Returning to Main Menu...\n");
                  Main.displayMenu(scanner);
                  break;
              default:
                  System.out.println(Constants.YELLOW + "Invalid option. Please try again." + Constants.RESET);
                  break;
          	}
      	} while (taxOption != 0);
	}
}
