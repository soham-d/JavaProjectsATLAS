package com.amazon.buspassmanagement;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Scanner;

import com.amazon.buspassmanagement.controller.AuthenticationService;
import com.amazon.buspassmanagement.controller.BusPassService;
import com.amazon.buspassmanagement.controller.FeedbackService;
import com.amazon.buspassmanagement.controller.RouteService;
import com.amazon.buspassmanagement.db.DB;

public class Menu {

	AuthenticationService auth = AuthenticationService.getInstance(); 
	RouteService routeService = RouteService.getInstance();
	BusPassService passService = BusPassService.getInstance();
	FeedbackService feedbackService = FeedbackService.getInstance();
	
	Scanner scanner = new Scanner(System.in);
	
	void showMainMenu() {
		
		// Initial Menu for the Application
        while(true) {
        	
        	
        	System.out.println();
        	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        	System.out.println("1: Admin");
        	System.out.println("2: User");
        	System.out.println("3: Visitor");
        	System.out.println("4: Quit");
        	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        	
        	System.out.println("Select an Option");
        	int choice = scanner.nextInt();
        	
        	if (choice == 4) {
        		System.out.println("Thank You For using Bus Pass Management App");
        		
        		// Close the DataBase Connection, when user has exited the application :)
        		DB db = DB.getInstance();
        		db.closeConnection();
        		scanner.close();
        		break;
        	}
        	
        	try {
        		MenuFactory.getMenu(choice).showMenu();
			} catch (Exception e) {
				System.err.println("[Menu] [Exception] Invalid Choice...");
			}
        	
        	
        	
        }
	}
	
	
	public void showMenu() throws NoSuchAlgorithmException, ParseException {
		System.out.println("Showing the Menu...");
	}
	
}
