package com.hexaware.payxpert.controller;

import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.dao.TaxDAO;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.Tax;

public class TaxController {
	
	TaxDAO taxDAO = new TaxDAO();
		
	public void taxOperations(Scanner scanner) {
	//Initializing the DB Conn b4 carring out Operations
	taxDAO.getConn();
	  int taxOption;
      do {
          System.out.println("\n---Tax Menu---");
          System.out.println("1. Calculate Tax");
          System.out.println("2. Get Tax by ID");
          System.out.println("3. Get Taxes for Employee");
          System.out.println("4. Get Taxes for Year");
          System.out.println("0. Back to Main Menu");
          System.out.print("Choose an option: ");

          taxOption = scanner.nextInt();
          scanner.nextLine(); // Consume newline

          switch (taxOption) {
              case 1:
                  System.out.print("Enter employee ID: ");
                  int employeeId = scanner.nextInt();
                  //System.out.print("Enter the Tax Year (yyyy): ");
                  //int taxYear = scanner.nextInt();
                  taxDAO.getTaxById(employeeId);
                  System.out.println("Tax calculated successfully (not implemented)");
                  break;
              case 2:
                  System.out.print("Enter Tax ID: ");
                  int taxId = scanner.nextInt();
                  Tax taxById = taxDAO.getTaxById(taxId);
                  System.out.println("Tax details: " + taxById);
                  break;
              case 3:
                  System.out.print("Enter employee ID: ");
                  int employeeIdForTaxes = scanner.nextInt();
                  List<Tax> taxesForEmployee = taxDAO.getTaxesForEmployee(employeeIdForTaxes);
                  System.out.println("Taxes for Employee: " + taxesForEmployee);
                  break;
              case 4:
                  System.out.print("Enter year: ");
                  int yearForTaxes = scanner.nextInt();
                  List<Tax> taxesForYear = taxDAO.getTaxesForYear(yearForTaxes);
                  System.out.println("Taxes for Year: " + taxesForYear);
                  break;
              case 0:
                  System.out.println("Returning to Main Menu...");
                  Main.displayMenu(scanner);
                  break;
              default:
                  System.out.println("Invalid option. Please try again.");
                  break;
          	}
      	} while (taxOption != 0);
	}
}
