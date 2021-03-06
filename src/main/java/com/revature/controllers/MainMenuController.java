package com.revature.controllers;

import java.util.Scanner;

public class MainMenuController {

    public void mainMenu() {

		boolean running = true;

		System.out.println();
		System.out.println("Welcome to LegacyBanking, where your money is our legacy");

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
					customerController.menu();
					break;

				case 2:
					TellerController tellerController = new TellerController();
					tellerController.menu();
					break;

				case 3:
					ManagerController managerController = new ManagerController();
					managerController.menu();
					break;


				default:
					System.out.println("Thank you, have a good day!");
					scan.close();
					running = false;
			}	
		} while (running);
	}
}
