package com.revature.controllers;

import java.util.Scanner;

import com.revature.utils.ControllerUtil;

/**
 * Employees of the bank should be able to view the
 * customer's login account and banking account(s)
 *
 * Employees of the bank should be able to approve/deny 
 * open applications for accounts. 
 */
public class TellerController extends ControllerUtil{

	@Override
	public void menu() {
		Scanner scan = createScanner();
		boolean running = true;

		do { 
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Approve/Deny an Application");
			System.out.println("2: View Customer Profiles");
			System.out.println("3: View Bank Account");
			System.out.println("Enter any key that's not 1-3 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					activateAccount();
					break;
				case 2:
					viewProfiles();
					break;
				case 3:
					viewAccounts();
					break;
				default:
					System.out.println("Exited teller interface");
					running = false;
			}	
		} while (running);

		running = true;
	}
}
