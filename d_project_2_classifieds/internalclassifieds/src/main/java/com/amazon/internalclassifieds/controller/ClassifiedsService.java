package com.amazon.internalclassifieds.controller;

import java.awt.Menu;
import java.util.List;
import java.util.Scanner;

import com.amazon.internalclassifieds.ClassifiedsSessionUser;
import com.amazon.internalclassifieds.db.CategoryDAO;
import com.amazon.internalclassifieds.db.ClassifiedsDAO;
import com.amazon.internalclassifieds.db.OrderDAO;
import com.amazon.internalclassifieds.db.UserDAO;
import com.amazon.internalclassifieds.model.Category;
import com.amazon.internalclassifieds.model.Classifieds;
import com.amazon.internalclassifieds.model.Order;
import com.amazon.internalclassifieds.model.User;

public class ClassifiedsService {
	private static ClassifiedsService service = new ClassifiedsService();
	ClassifiedsDAO cDao = new ClassifiedsDAO();
	CategoryDAO catDao = new CategoryDAO();

	Scanner scanner = new Scanner(System.in);

	public static ClassifiedsService getInstance() {
		return service;
	}

	private ClassifiedsService() {

	}

	public void showCategories() {

		System.out.println("Available Categories....");
		List<Category> categories = catDao.retrieve();
		for (Category category : categories) {
			category.prettyPrint();
		}
	}

	public int showCondition() {
		System.out.println("Select Product Conition...");
		System.out.println("1: Brand New (Seal Pakced)");
		System.out.println("2: Lightly Used");
		System.out.println("3: Moderately Used");
		System.out.println("4: Heavily Used");
		System.out.println("5: Damaged/Dented");
		System.out.println("6: Not Working");

		// Brand New(Seal Packed), Lightly Used, Moderately Used, Heavily Used,
		// Damaged/Dented, Not Working

		return Integer.parseInt(scanner.nextLine());
	}

	public void createClassified() {

		// Scanner scanner2 = new Scanner(System.in);

		// Show all categories
		showCategories();

		// Take input

		Classifieds classified = new Classifieds();

		System.out.println("Choose a category from the list above:");
		classified.setCategoryId(Integer.parseInt(scanner.nextLine()));

		classified.setCondition(showCondition());
		classified.setStatus(0);

		System.out.println("Enter Classified's Headline...[Max 100 Chars]");
		classified.setHeadLine(scanner.nextLine());

		if (classified.headLine.length() > 100)
			System.err.println("Character Limit Exceeded in Header");

		System.out.println("Enter Product Name...[Max 50 Chars]");
		classified.setProductName(scanner.nextLine());
		if (classified.productName.length() > 50)
			System.err.println("Character Limit Exceeded in product name");

		// classified.productName = scanner2.nextLine().substring(0,51);

		System.out.println("Enter Product Brand...[Max 25 Chars]");
		classified.setBrand(scanner.nextLine());
		if (classified.brand.length() > 100)
			System.err.println("Character Limit Exceeded in Brand");

		// classified.brand = scanner2.nextLine().substring(0,26);

		System.out.println("Enter Product Description...[Max 500 Chars]");
		classified.setDescription(scanner.nextLine());
		if (classified.description.length() > 500)
			System.err.println("Character Limit Exceeded in Description");
		// classified.description = scanner2.nextLine().substring(0,501);

		System.out.println("Enter Ask Price recurrence unit. (For example, if it is an apartment for\r\n"
				+ "rent, mention the price as Rs. 20,000 per month)");
		classified.setPrice(scanner.nextLine());
		// classified.price = scanner2.nextLine().substring(0,51);

		System.out.println("Enter Product Pictures' shortened links \n (max 4 links, separated by BLANK SPACE) Min resolution: 800X800 px \n Total length of URLs: 500 chars ");
		classified.setPictures(scanner.nextLine());
		if (classified.pictures.length() > 500)
			System.err.println("Character Limit Exceeded in Picture Links");
		// Check : Maximum 4 links allowed

		// Insert New Entry to classifieds table
		int createClassifiedResult = cDao.insert(classified);

		if (createClassifiedResult > 0) {
			System.out.println("Classified Created Successfully...");
		} else {
			System.err.println("Something went wrong, Please try again...");
		}
	}

	public void showClassifieds() {
		List<Classifieds> classifieds = cDao.retrieve();
		for (Classifieds classified : classifieds) {
			classified.prettyPrint();

		}
	}
	
	public void showApprovedClassifieds() {
		
		String sql = "SELECT * FROM classifieds where status=1";
		List<Classifieds> classifieds = cDao.retrieve(sql);
		for (Classifieds classified : classifieds) {
			classified.prettyPrint();

		}
	}

	

	public void getContactDetails(int classifiedId) {
		// TODO Auto-generated method stub
		String sqlCalssifieds = "Select * from classifieds where status = 1 and id=" + classifiedId;
		List<Classifieds> classifieds = cDao.retrieve(sqlCalssifieds);

		System.out.println();
		System.out.println("Getting User Details for AD# " + classifiedId);

		String sql = "Select * from \"user\" where id=" + classifieds.get(0).userId;

		List<User> users = new UserDAO().retrieve(sql);

		User postedBy = users.get(0);

		System.out.println("This Classified was posted by: ");
		System.out.println("Name: \t\t" + postedBy.name);
		System.out.println("Contact Number: " + postedBy.phone);
		System.out.println("Email ID: \t" + postedBy.email);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	}

	public void makePayment(int classifiedIDtoBuy) {
		// TODO Auto-generated method stub
		String sqlCalssifieds = "Select * from classifieds where status = 1 and id=" + classifiedIDtoBuy;
		List<Classifieds> classifieds = cDao.retrieve(sqlCalssifieds);
		
		Order order = new Order();
		
		order.setClassifiedId(classifieds.get(0).id);
		order.setFromUserId(classifieds.get(0).userId);
		order.setToUserId(ClassifiedsSessionUser.user.id);
		
		System.out.println("Quote a price to buy...");
		order.setProposedPrice(Integer.parseInt(scanner.nextLine()));
		order.setStatus(0); //0 = Requested for purchase
		
		
		
		

		System.out.println("Please make a payment of " + classifieds.get(0).price + " to proceed...");
		System.out.println("Select Payment Menthod...");
		System.out.println("1. NetBanking");
		System.out.println("2. UPI");
		System.out.println("3. By Cash at the time of pickUp");

		System.out.println("Enter your choice...");
		int payment = Integer.parseInt(scanner.nextLine());

		if (payment == 1)
			System.out.println("Paid successfully using NetBanking...");
		if (payment == 2)
			System.out.println("Paid successfully using UPI...");
		if (payment == 3)
			System.out.println("Handover the amount to owner at the time of pick up...");

		Classifieds classifieds2 = classifieds.get(0);
		classifieds2.setStatus(3);

		int sellResult = cDao.update(classifieds2);

		if (sellResult > 0) {
			System.out.println(classifieds2.productName + " Purchased successfully...");
			order.setStatus(3);
			int orderStatus = new OrderDAO().insert(order);
			if(orderStatus>0) {
				System.out.println("Order Created Successfully...");
			}else {
				System.err.println("Order creation failed... Please try again..");
			}
			
			
		} else {
			System.err.println("Purchase failed... try again..");
			order.setStatus(0);
			int orderStatus = new OrderDAO().insert(order);
			if(orderStatus>0) {
				System.out.println("Order Created Successfully...");
			}else {
				System.err.println("Order creation failed... Please try again..");
			}
		}

	}

	public void approveClassifieds() {
		System.out.println("Select Classified to approve");
		showClassifieds();

		System.out.println("Enter Classified ID:");
		int classifiedId = Integer.parseInt(scanner.nextLine());

		String sql = "Select * from classifieds where id=" + classifiedId;

		List<Classifieds> classifieds = cDao.retrieve(sql);

		Classifieds classified = classifieds.get(0);

		System.out.println("Selected classified: ");
		classified.prettyPrint();

		System.out.println("Enter 1 to approve or 2 to Reject");
		int decision = Integer.parseInt(scanner.nextLine());
		int updateClassified = 0;

		if (decision == 1) {
			classified.setStatus(1);
			updateClassified = cDao.update(classified);
			System.out.println("Classified is approved.");
		} else if (decision == 2) {
			classified.setStatus(2);
			updateClassified = cDao.update(classified);
			System.out.println("Classified is Rejected.");
		} else {
			System.err.println("Incorrect decision... No changes made");
		}

		if (updateClassified > 0)
			System.out.println("Classified updated successfully...");
	}

	public void deleteClassified() {
		Classifieds classified = new Classifieds();
		System.out.println("All classifieds in the DB:");
		showClassifieds();
		
		
		System.out.println("Enter classified ID to be deleted: ");
		classified.id = Integer.parseInt(scanner.nextLine());
		int result = cDao.delete(classified);
		String message = (result > 0) ? "Classified Deleted Successfully" : "Deleting Classified Failed. Try Again..";
		System.out.println(message);
	}

	public void modifyClassifiedDetails() {
		// TODO Auto-generated method stub
		
		System.out.println("Select Classified to modify...");
		showClassifieds();
		System.out.println();
		System.out.println("Enter the classified ID");
		int choice = Integer.parseInt(scanner.nextLine());
		
		String sql = "Select * from classifieds where id="+choice+";";
		
		
		List<Classifieds> classifieds = cDao.retrieve(sql);
		
		Classifieds modifiedClassified = classifieds.get(0);
		
		
		System.out.println("Select the attribute to be changed: ");
		System.out.println("1. Modify category ID");
		System.out.println("2. Modify Headline");
		System.out.println("3. Modify Product Name");
		System.out.println("4. Description");
		
		System.out.println("Enter the choice:");
		int option = Integer.parseInt(scanner.nextLine());
		
		if(option==1) {
			System.out.println("Enter new category ID:");
			modifiedClassified.setCategoryId(Integer.parseInt(scanner.nextLine()));
			
		}else if(option==2) {
			System.out.println("Enter new headline:");
			modifiedClassified.setHeadLine(scanner.nextLine());
			
			
		}else if(option==3) {
			System.out.println("Enter new product name:");
			modifiedClassified.setProductName(scanner.nextLine());
			
		}else if(option==4) {
			System.out.println("Enter new Description: ");
			modifiedClassified.setDescription(scanner.nextLine());
			
		}
		
		int modifyClassifiedResult = cDao.update(modifiedClassified);
		
		if(modifyClassifiedResult>0) {
			System.out.println("Classified updated successfully...");
		}else{
			System.out.println("Modification failed, Please try again..."); 
		}
		
	}

	public void orderReports() {
		// TODO Auto-generated method stub
		
		List<Order> orders = new OrderDAO().retrieve();
		
		for(Order order:orders) {
			order.prettyPrint();
		}
		
	}

}
