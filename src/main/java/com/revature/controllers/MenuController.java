package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

/**
 * Customers should be able to register with a user name and password, 
 * and appy to open an account
 * 
 * Once the account has been approved, customers should be able to 
 * withdraw/deposit/transfer funds between their accounts
 */
public abstract class MenuController {

	private static CustomerService customerService = new CustomerService();
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

	public abstract void mainMenu();
	
	/**
	 * Helper method to create a Scanner object
	 * @return ScannerObject
	 */
	public Scanner createScanner(){
		Scanner scan = new Scanner(System.in);
		
		return scan;
	}
}
