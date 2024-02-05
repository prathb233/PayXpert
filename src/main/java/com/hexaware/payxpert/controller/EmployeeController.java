package com.hexaware.payxpert.controller;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.hexaware.payxpert.dao.EmployeeDAO;
import com.hexaware.payxpert.exception.DatabaseConnectionException;
import com.hexaware.payxpert.exception.EmployeeNotFoundException;
import com.hexaware.payxpert.main.Main;
import com.hexaware.payxpert.model.Employee;

public class EmployeeController {
	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
    public void employeeOperations(Scanner scanner) {
    	try {
    		//Initializing the DB Conn b4 carring out Operations
    		employeeDAO.getConn();
    	    int employeeOption;
    	    do {
    	    System.out.println("\n---Employee Menu---\n");
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
    		    System.out.println("\n---Employee Details---\n" + employeeById);
    	        if (employeeById == null) {
    	            throw new EmployeeNotFoundException(employeeId);
    	        }
    		    break;
    		case 2:
    		    List<Employee> allEmployees = employeeDAO.getAllEmployees();
    		    System.out.println("\n---All Employees---\n" + allEmployees);
    		    break;
    		case 3:
    			System.out.print("Enter employee First Name: ");
                String firstName = scanner.next();
                System.out.print("Enter employee Last Name: ");
                String lastName = scanner.next();
                System.out.print("Enter employee Date of Birth (yyyy-mm-dd): ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.next());
                System.out.print("Enter employee Gender: ");
                String gender = scanner.next();
                System.out.print("Enter employee Email: ");
                String email = scanner.next();
                System.out.print("Enter employee Phone Number: ");
                String phoneNumber = scanner.next();
                System.out.print("Enter employee Address: ");
                String address = scanner.next();
                System.out.print("Enter employee Position: ");
                String position = scanner.next();
                System.out.print("Enter employee Joining Date (yyyy-mm-dd): ");
                LocalDate joiningDate = LocalDate.parse(scanner.next());
                
                LocalDate terminationDate = null;
                System.out.println("Want to assign Termination Date (Y or N)?");
                String assignTrmDt = scanner.next();
                if(assignTrmDt == "Y") {
                    System.out.print("Enter employee Termination Date (yyyy-mm-dd): ");
                    terminationDate = LocalDate.parse(scanner.next());
                }
    
                //LocalDate terminationDate = LocalDate.parse(scanner.next());

                Employee newEmployee = new Employee(
                        firstName, lastName, dateOfBirth, gender,
                        email, phoneNumber, address, position,
                        joiningDate, terminationDate);

                employeeDAO.addEmployee(newEmployee);
                System.out.println("Employee added successfully");
                break;
                
    		case 4:
    			System.out.print("Enter employee ID to update: ");
                int updateEmployeeId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Employee updatedEmployee = employeeDAO.getEmployeeById(updateEmployeeId);
                if (updatedEmployee != null) {
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
                    System.out.print("Enter new Joining Date (yyyy-mm-dd): ");
                    updatedEmployee.setJoiningDate(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Enter new Termination Date (yyyy-mm-dd): ");
                    updatedEmployee.setTerminationDate(LocalDate.parse(scanner.nextLine()));

                    employeeDAO.updateEmployee(updatedEmployee);
                    System.out.println("Employee updated successfully");
                } else {
                    System.out.println("Employee not found");
                }
                break;
    		case 5:
    		    System.out.print("Enter employee ID to remove: ");
    		    int removeEmployeeId = scanner.nextInt();
    		    employeeDAO.removeEmployee(removeEmployeeId);
    		    System.out.println("Employee removed successfully");
    		    break;
    		case 0:
                System.out.println("Returning to Main Menu...");
    			Main.displayMenu(scanner);
    		    break;
    		default:
    		    System.out.println("Invalid option. Please try again.");
    		    break;
    		}
    	    } while (employeeOption != 0);
    	    
    	} catch (InputMismatchException e) {
        	System.out.println("Please eneter a valid input");
        } catch (DatabaseConnectionException e) {
            System.out.println("Error connecting to the database. Please check your credentials and try again.");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
    	    employeeDAO.callCloseCon();
    	}
    }
}
