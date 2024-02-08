USE PayXpert;

-- DROP TRIGGER update_pay_end_dates;
-- Trigger to insert pay_dates in Payroll Table
DELIMITER //
CREATE TRIGGER update_pay_end_dates
BEFORE INSERT ON Payroll
FOR EACH ROW
BEGIN
DECLARE last_end_date DATE;

	SELECT (MAX(Pay_Period_End_Date)) INTO last_end_date
    FROM Payroll
    WHERE Employee_ID = NEW.Employee_ID;
    
    IF last_end_date IS NULL THEN
        SET NEW.Pay_Period_Start_Date = (SELECT Joining_Date FROM Employee WHERE Employee_ID = NEW.Employee_ID);
        SET NEW.Pay_Period_End_Date = NEW.Pay_Period_Start_Date + INTERVAL 30 DAY;
    ELSE
		SET NEW.Pay_Period_Start_Date = last_end_date + INTERVAL 1 DAY;
		SET NEW.Pay_Period_End_Date = NEW.Pay_Period_Start_Date + INTERVAL 30 DAY;
    END IF;
END;    
//
DELIMITER ;


-- Trigger to update Taxable Income
DELIMITER //
CREATE TRIGGER update_taxable_income
BEFORE INSERT ON Tax
FOR EACH ROW
BEGIN
    DECLARE v_taxable_income DECIMAL(10, 2);
    DECLARE pay_year INT;
    
-- Calculating the cumulative salary for every year
	SELECT SUM(Net_Salary) INTO v_taxable_income
    FROM Payroll P
    WHERE P.Employee_ID = NEW.Employee_ID AND
    YEAR (P.Pay_Period_Start_Date) = NEW.Tax_Year
    GROUP BY P.Employee_ID, NEW.Tax_Year;
    
    
-- Update Tax table based on cumulative Net Salary
	IF v_taxable_income < 500000 THEN
		SET NEW.Taxable_Income = 0;  
        SET NEW.Tax_Amount = 0;
    ELSE
        SET 
            NEW.Taxable_Income = v_taxable_income - 500000,
            NEW.Tax_Amount = 0.05 * (v_taxable_income - 500000);
    END IF;
END;
//
DELIMITER ;

/* 
	Notes:
		First of all we'll have to use BEFORE INSERT in this case as we are inserting and updating the Tax table
        at the same time and when we use BEFORE INSERT we can directly use SET NEW. ... inteasd of using UPDATE Tax 
        coz now we r not updating the table infact we r inserting new values to it in real-time
*/

 
-- Trigger to Insert Values in Financial_Records
DELIMITER //
CREATE TRIGGER update_financial_records
AFTER INSERT ON Payroll
FOR EACH ROW
BEGIN
	INSERT INTO Financial_Record (Employee_ID, Record_Date, Description, Amount, Record_Type)
    VALUES (NEW.Employee_ID, NEW.Pay_Period_End_Date, 'Base Salary Payment', NEW.Basic_Salary, 'Credit'),
           (NEW.Employee_ID, NEW.Pay_Period_End_Date, 'Overtime Payment', NEW.Overtime_Pay, 'Credit'),
           (NEW.Employee_ID, NEW.Pay_Period_End_Date, 'Loan/ Prof. Tax/ EPF Deductions', NEW.Deductions, 'Debit');
END;
//
DELIMITER ;


