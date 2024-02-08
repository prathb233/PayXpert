package com.hexaware.payxpert.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.Constants;
import com.hexaware.payxpert.dao.EmployeeDAO;
import com.hexaware.payxpert.exception.EmployeeNotFoundException;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.Employee;

public class EmployeeController {
	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
	/**
	 * It displays the Employee Menu and operations which can be performed in it
	 * @param scanner
	 */
    public void employeeOperations(Scanner scanner) {
    	try {
    		//Initializing the DB connection to carry out Operations
    		employeeDAO.getConn();
    	    int employeeOption;
    	    do {
    	    System.out.println(Constants.CYAN + "\n---Employee Menu---" + Constants.RESET);
    		System.out.println("1. Get Employee by ID");
    		System.out.println("2. Get All Employees");
    		System.out.println("3. Add Employee");
    		System.out.println("4. Update Employee");
    		System.out.println("5. Remove Employee");
    		System.out.println("0. Back to Main Menu");
    		System.out.print("Choose an option: ");

    		employeeOption = scanner.nextInt();
    		scanner.nextLine(); // Consume newline

    		switch (employeeOption) {
    		case 1:
    		    System.out.print("\nEnter employee ID: ");
    		    int employeeId = scanner.nextInt();
    		    Employee employeeById = employeeDAO.getEmployeeById(employeeId);
    		    System.out.println(
    		    		Constants.GREEN + "\n---Employee Details---\n" + 
    		    		Constants.RESET + employeeById);
    		    break;
    		case 2:
    		    List<Employee> allEmployees = employeeDAO.getAllEmployees();
    		    System.out.println(Constants.GREEN + "\n---All Employees---\n" + Constants.RESET + allEmployees);
    		    break;
    		case 3:
    			System.out.print("\nEnter employee First Name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter employee Last Name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter employee Date of Birth (YYYY-MM-DD): ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter employee Gender: ");
                String gender = scanner.nextLine();
                System.out.print("Enter employee Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter employee Phone Number: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("Enter employee Address: ");
                String address = scanner.nextLine();
                System.out.print("Enter employee Position: ");
                String position = scanner.nextLine();
                
                System.out.print("Enter employee Joining Date (YYYY-MM-DD): ");
                LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                
                LocalDate terminationDate = null;
                System.out.println("Want to assign Termination Date (Y or N)?");
                String assignTrmDt = scanner.nextLine();
                if("Y".equals(assignTrmDt)) {
                	do {
	                    System.out.print("Enter employee Termination Date (YYYY-MM-DD): ");
	                    terminationDate = LocalDate.parse(scanner.nextLine());
	                	if(terminationDate.isBefore(joiningDate)) {
	                		System.out.println(Constants.YELLOW + "⚠Error! " 
	                				+ "Termination Date should be after the Joining Date\n" 
	                				+ Constants.RESET);
	                	}
                	} while(terminationDate.isBefore(joiningDate));
                }
    
                Employee newEmployee = new Employee(
                        firstName, lastName, dateOfBirth, gender,
                        email, phoneNumber, address, position,
                        joiningDate, terminationDate);

                employeeDAO.addEmployee(newEmployee);
                System.out.println(Constants.GREEN 
                		+ "Employee added successfully!"
                		+ Constants.RESET);
                break;
                
    		case 4:
    			//getting employee to be updated
    			System.out.print("\nEnter employee ID to update: ");
                int updateEmployee = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                //store the details of employee being updated in a new Employee instance
                Employee updatedEmployee = employeeDAO.getEmployeeById(updateEmployee);
                
                System.out.print("Enter new First Name: ");
                updatedEmployee.setFirstName(scanner.nextLine());
                System.out.print("Enter new Last Name: ");
                updatedEmployee.setLastName(scanner.nextLine());
                System.out.print("Enter new Date of Birth (yyyy-mm-dd): ");
                updatedEmployee.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
                System.out.print("Enter new Gender: ");
                updatedEmployee.setGender(scanner.nextLine());
                System.out.print("Enter new Email: ");
                updatedEmployee.setEmail(scanner.nextLine());
                System.out.print("Enter new Phone Number: ");
                updatedEmployee.setPhoneNumber(scanner.nextLine());
                System.out.print("Enter new Address: ");
                updatedEmployee.setAddress(scanner.nextLine());
                System.out.print("Enter new Position: ");
                updatedEmployee.setPosition(scanner.nextLine());
                
                terminationDate = null;
                System.out.println("Want to assign Termination Date (Y or N)?");
                assignTrmDt = scanner.next();
                if("Y".equals(assignTrmDt)) {
                    System.out.print("Enter employee Termination Date (yyyy-mm-dd): ");
                    updatedEmployee.setTerminationDate(LocalDate.parse(scanner.next()));
                }

                employeeDAO.updateEmployee(updatedEmployee);
                System.out.println(Constants.GREEN 
                		+ "Employee with ID: " + updateEmployee + " updated successfully!" 
                		+ Constants.RESET);
                break;
    		case 5:
    		    System.out.print("\nEnter employee ID to remove: ");
    		    int removeEmployeeId = scanner.nextInt();
    		    employeeDAO.removeEmployee(removeEmployeeId);
    		    System.out.println(Constants.GREEN 
    		    		+ "Employee with ID: " + removeEmployeeId + " removed successfully!"  
    		    		+ Constants.RESET);
    		    break;
    		case 0:
                System.out.println("Returning to Main Menu...\n");
    			Main.displayMenu(scanner);
    		    break;
    		default:
    		    System.out.println(Constants.YELLOW + "Invalid option. Please choose correct option" + Constants.RESET);
    		    break;
    		}
    	    } while (employeeOption != 0);
    	    
    	} catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
    	    employeeDAO.callCloseCon();
    	}
    }
}
