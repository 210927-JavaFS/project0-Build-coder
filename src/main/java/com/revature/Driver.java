package com.revature;

import com.revature.controllers.MenuController;
import com.revature.models.CustomerAccount;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Welcome to LegacyBanking, your headache awaits...");
		MenuController menuController = new MenuController();
		CustomerAccount account = menuController.getAccount();
		
		while (account != null) {
			System.out.println(account.getName());
			System.out.println(account.getAccountID());
			System.out.println(account.getBalance());
			
			account = null;
		}
		
	}
}
