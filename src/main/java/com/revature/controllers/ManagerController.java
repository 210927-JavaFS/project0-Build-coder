package com.revature.controllers;

import java.util.Scanner;
import java.util.UUID;

import com.revature.models.Account;
import com.revature.services.AccountService;

public class ManagerController extends MenuController {

    private static AccountService accountService = new AccountService();
	private static boolean running = true;
	Scanner scan = createScanner();

    @Override
	public void mainMenu(){
		do { // customer functionality
			System.out.println();
			System.out.println("Please choose an option: ");		
			System.out.println("1: Create a bank account");
			System.out.println("2: Deposit");
			System.out.println("3: Withdraw");
			System.out.println("4: Transfer");
			System.out.println("Enter any key that's not 1-4 to quit");
			System.out.println();
			
			int response = scan.nextInt();
			
			switch (response) {
				case 1:
					Scanner scan = createScanner();
					System.out.println("What is your name?");
					String name = scan.nextLine();
					Account account = null;
					account = buildBankAccount(account, name);
					accountService.addToList(account);
                    scan.close();
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
}
