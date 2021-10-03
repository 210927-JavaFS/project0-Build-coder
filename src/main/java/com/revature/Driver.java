package com.revature;

import com.revature.controllers.MenuController;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Welcome to LegacyBanking, your headache awaits...");
		MenuController menuController = new MenuController();
		menuController.logIn();
		menuController.mainMenu();
	}
}
