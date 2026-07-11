-- ====================================================================
-- Exercise 3: Stored Procedures
-- Description: Database stored procedures for bank operations.
-- ====================================================================

-- --------------------------------------------------------------------
-- SETUP: Tables and Sample Data
-- --------------------------------------------------------------------

-- Drop tables if they already exist
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Accounts';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Employees';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Create Accounts Table
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20) NOT NULL, -- e.g., 'Savings', 'Checking'
    Balance NUMBER NOT NULL,
    LastUpdate DATE DEFAULT SYSDATE
);

-- Create Employees Table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    Department VARCHAR2(50) NOT NULL,
    Salary NUMBER NOT NULL,
    HireDate DATE DEFAULT SYSDATE
);

-- Insert Sample Accounts
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES
(1001, 101, 'Savings', 5000);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES
(1002, 101, 'Checking', 2000);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES
(1003, 102, 'Savings', 12000);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES
(1004, 103, 'Checking', 500);

-- Insert Sample Employees
INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES
(201, 'Alice Green', 'HR', 60000);

INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES
(202, 'Bob White', 'IT', 80000);

INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES
(203, 'Charlie Grey', 'IT', 75000);

INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES
(204, 'Diana Gold', 'Sales', 50000);

COMMIT;

-- --------------------------------------------------------------------
-- Scenario 1: Process Monthly Interest for Savings Accounts
-- --------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    -- Apply an interest rate of 1% to all Savings accounts
    UPDATE Accounts
    SET Balance = Balance * 1.01,
        LastUpdate = SYSDATE
    WHERE AccountType = 'Savings';
    
    DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to all Savings accounts.');
    DBMS_OUTPUT.PUT_LINE('Total accounts updated: ' || SQL%ROWCOUNT);
END;
/

-- --------------------------------------------------------------------
-- Scenario 2: Update Employee Bonus
-- --------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department IN VARCHAR2,
    p_BonusPercentage IN NUMBER
) AS
BEGIN
    -- Validate bonus percentage
    IF p_BonusPercentage < 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Bonus percentage cannot be negative.');
    END IF;

    -- Update salary by adding the bonus percentage
    UPDATE Employees
    SET Salary = Salary * (1 + p_BonusPercentage / 100)
    WHERE Department = p_Department;
    
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_BonusPercentage || '% applied to department: ' || p_Department);
    DBMS_OUTPUT.PUT_LINE('Total employees updated: ' || SQL%ROWCOUNT);
END;
/

-- --------------------------------------------------------------------
-- Scenario 3: Safe Fund Transfer between Accounts
-- --------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID IN NUMBER,
    p_Amount IN NUMBER
) AS
    v_SourceBalance NUMBER;
    v_DestExists    NUMBER;
    insufficient_funds EXCEPTION;
    invalid_amount     EXCEPTION;
    account_not_found  EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Starting transfer of $' || p_Amount || ' from Account ' || p_SourceAccountID || ' to Account ' || p_DestAccountID);

    -- Validate amount
    IF p_Amount <= 0 THEN
        RAISE invalid_amount;
    END IF;

    -- Check if source account exists and fetch balance
    BEGIN
        SELECT Balance INTO v_SourceBalance
        FROM Accounts
        WHERE AccountID = p_SourceAccountID;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20003, 'Source Account ID ' || p_SourceAccountID || ' not found.');
    END;

    -- Check if destination account exists
    SELECT COUNT(*) INTO v_DestExists
    FROM Accounts
    WHERE AccountID = p_DestAccountID;
    
    IF v_DestExists = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Destination Account ID ' || p_DestAccountID || ' not found.');
    END IF;

    -- Check for sufficient balance
    IF v_SourceBalance < p_Amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Perform Debit from source
    UPDATE Accounts
    SET Balance = Balance - p_Amount,
        LastUpdate = SYSDATE
    WHERE AccountID = p_SourceAccountID;

    -- Perform Credit to destination
    UPDATE Accounts
    SET Balance = Balance + p_Amount,
        LastUpdate = SYSDATE
    WHERE AccountID = p_DestAccountID;

    -- Transaction successfully completed
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful! Transaction committed.');

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient funds in source account.');
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in the source account.');
    WHEN invalid_amount THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Invalid transfer amount.');
        RAISE_APPLICATION_ERROR(-20005, 'Transfer amount must be positive.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Unexpected database error.');
        RAISE;
END;
/

-- --------------------------------------------------------------------
-- DEMONSTRATION: Running Stored Procedures
-- --------------------------------------------------------------------
BEGIN
    DBMS_OUTPUT.PUT_LINE('==================================================');
    DBMS_OUTPUT.PUT_LINE('       Stored Procedures Demonstration Run       ');
    DBMS_OUTPUT.PUT_LINE('==================================================');
    
    -- Demo 1: Process Savings Account Monthly Interest
    ProcessMonthlyInterest;
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Demo 2: Update Employee Bonus in IT Department
    UpdateEmployeeBonus('IT', 5);
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Demo 3: Safe Fund Transfer (Successful Case)
    TransferFunds(1001, 1002, 1000);
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Demo 4: Safe Fund Transfer (Failed Case - Insufficient Balance)
    BEGIN
        TransferFunds(1004, 1003, 1000);
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Caught Expected Exception: ' || SQLERRM);
    END;
    DBMS_OUTPUT.PUT_LINE('==================================================');
END;
/
