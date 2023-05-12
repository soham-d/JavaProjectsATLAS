package com.amazon.dmataccountmanger.controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import com.amazon.dmataccountmanger.db.UserDAO;
import com.amazon.dmataccountmanger.model.User;

public class AuthenticationService {
	
	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();
	
	Scanner scanner = new Scanner(System.in);
	
	public static AuthenticationService getInstance() {
		return service;
	}
	
//	private static byte[] getSHA(String input) throws NoSuchAlgorithmException{
//		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
//		return mDigest.digest(input.getBytes(StandardCharsets.UTF_8));
//	}
//	
//	private static String toHexString(byte[] hash) {
//		BigInteger number = new BigInteger(1,hash);
//		StringBuilder hexString = new StringBuilder(number.toString(16));
//		
//		while(hexString.length()<64) {
//			hexString.insert(0,'0');
//			
//		}
//		return hexString.toString();
//	}
	
	
	private AuthenticationService(){

	}

	
	public boolean loginUser(User user) throws NoSuchAlgorithmException {
		
//		try {
//			// Hash the Password of User :)
//			MessageDigest digest = MessageDigest.getInstance("SHA-256");
//			byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
//			user.password = Base64.getEncoder().encodeToString(hash);
//		}catch (Exception e) {
//			System.err.println("Something Went Wrong: "+e);
//		}
//		
				
		String sql = "SELECT * FROM \"user\" WHERE accountNumber = "+user.accountNumber+" AND password = '"+user.password+"'";
		List<User> users = dao.retrieve(sql);
		
		if(users.size() > 0) {
			User u = users.get(0);
			user.id = u.id;
			user.name = u.name;
			user.accountNumber = u.accountNumber;
			user.accountBalance = u.accountBalance;
			user.lastUpdatedOn = u.lastUpdatedOn;
			return true;
		}
		
		return false; 
	}
	
	public boolean registerUser() throws NoSuchAlgorithmException {
		User user = new User();
		
		System.out.println();
		System.out.println("Enter User Name...");
		user.name = scanner.nextLine();
		System.out.println("Enter Your Password:");
		//scanner.nextLine();
		user.password = scanner.nextLine();
		user.accountNumber = returnLastAccountNumber()+1;
		user.accountBalance = 0;
		
		try {
			// Hash the Password of User :)
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
			user.password = Base64.getEncoder().encodeToString(hash);
		}catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		System.out.println("Your Account Nummber is: "+user.accountNumber);
	
		return dao.insert(user) > 0;
	}
	
	
	public int returnLastAccountNumber() {
		List<User> accounts = new UserDAO().retrieve();
		User lastAccount = new User();
		for(User account:accounts) {
			lastAccount = account;			
		}
		return lastAccount.accountNumber;
	}

}
