package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.CustomerAccount;
import com.revature.services.CustomerService;

public class MenuController {
	
	private static CustomerService customerService = new CustomerService();
	Scanner scan = createScanner();

	public void mainMenu(){
		boolean running = true;

		do {
			System.out.println("Please choose an option: ");		
			System.out.println("1: Create an account");
			System.out.println("2: Deposit");
			System.out.println("3: Withdraw");
			System.out.println("4: Transfer");
			System.out.println("5: Exit");
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					CustomerAccount account = null;
					buildAccount(account);
					customerService.addToList(account);
					break;
				case 2:
					deposit(account);
					break;
		//		case 3:
		//			withdraw();
		//			break;
		//		case 4:
		//			transfer();
		//			break;
		
				default:
					System.out.println("Thank you, have a good day!");
					running = false;
			}	
		} while (running);
	}
	
	public void buildAccount(CustomerAccount account) {
		scan = createScanner();

		System.out.println("What is your name?");
		String name = scan.nextLine();
		String accountID = UUID.randomUUID().toString();
		int balance = 0;
		account=customerService.createAccount(name,accountID,balance);
	}

	public void deposit(CustomerAccount account) {
		scan = createScanner();

		System.out.println("How much do you want to deposit?");
		int amount = scan.nextInt();
		customerService.addDeposit(account, amount);
	}

	/**
	 * Helper method to create a Scanner object
	 * @return ScannerObject
	 */
	public Scanner createScanner(){
		Scanner scan = new Scanner(System.in);
		
		return scan;
	}
	
}
