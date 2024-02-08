USE PayXpert;

-- Insert values into Employee Table
INSERT INTO Employee (First_Name, Last_Name, Date_Of_Birth, Gender, Email, Phone_Number, Address, Position, Joining_Date, Termination_Date)
VALUES
    ('Bhupendra', 'Jogi!', '1988-07-18', 'M', 'naam.bataiye@example.com', '8765432101', 'Bangalore, India', 'Marketing Executive', '2021-02-10', '2025-02-03'),
    ('Cazual', 'Gupta', '1995-04-25', 'M', 'likhne.ka.tareeka.@thoda_cazual_hai.com', '7654321092', 'Chennai, India', 'Sales Manager', '2020-11-05', NULL),
    ('Jaya', 'Bacchan', '1980-12-12', 'F', 'rekha.ki@maki.com', '6543210983', 'Kolkata, India', 'Senior Developer', '2021-09-15', '2023-01-31'),
    ('Rekha', 'Just Rekha', '1992-03-08', 'M', 'jaya.ki@maki.com', '5432109874', 'Hyderabad, India', 'HR Coordinator', '2022-03-20', NULL),
    ('Amitabh', 'Bacchan', '1987-06-30', 'M', 'jaya_rekha.dono_ki@maki.com', '4321098765', 'Mumbai, India', 'Financial Analyst', '2021-06-01', NULL),
    ('Abhishek', 'Bacchan', '1991-10-14', 'M', 'papa.kehte_hai@bada_naam_karega.com', '3210987654', 'Delhi, India', 'Project Manager', '2020-08-12', NULL),
    ('Sarika', 'Rao', '1983-02-19', 'F', 'sarika.rao@example.com', '2109876543', 'Chandigarh, India', 'Senior Consultant', '2020-05-25', NULL),
    ('Arun', 'Joshi', '1990-09-03', 'M', 'arun.joshi@example.com', '1098765432', 'Pune, India', 'Software Architect', '2022-02-28', NULL),
    ('Neha', 'Malhotra', '1986-11-28', 'F', 'neha.malhotra@example.com', '9876543210', 'Jaipur, India', 'Product Manager', '2021-01-15', '2023-03-15'),
    ('Ankit', 'Shah', '1993-08-05', 'M', 'ankit.shah@example.com', '8765432109', 'Ahmedabad, India', 'QA Engineer', '2020-07-10', NULL);
    

-- Insert values into Payroll Table
INSERT INTO Payroll (Employee_ID, Basic_Salary, Overtime_Pay, Deductions)
VALUES
    (103, 90000, 2530, 4600),
    (103, 803000, 2200, 4600),
    (103, 200000, 2508, 4600),
    (103, 801000, 4500, 4600),
    (103, 1000000, 3006, 4600),
    (103, 304000, 1080, 4600),
    (103, 902900, 2502, 4600);
  