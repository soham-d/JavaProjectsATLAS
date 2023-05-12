package com.amazon.internalclassifieds;

import java.security.NoSuchAlgorithmException;

import java.text.ParseException;
import java.util.Scanner;

import com.amazon.internalclassifieds.controller.AuthenticationService;
import com.amazon.internalclassifieds.controller.ClassifiedsService;
import com.amazon.internalclassifieds.db.DB;


public class Menu {

	AuthenticationService auth = AuthenticationService.getInstance(); 
	ClassifiedsService cService = ClassifiedsService.getInstance();
	DB db = DB.getInstance();
	
	Scanner scanner = new Scanner(System.in);
	
	void showMainMenu() {
		
		// Initial Menu for the Application
        while(true) {
        	
        	
        	System.out.println();
        	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        	System.out.println("1: Admin");
        	System.out.println("2: User");
        	System.out.println("3: Quit");
        	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        	
        	System.out.println("Select an Option");
        	int choice = Integer.parseInt(scanner.nextLine());
        	
        	if (choice == 3) {
        		System.out.println("Thank You For using Classifieds App");
        		
        		// Close the DataBase Connection, when user has exited the application :)
        		DB db = DB.getInstance();
        		db.closeConnection();
        		scanner.close();
        		break;
        	}
        	
        	try {
        		MenuFactory.getMenu(choice).showMenu();
        		
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("[Menu] [Exception] Invalid Choice...");
				
			}
        	
        	
        	
        }
	}
	
	
	public void showMenu() throws NoSuchAlgorithmException, ParseException {
		System.out.println("Showing the Menu...");
	}
	
}
