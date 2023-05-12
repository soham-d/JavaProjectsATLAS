package com.amazon.internalclassifieds;

import java.security.NoSuchAlgorithmException;

import java.util.Date;

import com.amazon.internalclassifieds.model.User;

public class AdminMenu extends Menu {

	private static AdminMenu menu = new AdminMenu();

	public static AdminMenu getInstance() {
		return menu;
	}

	private AdminMenu() {

	}

	public void showMenu() throws NoSuchAlgorithmException {

		System.out.println("Navigating to Admin Menu...");

		// Login Code should come before the Menu becomes Visible to the Admin
		User adminUser = new User();

		// AuthenticationService auth = AuthenticationService.getInstance();

		System.out.println("Enter Your Email:");
		adminUser.email = scanner.nextLine();

		System.out.println("Enter Your Password:");
		adminUser.password = scanner.nextLine();

		boolean result = auth.loginUser(adminUser);

		if (result && adminUser.type == 1) {

			// Link the Admin User to the Session User :)
			ClassifiedsSessionUser.user = adminUser;

			System.out.println("*********************");
			System.out.println("Welcome to Admin App");
			System.out.println("Hello, " + adminUser.name);
			System.out.println("Its: " + new Date());
			System.out.println("*********************");

			boolean quit = false;

			while (true) {
				System.out.println();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("1: Approve/Reject Classifieds");
				System.out.println("2: Activate/Deactivate Users");
				System.out.println("3: Add/Remove Classifieds");
				System.out.println("4: Modify Classifieds"); // Manage type or category
				System.out.println("5: Generate Reports");
				System.out.println("6: Quit Admin App");
				System.out.println("Select an Option");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					// Approve Reject Buss Pass
					cService.approveClassifieds();

					break;

				case 2:

					auth.activateDeactivateUsers();
					break;

				case 3:
					System.out.println("1: Add Classified");
					System.out.println("2: Delete Classified");

					System.out.println("Enter Your Choice: ");
					int classifiedChoice = Integer.parseInt(scanner.nextLine());

					if (classifiedChoice == 1) {
						cService.createClassified();
					} else if (classifiedChoice == 2) {
						cService.deleteClassified();
					} else {
						System.err.println("Invalid Stop Choice..");

					}
					break;

				case 4:
					
					cService.modifyClassifiedDetails();

					break;

				case 5:
					System.out.println("Select the report type:");
					System.out.println("1. Orders ");
					System.out.println("2. Classifieds ");
					
					int reportChoice = Integer.parseInt(scanner.nextLine());
					
					if(reportChoice == 1) {
						
					
					System.out.println("Orders processed till date...");
					cService.orderReports();
					}
					else if(reportChoice == 2) {
					System.out.println("Classifieds created till date");
					cService.showClassifieds();
					}else {
						System.err.println("Invalid choice... Try again..");
					}

					break;

				case 6:
					System.out.println("Thank You for Using Admin App !!");
					quit = true;
					break;

				default:
					System.err.println("Invalid Choice...");
					break;
				}

				if (quit) {
					break;
				}

			}
		} else {
			System.err.println("Invalid Credentials. Please Try Again !!");
		}
	}

}
