package com.revature.controllers;

// temporarily using Array's in this class
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.services.CustomerService;
import com.revature.utils.ControllerUtil;
	
/**
 * Customers should be able to register with a user name and password, 
 * and appy to open an account
 * 
 * Once the account has been approved, customers should be able to 
 * withdraw/deposit/transfer funds between their accounts
 */
public class CustomerController extends ControllerUtil {

	private static CustomerService customerService = new CustomerService();
	private static boolean running = true;
	Scanner scan = createScanner();

    @Override
	public void menu(){
		do { 
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Create a bank account");
			System.out.println("2: Deposit");
			System.out.println("3: Withdraw");
			System.out.println("4: Transfer");
			System.out.println("5: View Accounts");
			System.out.println("Enter any key that's not 1-5 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				// case 1:
				// 	String name = getUserName(bankAccounts, userID);
				// 	Account account = null;
				// 	account = buildBankAccount(name, account);
				// 	accountService.addToList(account, bankAccounts);
				// 	accountService.save(account);
                //     System.out.println("Congrats " + name + "! You opened a new bank account");
				// 	break;
				// case 2:
				// 	deposit(bankAccounts);
				// 	break;
				// case 3:
				// 	withdraw(bankAccounts);
				// 	break;
				// case 4:
				// 	transfer(bankAccounts);
				// 	break;
				case 5:
					// class example
					// viewProfiles();
					viewAccounts();
					break;
	
				default:
					System.out.println("Exited customer interface");
					running = false;
			}	
		} while (running);

		running = true;
	}
	
	public Account buildBankAccount(String name, Account account) {

		String accountID = UUID.randomUUID().toString();
		int balance = 0;
		boolean active = false;
		account=accountService.createAccount(name,accountID,balance, active);

		return account;
	}
}
