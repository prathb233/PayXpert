USE PayXpert;

-- Trigger to insert pay_dates in Payroll Table
DELIMITER //
CREATE TRIGGER update_pay_dates
BEFORE INSERT ON Payroll
FOR EACH ROW
BEGIN
DECLARE last_end_date DATE;
	SELECT (MAX(Pay_Period_End_Date)) INTO last_end_date
    FROM Payroll
    WHERE Employee_ID = NEW.Employee_ID;
    
    IF last_end_date IS NULL THEN
        SET NEW.Pay_Period_Start_Date = (SELECT Joining_Date FROM Employee WHERE Employee_ID = NEW.Employee_ID);
        -- SET NEW.Pay_Period_End_Date = NEW.Pay_Period_Start_Date + INTERVAL 30 DAY;
    ELSE
		SET NEW.Pay_Period_Start_Date = last_end_date + INTERVAL 1 DAY;
    END IF;
	SET NEW.Pay_Period_End_Date = NEW.Pay_Period_Start_Date + INTERVAL 30 DAY;
END;    
//
DELIMITER ;


-- Trigger to update Taxable Income
DELIMITER //
CREATE TRIGGER update_taxable_income
AFTER INSERT ON Payroll
FOR EACH ROW
BEGIN
    DECLARE v_taxable_income DECIMAL(10, 2);
	DECLARE entry_exists INT;
    
    -- Check if there's an existing entry for the given Employee_ID and Tax_Year
    SELECT COUNT(*) INTO entry_exists
    FROM Tax
    WHERE Employee_ID = NEW.Employee_ID AND Tax_Year = YEAR(NEW.Pay_Period_Start_Date);

    -- Calculating the cumulative salary for every year
    SELECT SUM(Net_Salary) INTO v_taxable_income
    FROM Payroll P
    WHERE P.Employee_ID = NEW.Employee_ID AND
    YEAR(P.Pay_Period_Start_Date) = YEAR(NEW.Pay_Period_Start_Date)
    GROUP BY P.Employee_ID, YEAR(NEW.Pay_Period_Start_Date);

    -- Update or insert into Tax table based on cumulative Net Salary
    IF entry_exists > 0 THEN
        -- Entry already exists, update it
        UPDATE Tax T
        SET Taxable_Income = CASE 
								WHEN v_taxable_income < 500000 THEN 0 
								ELSE v_taxable_income - 500000 
							 END,
                                
                Tax_Amount = CASE 
								WHEN v_taxable_income < 500000 THEN 0 
								ELSE 0.05 * (v_taxable_income - 500000) 
							 END
        WHERE T.Employee_ID = NEW.Employee_ID AND Tax_Year = YEAR(NEW.Pay_Period_Start_Date);
    ELSE
        -- Entry doesn't exist, insert new data
        INSERT INTO Tax (Employee_ID, Tax_Year, Taxable_Income, Tax_Amount)
        VALUES (NEW.Employee_ID, YEAR(NEW.Pay_Period_Start_Date), 
                CASE WHEN v_taxable_income < 500000 THEN 0 ELSE v_taxable_income - 500000 END,
                CASE WHEN v_taxable_income < 500000 THEN 0 ELSE 0.05 * (v_taxable_income - 500000) END);
    END IF;
END;
//
DELIMITER ;
-- TRUNCATE TABLE Tax; //for testing!

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


