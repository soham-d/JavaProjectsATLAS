package com.amazon.buspassmanagement.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
 

import com.amazon.buspassmanagement.BusPassSession;
import com.amazon.buspassmanagement.db.BusPassDAO;
import com.amazon.buspassmanagement.db.UserDAO;
import com.amazon.buspassmanagement.model.BusPass;
import com.amazon.buspassmanagement.model.User;
import com.nimbusds.jose.util.DateUtils;

public class BusPassService {

	BusPassDAO passDAO = new BusPassDAO();
	UserDAO userDAO = new UserDAO();
	
	// Create it as a Singleton 
	private static BusPassService passService = new BusPassService();
	AuthenticationService auth = AuthenticationService.getInstance();
	
	Scanner scanner = new Scanner(System.in);
	
	public static BusPassService getInstance() {
		return passService;
	}
	
	private BusPassService() {
	
	}
	
	// Handler for the Bus Pass :)
	public void requestPass() {
		BusPass pass = new BusPass();
		pass.getDetails(false);
		
		// Add the User ID Implicitly.
		pass.uid = BusPassSession.user.id;
		
		// As initially record will be inserted by User where it is a request
		pass.status = 1; // initial status as requested :)
		
		int result = passDAO.insert(pass);
		String message = (result > 0) ? "Pass Requested Successfully" : "Request for Pass Failed. Try Again.."; 
		System.out.println(message);
	}
	
	// Handler for the Bus Pass :)
	public void requestPassVisitor() throws NoSuchAlgorithmException {
		
		BusPass pass = new BusPass();
		
		User user = new User();
		
		boolean result = false;
		
		//Get details for visitor
		
		
		System.out.println("Enter Your Name:");
		user.name = scanner.nextLine();
		
		System.out.println("Enter Your Phone:");
		user.phone = scanner.nextLine();
		
		System.out.println("Enter Your Email:");
		user.email = scanner.nextLine();
		
		System.out.println("Enter Your Password:");
		user.password = scanner.nextLine();
		String origPwd = user.password;
		
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
		
		// As we know, Visitor is registering :)
		user.type = 4;
		
		result = auth.registerUser(user);

		boolean loginStatus = false;
		
		System.out.println("Details Stored Successfully...");
		
		user.password = origPwd;
		loginStatus = auth.loginUser(user);
		List<User> users =  userDAO.retrieve("SELECT * FROM users WHERE email = '"+user.email+"'");
		
		
		if(result) {
			
			BusPass pass1 = new BusPass();
			pass1.getDetails(false);
			
			// Add the User ID Implicitly.
			pass1.uid = users.get(0).id;
			
			// As initially record will be inserted by User where it is a request
			pass1.status = 1; // initial status as requested :)
			
			int result1 = passDAO.insert(pass1);
			String message = (result1 > 0) ? "Pass Requested Successfully" : "Request for Pass Failed. Try Again.."; 
			System.out.println(message);
		}
		else {
			System.err.println("Registration Failed for Visitor");
			
		}
		
	}
	
	public void deletePass() {
		BusPass pass = new BusPass();
		System.out.println("Enter Pass ID to be deleted: ");
		pass.id = scanner.nextInt();
		int result = passDAO.delete(pass);
		String message = (result > 0) ? "Pass Deleted Successfully" : "Deleting Pass Failed. Try Again.."; 
		System.out.println(message);
	}
	
	/*
	 
	 	Extra Task:
	 	IFF : You wish to UpSkill :)
	 
	 	Scenario: Open the same application in 2 different terminals
	 	1 logged in by user
	 	another logged in by admin
	 	
	 	If admin, approves or rejects the pass -> User should be notified :)
	 	
	 	Reference Link
	 	https://github.com/ishantk/AmazonAtlas22/blob/master/Session8/src/com/amazon/atlas/casestudy/YoutubeApp.java
	 
	 */
	
	public void approveRejectPassRequest() {
		
		BusPass pass = new BusPass();

		System.out.println("Enter Pass ID: ");
		pass.id = scanner.nextInt();
		
		System.out.println("2: Approve");
		System.out.println("3: Cancel");
		System.out.println("Enter Approval Choice: ");
		pass.status = scanner.nextInt();

    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();
		pass.approvedRejectedOn = dateFormat.format(date1);
		
		if(pass.status == 2) {
			calendar.add(Calendar.YEAR, 1);
			Date date2 = calendar.getTime();
			pass.validTill = dateFormat.format(date2);
		}else {
			pass.validTill = pass.approvedRejectedOn;
		}
		
		int result = passDAO.update(pass);
		String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again.."; 
		System.out.println(message);
		System.out.println();
	}
	
	public void viewPassRequests() {
		
		System.out.println("Enter Route ID to get All the Pass Reqeuests for a Route");
		System.out.println("Or 0 for All Bus Pass Requests");
		System.out.println("Enter Route ID: ");
		
		int routeId = scanner.nextInt();
		
		List<BusPass> objects = null;
		
		if(routeId == 0) {
			objects = passDAO.retrieve();
		}else {
			String sql = "SELECT * from BusPass where routeId = "+routeId;
			objects = passDAO.retrieve(sql);
		}
		
		for(BusPass object : objects) {
			object.prettyPrint();
		}
	}
	
	public void viewPassRequestsByUser(int uid) {
		
		String sql = "SELECT * from BusPass where uid = "+uid;
		List<BusPass> objects = passDAO.retrieve(sql);
		
		for(BusPass object : objects) {
			object.prettyPrint();
		}
	}

	public void suspendPass() throws ParseException {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Enter the Bus Pass ID...");
		int passId = scanner.nextInt();
		
		System.out.println("Enter the duration for suspension [IN MONTHS]");
		int suspensionDuration = scanner.nextInt();
		
		
		//Step 1: get existing details
		
		String sql = "SELECT * from BusPass WHERE id = "+passId;
		
		List<BusPass> bPass = passDAO.retrieve(sql);
		
		
		//Step 2: modify details
		
		BusPass pass = new BusPass();
		pass = bPass.get(0);
		pass.prettyPrint();
		
		
		pass.status = 4;
		Date currDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(pass.validTill);
		
		//LocalDate currDate = LocalDate.parse(pass.validTill);
		//System.out.println(currDate.toString());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		
		cal.add(Calendar.MONTH, suspensionDuration);
		
		Date newDate = cal.getTime();
		
		//System.out.println(newDate);
		
		java.sql.Timestamp ts = new java.sql.Timestamp(newDate.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//        System.out.println(formatter.format(ts).toString());
//		System.out.println(ts.toString()); 
		pass.validTill = formatter.format(ts).toString();
		
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, suspensionDuration);
		
		Date newApprovalDate = cal.getTime();
		java.sql.Timestamp ts1 = new java.sql.Timestamp(newApprovalDate.getTime()); 
		pass.approvedRejectedOn = formatter.format(ts1).toString();
		
	
		//Step 3: Change values in DB
		
		pass.prettyPrint();
		int result = passDAO.update(pass);
		String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again.."; 
		
		System.out.println(message);
		
		
		
		
		
	}
	
}
