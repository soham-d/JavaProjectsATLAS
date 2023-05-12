package com.amazon.internalclassifieds;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

import com.amazon.internalclassifieds.model.User;



public class UserMenu extends Menu{
		
	private static UserMenu menu = new UserMenu();
	
	public static UserMenu getInstance() {
		return menu;
	}
	
	private UserMenu() {
		
	}
	
	public void showMenu() throws NoSuchAlgorithmException, ParseException {
		
		System.out.println();
		System.out.println("Navigating to User Menu...");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1: Register");
		System.out.println("2: Login");
		System.out.println("3: Cancel");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Enter Your Choice: ");
		int initialChoice = Integer.parseInt(scanner.nextLine());
		
		boolean result = false;
		
		User user = new User();
		
		
		if(initialChoice == 1) {
			
			System.out.println("Enter Your Name:");
			user.name = scanner.nextLine();
			
			System.out.println("Enter Your Phone:");
			user.phone = scanner.nextLine();
			
			System.out.println("Enter Your Email:");
			user.email = scanner.nextLine();
			
			System.out.println("Enter Your Password:");
			user.password = scanner.nextLine();
			
			try {
				// Hash the Password of User :)
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
				user.password = Base64.getEncoder().encodeToString(hash);
			}catch (Exception e) {
				System.err.println("Something Went Wrong: "+e);
			}
			
			System.out.println("Enter Your Address:");
			user.address = scanner.nextLine();
			
			System.out.println("Enter Your Department:");
			user.department = scanner.nextLine();
			
			// As we know, user is registering :)
			user.type = 2;
			user.status = 1;
			
			result = auth.registerUser(user);
			
		}else if(initialChoice == 2) {
			
			System.out.println("Enter Your Email:");
			user.email = scanner.nextLine();
			
			System.out.println("Enter Your Password:");
			user.password = scanner.nextLine();
			
			try {
				// Encoded to Hash i.e. SHA-256 so as to match correctly
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
				user.password = Base64.getEncoder().encodeToString(hash);
			}catch (Exception e) {
				System.err.println("Something Went Wrong: "+e);
			}
			
			result = auth.loginUser(user);
			
		}else if(initialChoice == 3) {
			System.out.println("Thank You for using classifieds App");
		}else {
			System.err.println("Invalid Choice...");
			System.out.println("Thank You for Using classifieds App");
		}
		
		
		if(result && user.type == 2) {
		
			// Link the User to the Session User :)
			ClassifiedsSessionUser.user = user;
			
			System.out.println("^^^^^^^^^^^^^^^^^^^");
			System.out.println("Welcome to User App");
			System.out.println("Hello, "+user.name);
			System.out.println("Its: "+new Date());
			System.out.println("^^^^^^^^^^^^^^^^^^^");
			
			boolean quit = false;
			
			while(true) {
				System.out.println();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        	System.out.println("1: Sell an item");
	        	System.out.println("2: View all classifieds");
	        	System.out.println("3: Connect With Other Users...");
	        	System.out.println("4: Make Payment");
	        	System.out.println("5: Manage your profile");
	        	System.out.println("6: Quit User App");
	        	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        	System.out.println("Select an Option");
	        	
	        	
	        	int choice = Integer.parseInt(scanner.nextLine());

	        	switch (choice) {
					case 1:
						cService.createClassified();
						//cService.postClassified();
						break;
						
					case 2:

						cService.showApprovedClassifieds();
						break;
	
					case 3:
						// Get contact details for other users to connect with
						System.out.println("Select the classified to get Poster's details...");
						cService.showApprovedClassifieds();
						
						System.out.println("Enter the classified ID");
						int classifiedID = Integer.parseInt(scanner.nextLine());
						
						cService.getContactDetails(classifiedID);
						
						break;
						
					case 4:
						
						// Make Payment
						System.out.println("Select a product to buy...");
						cService.showApprovedClassifieds();
						System.out.println("Enter the classified ID");
						int classifiedIDtoBuy = Integer.parseInt(scanner.nextLine());
						cService.makePayment(classifiedIDtoBuy);
						
						break;
						
					case 5:
						System.out.println("My Profile");
						user.prettyPrint();
						
						System.out.println("Do you wish to update Profile (1: update 0: cancel)");
						
			        	choice = Integer.parseInt(scanner.nextLine());

						
						if(choice == 1) {
							
							
							System.out.println("Enter Your Name:");
							String name = scanner.nextLine();
							if(!name.isEmpty()) {
								user.name = name;
							}
							
							System.out.println("Enter Your Phone:");
							String phone = scanner.nextLine();
							if(!phone.isEmpty()) {
								user.phone = phone;
							}
							
							System.out.println("Enter Your Password:");
							String password = scanner.nextLine();
							if(!password.isEmpty()) {
								try {
									// Hash the Password of User :)
									MessageDigest digest = MessageDigest.getInstance("SHA-256");
									byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
									user.password = Base64.getEncoder().encodeToString(hash);
								}catch (Exception e) {
									System.err.println("Something Went Wrong: "+e);
								}
								
							}
							
							System.out.println("Enter Your Address:");
							String address = scanner.nextLine();
							if(!address.isEmpty()) {
								user.address = address;
							}
							
							System.out.println("Enter Your Department:");
							String department = scanner.nextLine();
							if(!department.isEmpty()) {
								user.department = department;
							}
							
							if(auth.updateUser(user)) {
								System.out.println("Profile Updated Successfully");
							}else {
								System.err.println("Profile Update Failed...");
							}
							
						}
						
						
						
						break;
						
	
					case 6:
						System.out.println("Thank You for Using User App !!");
						quit = true;
						break;
		
					default:
						System.err.println("Invalid Choice...");
						break;
				}
	        	
	        	if(quit) {
	        		break;
	        	}
	        	
	        }
		}else {
			System.err.println("Authentication Failed..");
		}
	}
	
}
