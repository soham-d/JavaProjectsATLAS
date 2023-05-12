package com.amazon.dmataccountmanger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

import com.amazon.dmataccountmanger.controller.AuthenticationService;
import com.amazon.dmataccountmanger.controller.ShareService;
import com.amazon.dmataccountmanger.controller.TransactionService;
import com.amazon.dmataccountmanger.db.DB;
import com.amazon.dmataccountmanger.model.User;

public class Menu {

	AuthenticationService auth = AuthenticationService.getInstance();
	TransactionService tService = TransactionService.getInstance();
	ShareService sService = ShareService.getInstance();
	Scanner scanner = new Scanner(System.in);

	User user = new User();

	void showMainMenu() throws NoSuchAlgorithmException {

		// Initial Menu for the Application

		System.out.println("Account Number:");
		user.accountNumber = scanner.nextInt();
		
		scanner.nextLine();

		System.out.println("Enter Your Password:");
		user.password = scanner.nextLine();

		try {
			// Hash the Password of User :)
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
			user.password = Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}
		
		boolean result = false;

		result = auth.loginUser(user);

		if (result==true) {

			DmatUserSession.user = user;

			System.out.println("^^^^^^^^^^^^^^^^^^^");
			System.out.println("Welcome to User App");
			System.out.println("Hello, " + user.name);
			System.out.println("Its: " + new Date());
			System.out.println("^^^^^^^^^^^^^^^^^^^");

			while (true) {

				System.out.println();
				System.out.println("1: Display DMAT account details");
				System.out.println("2: Deposit Money");
				System.out.println("3: Withdraw Money");
				System.out.println("4: Buy transaction");
				System.out.println("5: Sell transaction");
				System.out.println("6: View transaction report");
				System.out.println("0: Quit App");

//        	0 – Quit
//        	1 – Display DMAT account details
//        	2 – Deposit Money
//        	3 – Withdraw Money
//        	4 – Buy transaction
//        	5 – Sell transaction
//        	6 – View transaction report

				// DB db = DB.getInstance();

				System.out.println("Select an Option");

				boolean quit = false;
				int choice = scanner.nextInt();

				switch (choice) {
				case 1:
					// Display Account Details
					user.prettyPrint();
					
					System.out.println("Shares held by the user...");
					sService.displayPortfolio();

					break;
				case 2:
					// Deposit Money
					
					if(tService.depositAmount()>0) {
						System.out.println("Amount deposited successfully...");
					}else {
						System.err.println("Deposit failed, please retry...");
					}

					break;

				case 3:
					// Withdraw money
					tService.withrawAmount();

					break;

				case 4:
					// Buy Transaction
					tService.buyShare();
					break;

				case 5:
					// Sell Transaction
					tService.sellShare();

					break;

				case 6:
					// View Transaction Report
					tService.viewTransactionReport();
					break;

				case 0:
					// Quit
					System.out.println("Thank you for using BlazingBulls DMAT Account Application...");
					scanner.close();
					quit = true;

					break;

				default:
					break;
				}
				if (quit) {
					break;
				}

			}

		}
		else {
			System.out.println("Login Unsucessful...");
		}

	}
}

//	void showMainMenu() {
//		
//		// Initial Menu for the Application
//        while(true) {
//        	
//        	System.out.println();
//        	System.out.println("1: Display DMAT account details");
//        	System.out.println("2: Deposit Money");
//        	System.out.println("3: Withdraw Money");
//        	System.out.println("4: Buy transaction");
//        	System.out.println("5: Sell transaction");
//        	System.out.println("6: View transaction report");
//        	System.out.println("0: Quit App");
//        	
////        	0 – Quit
////        	1 – Display DMAT account details
////        	2 – Deposit Money
////        	3 – Withdraw Money
////        	4 – Buy transaction
////        	5 – Sell transaction
////        	6 – View transaction report
//        	
//        	//DB db = DB.getInstance();
//        	
//        	
//        	
//        	System.out.println("Select an Option");
//        	
//        	boolean quit = false;
//        	int choice = scanner.nextInt();
//        	
//        	switch (choice) {
//			case 1:
//				//Display Account Details
//				
//				break;
//			case 2:
//				//Deposit Money
//				
//				break;
//				
//			case 3:
//				//Withdraw money
//				
//				break;
//				
//			case 4:
//				//Buy Transaction
//				
//				break;
//				
//			case 5:
//				//Sell Transaction
//				
//				break;
//				
//			case 6:
//				//View Transaction Report
//				
//				break;
//				
//			case 0:
//				//Quit
//				System.out.println("Thank you for using BlazingBulls DMAT Account Application...");
//				quit = true;
//				
//				break;
//
//			default:
//				break;
//			}
//        	if(quit) {
//        		break;
//        	}
//        	
//        	
//        	}
//        	
//        }
//	}
//
