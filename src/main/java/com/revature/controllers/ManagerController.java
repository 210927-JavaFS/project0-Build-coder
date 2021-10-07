package com.revature.controllers;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Customer;

/**
 * Employees of the bank should be able to view the
 * customer's login account and banking account(s)
 *
 * Employees of the bank should be able to approve/deny 
 * open applications for accounts. 
 * 
 * Bank admins should be able to withdraw/deposit/transfer
 * Cancel accounts 
 */
public class ManagerController extends UtilityController {

	@Override
	public void menu(String name, ArrayList<Account> bankAccounts, ArrayList<Customer> profiles) {
		scan = createScanner();
		boolean running = true;

		do { 
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Approve/Deny an Application");
			System.out.println("2: View Customer Account");
			System.out.println("3: View Bank Account");
			System.out.println("4: Deposit");
			System.out.println("5: Withdraw");
			System.out.println("6: Transfer");
			System.out.println("Enter any key that's not 1-3 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					// first account in the list hard coded is for now
					approveAccount(bankAccounts.get(0));
					break;
				case 2:
					viewProfiles(profiles);
					break;
				case 3:
					viewAccounts(bankAccounts);
					break;
				case 4:
					deposit(bankAccounts);
					break;
				case 5:
					withdraw(bankAccounts);
					break;
				case 6:
					transfer(bankAccounts);
					break;
				default:
					System.out.println("Exited manager interface");
					running = false;
			}	
		} while (running);

		running = true;
	}
}
