package com.amazon.buspassmanagement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

import com.amazon.buspassmanagement.controller.BusPassService;
import com.amazon.buspassmanagement.controller.FeedbackService;
import com.amazon.buspassmanagement.controller.RouteService;
import com.amazon.buspassmanagement.model.User;

public class VisitorMenu extends Menu{
		
	private static VisitorMenu menu = new VisitorMenu();
	
	public static VisitorMenu getInstance() {
		return menu;
	}
	
	RouteService rService = RouteService.getInstance();
	FeedbackService fService = FeedbackService.getInstance();
	BusPassService bpService = BusPassService.getInstance();
	
	private VisitorMenu() {
		
	}
	
	public void showMenu() throws NoSuchAlgorithmException {
		
		System.out.println("Navigating to Visitor Menu...");
		
//		Scanner scanner = new Scanner(System.in);

		
		boolean quit = false;
		
		
		while(true) {
			
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("1: View All Routes");
			System.out.println("2: View Seat Occupancy");
			System.out.println("3: Request for new route");
			System.out.println("4: Apply For Bus Pass");
			System.out.println("5: Quit");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			System.out.println("Enter Your Choice: ");
			int initialChoice = Integer.parseInt(scanner.nextLine());
			
			
			switch (initialChoice) {
			case 1:
				//View All Routes
				rService.viewAlloutes();
//				scanner.nextLine();
				break;
			case 2:
				//View Seat Occupancy
				rService.viewSeatOccupancy();
//				scanner.nextLine();
				break;
			case 3:
				//Request New Route
				rService.requestRouteVisitor();
//				scanner.nextLine();
				break;
			case 4:
				//Apply for bus pass
				bpService.requestPassVisitor();
//				scanner.nextLine();
				break;
			case 5:
				//Quit
				System.out.println("Thank you for using the Bus Pass Management App...");
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
}
