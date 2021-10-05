package com.revature;

import java.util.Scanner;

// temporarily using Array's in this class
import java.util.ArrayList;

import com.revature.controllers.ManagerController;
import com.revature.controllers.TellerController;
import com.revature.controllers.CustomerController;

// temporarily using models in this class
import com.revature.models.*;

public class Driver {

	public static void main(String[] args) {

		// since we have yet to set up a database, I'm gonna
		// store the lists of bank accounts and customer accounts here
		// temporarily
		ArrayList<Customer> customerAccounts = new ArrayList<>();
		ArrayList<Account> bankAccounts = new ArrayList<>();

		boolean running = true;

		System.out.println("Welcome to LegacyBanking, your headache awaits...");

		do { 
			Scanner scan = new Scanner(System.in);

			System.out.println();
			System.out.println("What is your role? Enter the number that corresponds with your role: ");		
			System.out.println("1: Customer");
			System.out.println("2: Teller");
			System.out.println("3: Manager");
			System.out.println("Enter any key that's not 1-3 to exit");
			System.out.println();
			
			int response = scan.nextInt();
			String name;
			
			switch (response) {
				case 1:
					CustomerController customerController = new CustomerController();
					name = customerController.logIn(customerAccounts);
					customerController.mainMenu(name, bankAccounts);
					break;

				// case 2:
				// 	TellerController tellerController = new TellerController();
				// 	tellerController.mainMenu(customerAccounts, bankAccounts);
				// 	break;

				// case 3:
				// 	ManagerController managerController = new ManagerController();
				// 	managerController.mainMenu(customerAccounts, bankAccounts);
				// 	break;

				default:
					System.out.println("Thank you, have a good day!");
					scan.close();
					running = false;
			}	
		} while (running);
	}
}
