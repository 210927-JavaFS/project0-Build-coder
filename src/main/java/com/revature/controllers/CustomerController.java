package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.Customer;
import com.revature.services.*;
import com.revature.utils.ControllerUtil;
	
/**
 * Customers should be able to register with a user name and password, 
 * and appy to open an account
 * 
 * Once the account has been approved, customers should be able to 
 * withdraw/deposit/transfer funds between their accounts
 */
public class CustomerController extends ControllerUtil {

	private static boolean running = true;
	private static AccountService accountService = new AccountService();

	Scanner scan = createScanner();

    @Override
	public void menu(){

		String id = logIn();

		if(id != ""){
			do { 
				System.out.println();
				System.out.println("Please choose an option: ");		
				System.out.println("1: Register for a new bank account");
				System.out.println("2: Deposit");
				System.out.println("3: Withdraw");
				System.out.println("4: Transfer");
				System.out.println("5: View Accounts");
				System.out.println("Enter any key that's not 1-5 to quit");
				System.out.println();
				
				int response = scan.nextInt();
				
				switch (response) {
					case 1:
						Customer c;
						c = findByCustomerID(id);
						String accountID = UUID.randomUUID().toString();
						accountID = cutString(accountID);
						accountService.createAccount(accountID, 0, false, c);
						System.out.println("Congrats " + c.getName() + 
						"! You registered for a new bank account");
						break;
					case 2:
						viewCustomersAccounts(id);
						deposit();
						break;
					case 3:
						viewCustomersAccounts(id);
						withdraw();
						break;
					case 4:
						viewCustomersAccounts(id);
						transfer();
						break;
					case 5:
						viewCustomersAccounts(id);
						break;
		
					default:
						System.out.println("Exited customer interface");
						running = false;
				}	
			} while (running);
	
			running = true;

		} else {
			try {
				id = createProfile();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
