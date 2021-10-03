package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;

public class MenuController {
	
	private static CustomerService customerService = new CustomerService();
	private static AccountService accountService = new AccountService();
	private static boolean running = true;
	Scanner scan = createScanner();

	public void logIn(){
		do {
			String name, password, id;

			System.out.println("Please enter your name: ");
			name = scan.nextLine();
			System.out.println("Please enter your password: ");
			password = scan.nextLine();
			id = UUID.randomUUID().toString();

			if (!(name.isEmpty() || password.isEmpty())) {
				Customer customer = customerService.createAccount(name,password,id);
				customerService.addToList(customer);
				System.out.println();
				System.out.println("Congrats, you have created a user account");
				running = false;
			} else {
				System.out.println();
				System.out.println("Name and/or password is incomplete. Try again");
				System.out.println();
			}
		} while(running);

		running = true;
	}

	public void mainMenu(){
		do {
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Create a bank account");
			System.out.println("2: Deposit");
			System.out.println("3: Withdraw");
			System.out.println("4: Transfer");
			System.out.println("5: Exit");
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					Scanner scan = createScanner();
					System.out.println("What is your name?");
					String name = scan.nextLine();
					Account account = null;
					account = buildBankAccount(account, name);
					accountService.addToList(account);
					break;
				case 2:
					// find customer account
					// deposit(account);
					// break;
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
	
	public Account buildBankAccount(Account account, String name) {
		String accountID = UUID.randomUUID().toString();
		int balance = 0;
		account=accountService.createAccount(name,accountID,balance);

		return account;
	}

	public void deposit(Account account) {
		scan = createScanner();

		System.out.println("How much do you want to deposit?");
		int amount = scan.nextInt();
		accountService.addDeposit(account, amount);
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
