-- ====================================================================
-- Exercise 1: Control Structures
-- Description: PL/SQL scripts for bank customer management.
-- ====================================================================

-- --------------------------------------------------------------------
-- SETUP: Tables and Sample Data
-- --------------------------------------------------------------------

-- Drop tables if they already exist to ensure a clean run
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Create Customers Table
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    DOB DATE NOT NULL,
    Balance NUMBER NOT NULL,
    IsVIP VARCHAR2(5) DEFAULT 'FALSE'
);

-- Create Loans Table
CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER REFERENCES Customers(CustomerID),
    InterestRate NUMBER NOT NULL,
    DueDate DATE NOT NULL,
    Amount NUMBER NOT NULL
);

-- Insert Sample Customers
-- Note: SYSDATE is used to define DOB and test dates dynamically
INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP) VALUES
(1, 'Alice Smith', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 12000, 'FALSE'); -- Age > 60, Balance > 10,000

INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP) VALUES
(2, 'Bob Jones', TO_DATE('1980-08-20', 'YYYY-MM-DD'), 8500, 'FALSE');   -- Age <= 60, Balance < 10,000

INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP) VALUES
(3, 'Charlie Brown', TO_DATE('1960-12-10', 'YYYY-MM-DD'), 15000, 'FALSE'); -- Age > 60, Balance > 10,000

INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP) VALUES
(4, 'Diana Prince', TO_DATE('1995-02-28', 'YYYY-MM-DD'), 4500, 'FALSE');  -- Age <= 60, Balance < 10,000

-- Insert Sample Loans
-- Note: Due dates are configured relative to SYSDATE for Scenario 3
INSERT INTO Loans (LoanID, CustomerID, InterestRate, DueDate, Amount) VALUES
(101, 1, 7.5, SYSDATE + 15, 50000);   -- Due in 15 days (Within 30 days)

INSERT INTO Loans (LoanID, CustomerID, InterestRate, DueDate, Amount) VALUES
(102, 2, 8.0, SYSDATE + 45, 30000);   -- Due in 45 days (Outside 30 days)

INSERT INTO Loans (LoanID, CustomerID, InterestRate, DueDate, Amount) VALUES
(103, 3, 6.5, SYSDATE + 20, 25000);   -- Due in 20 days (Within 30 days)

COMMIT;

-- --------------------------------------------------------------------
-- Scenario 1: Apply 1% discount to loan interest rates for customers over 60.
-- --------------------------------------------------------------------
DECLARE
    -- Cursor to fetch customers older than 60
    CURSOR c_senior_customers IS
        SELECT CustomerID, DOB
        FROM Customers
        WHERE MONTHS_BETWEEN(SYSDATE, DOB) / 12 > 60;
        
    v_customer_id Customers.CustomerID%TYPE;
    v_dob         Customers.DOB%TYPE;
    v_age         NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- SCENARIO 1: Senior Citizen Loan Discount Process ---');
    
    OPEN c_senior_customers;
    LOOP
        FETCH c_senior_customers INTO v_customer_id, v_dob;
        EXIT WHEN c_senior_customers%NOTFOUND;
        
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, v_dob) / 12);
        
        -- Apply a 1% discount to all loans belonging to this customer
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE CustomerID = v_customer_id;
        
        DBMS_OUTPUT.PUT_LINE('Applied 1% interest rate discount to Customer ID: ' || v_customer_id || ' (Age: ' || v_age || ')');
    END LOOP;
    CLOSE c_senior_customers;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 1 updates committed successfully.');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

-- --------------------------------------------------------------------
-- Scenario 2: Promote customers to VIP status based on balance > $10,000.
-- --------------------------------------------------------------------
DECLARE
    -- Cursor to fetch customers with balance over 10,000 who are not already VIP
    CURSOR c_potential_vips IS
        SELECT CustomerID, Name, Balance
        FROM Customers
        WHERE Balance > 10000 AND IsVIP = 'FALSE';
        
    v_cust_id   Customers.CustomerID%TYPE;
    v_name      Customers.Name%TYPE;
    v_balance   Customers.Balance%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- SCENARIO 2: VIP Status Promotion Process ---');
    
    OPEN c_potential_vips;
    LOOP
        FETCH c_potential_vips INTO v_cust_id, v_name, v_balance;
        EXIT WHEN c_potential_vips%NOTFOUND;
        
        -- Update the customer's status to VIP
        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = v_cust_id;
        
        DBMS_OUTPUT.PUT_LINE('Promoted ' || v_name || ' (ID: ' || v_cust_id || ', Balance: $' || v_balance || ') to VIP status.');
    END LOOP;
    CLOSE c_potential_vips;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 2 updates committed successfully.');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

-- --------------------------------------------------------------------
-- Scenario 3: Send reminders to customers whose loans are due in next 30 days.
-- --------------------------------------------------------------------
DECLARE
    -- Cursor to fetch loans due within next 30 days
    CURSOR c_due_loans IS
        SELECT l.LoanID, c.Name, l.DueDate, l.Amount
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30;
        
    v_loan_id   Loans.LoanID%TYPE;
    v_name      Customers.Name%TYPE;
    v_due_date  Loans.DueDate%TYPE;
    v_amount    Loans.Amount%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- SCENARIO 3: Loan Due Reminders ---');
    
    OPEN c_due_loans;
    LOOP
        FETCH c_due_loans INTO v_loan_id, v_name, v_due_date, v_amount;
        EXIT WHEN c_due_loans%NOTFOUND;
        
        -- Print a reminder message for the customer
        DBMS_OUTPUT.PUT_LINE('REMINDER: Hello ' || v_name || ', your loan #' || v_loan_id || 
                             ' of $' || v_amount || ' is due on ' || TO_CHAR(v_due_date, 'YYYY-MM-DD') || 
                             '. Please arrange for the payment.');
    END LOOP;
    CLOSE c_due_loans;
    
    DBMS_OUTPUT.PUT_LINE('Scenario 3 reminder generation complete.');
    DBMS_OUTPUT.PUT_LINE('==================================================');
END;
/
