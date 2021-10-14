-- Create a stored procedure
CREATE PROCEDURE CreateTable
AS

CREATE TABLE customers (
	customer_id VARCHAR(500) PRIMARY KEY,
	customer_name VARCHAR(500),
	customer_password VARCHAR(500)
)

CREATE TABLE accounts (
	account_id VARCHAR(500) PRIMARY KEY,
	account_balance FLOAT CHECK (account_balance >= 0),
	account_active BOOLEAN,
	customer_id VARCHAR(500) REFERENCES customers(customer_id)
)

-- if too much money is deposited at once
-- account gets added to the naughty list
CREATE TABLE audit_list (
	audit_id VARCHAR(500) PRIMARY KEY,
	customer_id VARCHAR(500),
	account_id VARCHAR(500) REFERENCES accounts(account_id)
)
GO;

INSERT INTO customers (customer_id, customer_name, customer_password)
	VALUES('12345','bob','racecar');

INSERT INTO customers (customer_id, customer_name, customer_password)
	VALUES('56432','tim','ssap');

INSERT INTO customers (customer_id, customer_name, customer_password)
	VALUES('10101','sally','god');

INSERT INTO accounts (account_id, account_balance, account_active, customer_id)
	VALUES('65231','25.50','false','56432');

INSERT INTO accounts (account_id, account_balance, account_active, customer_id)
	VALUES('16231','50.22','false','56432');

INSERT INTO accounts (account_id, account_balance, account_active, customer_id)
	VALUES('61231','70.50','false','12345');

INSERT INTO accounts (account_id, account_balance, account_active, customer_id)
	VALUES('18231','22.22','false','10101');

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;

TRUNCATE TABLE accounts CASCADE;
TRUNCATE TABLE customers CASCADE;