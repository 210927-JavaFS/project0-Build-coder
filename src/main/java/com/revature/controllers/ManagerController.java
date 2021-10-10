package com.revature.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.utils.ControllerUtil;

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
public class ManagerController extends ControllerUtil {

	@Override
	public void menu() {
		Scanner scan = createScanner();
		boolean running = true;

		do { 
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Approve/Deny an Application");
			System.out.println("2: View Customer Pofiles");
			System.out.println("3: View Bank Account");
			System.out.println("4: Deposit");
			System.out.println("5: Withdraw");
			System.out.println("6: Transfer");
			System.out.println("7: Cancel Bank Account");
			System.out.println("Enter any key that's not 1-7 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				// case 1:
				// 	// first account in the list hard coded is for now
				// 	approveAccount(bankAccounts.get(0));
				// 	break;
				case 2:
					viewProfiles();
					break;
				case 3:
					viewAccounts();
					break;
				case 4:
					deposit();
					break;
				case 5:
					withdraw();
					break;
				case 6:
					transfer();
					break;
				case 7:
					// remove bank account
					cancelAccount();
					break;
				// case 8:
					/**
					 * Not sure if I need this functionality
					 * Might have to set the account_id to null
					 */
					// cancelProfile();
					// break;

				default:
					System.out.println("Exited manager interface");
					running = false;
			}	
		} while (running);

		running = true;
	}
}
