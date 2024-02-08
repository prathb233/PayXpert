CREATE DATABASE PayXpert;
USE PayXpert;
-- DROP DATABASE payxpert;

-- Employee Table
CREATE TABLE Employee (
    Employee_ID INT AUTO_INCREMENT PRIMARY KEY,
    First_Name VARCHAR(255),
    Last_Name VARCHAR(255),
    Date_Of_Birth DATE,
    Gender ENUM('M', 'F'),
    Email VARCHAR(255),
    Phone_Number VARCHAR(20),
    Address VARCHAR(255),
    Position VARCHAR(255),
    Joining_Date DATE,
    Termination_Date DATE DEFAULT NULL,
    UNIQUE KEY unique_emp (Employee_ID, Phone_Number, Email)
)	AUTO_INCREMENT = 101;


-- Payroll Table
CREATE TABLE Payroll (
    Payroll_ID INT AUTO_INCREMENT PRIMARY KEY,
    Employee_ID INT,
    Pay_Period_Start_Date DATE,
    Pay_Period_End_Date DATE,
    Basic_Salary DECIMAL(10, 2),
    Overtime_Pay DECIMAL(10, 2),
    Gross_Salary DECIMAL(10, 2) GENERATED ALWAYS AS (Basic_Salary + Overtime_Pay) STORED,
    Deductions DECIMAL(10, 2),
    Net_Salary DECIMAL(10, 2) GENERATED ALWAYS AS (Gross_Salary - Deductions) STORED,
    FOREIGN KEY (Employee_ID) REFERENCES Employee(Employee_ID),
    UNIQUE KEY unique_payroll_id (Employee_id, Pay_Period_Start_Date)
)	AUTO_INCREMENT = 1001;


-- Tax Table
CREATE TABLE Tax (
    Tax_ID INT AUTO_INCREMENT PRIMARY KEY,
    Employee_ID INT,
    Tax_Year INT,
    Taxable_Income DECIMAL(10, 2),
    Tax_Amount DECIMAL(10, 2),
    FOREIGN KEY (Employee_ID) REFERENCES Employee(Employee_ID),
    UNIQUE KEY unique_tax (Employee_ID, Tax_Year)
)	AUTO_INCREMENT = 4758;


-- FinancialRecord Table
CREATE TABLE Financial_Record (
    Record_ID INT AUTO_INCREMENT PRIMARY KEY,
    Employee_ID INT,
    Record_Date DATE,
    Description VARCHAR(255),
    Amount DECIMAL(10, 2) DEFAULT 0.00,
    Record_Type ENUM('Credit', 'Debit'),
    FOREIGN KEY (Employee_ID) REFERENCES Employee(Employee_ID)
)	AUTO_INCREMENT = 1;


