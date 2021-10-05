package com.revature;

import java.util.Scanner;

import com.revature.controllers.ManagerController;
import com.revature.controllers.TellerController;
import com.revature.controllers.CustomerController;

public class Driver {

	public static void main(String[] args) {

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
					customerController.logIn();
					customerController.mainMenu();
					break;

				case 2:
					TellerController tellerController = new TellerController();
					tellerController.mainMenu();
					break;

				case 3:
					ManagerController managerController = new ManagerController();
					managerController.mainMenu();
					break;

				default:
					System.out.println("Thank you, have a good day!");
					scan.close();
					running = false;
			}	
		} while (running);
	}
}
