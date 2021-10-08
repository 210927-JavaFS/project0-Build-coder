package com.revature.controllers;

import java.util.Scanner;
// temporarily using Array's in this class
import java.util.ArrayList;

// temporarily using models in this class
import com.revature.models.*;

public class MainMenuController {

    public void mainMenu() {
        // since we have yet to set up a database, I'm gonna
		// store the lists of bank accounts and customer accounts here
		// temporarily
		ArrayList<Customer> customerProfiles = new ArrayList<>();
		ArrayList<Employee> employeeProfiles = new ArrayList<>();
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
			
			switch (response) {
				case 1:
					CustomerController customerController = new CustomerController();
					String userID = customerController.customerLogin(customerProfiles);
					customerController.menu(userID, bankAccounts, customerProfiles);
					break;

				case 2:
					TellerController tellerController = new TellerController();
					String tellerID = tellerController.employeeLogin(employeeProfiles);
					tellerController.menu(tellerID, bankAccounts, customerProfiles);
					break;

				case 3:
					ManagerController managerController = new ManagerController();
					String managerID = managerController.employeeLogin(employeeProfiles);
					managerController.menu(managerID, bankAccounts, customerProfiles);
					break;

				default:
					System.out.println("Thank you, have a good day!");
					scan.close();
					running = false;
			}	
		} while (running);
	}
}
