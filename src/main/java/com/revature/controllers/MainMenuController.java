package com.revature.controllers;

import java.util.Scanner;
// temporarily using Array's in this class
import java.util.ArrayList;

// import com.revature.controllers.ManagerController;
// import com.revature.controllers.TellerController;
// import com.revature.controllers.CustomerController;

// temporarily using models in this class
import com.revature.models.*;

public class MainMenuController {

    public void mainMenu() {
        // since we have yet to set up a database, I'm gonna
		// store the lists of bank accounts and customer accounts here
		// temporarily
		ArrayList<Customer> customerProfiles = new ArrayList<>();
		ArrayList<Customer> employeeProfiles = new ArrayList<>();
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
					name = customerController.logIn(customerProfiles);
					customerController.menu(name, bankAccounts, customerProfiles);
					break;

				case 2:
					/**
					 * Will take out CustomerControllers from this case and one below. 
					 * Just want to make sure utility methods work in both cases
					 */
					TellerController tellerController = new TellerController();
					name = tellerController.logIn(employeeProfiles);
					tellerController.menu(name, bankAccounts, customerProfiles);
					break;

				case 3:
					ManagerController managerController = new ManagerController();
					name = managerController.logIn(employeeProfiles);
					managerController.menu(name, bankAccounts, customerProfiles);
					break;

				default:
					System.out.println("Thank you, have a good day!");
					scan.close();
					running = false;
			}	
		} while (running);
	}
}
