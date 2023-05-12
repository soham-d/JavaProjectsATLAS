package com.amazon.internalclassifieds.controller;

import java.math.BigInteger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import com.amazon.internalclassifieds.db.UserDAO;
import com.amazon.internalclassifieds.model.User;

public class AuthenticationService {

	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();
	
	public static AuthenticationService getInstance() {
		return service;
	}

	private static byte[] getSHA(String input) throws NoSuchAlgorithmException{
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		return mDigest.digest(input.getBytes(StandardCharsets.UTF_8));
	}
	
	private static String toHexString(byte[] hash) {
		BigInteger number = new BigInteger(1,hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		
		while(hexString.length()<64) {
			hexString.insert(0,'0');
			
		}
		return hexString.toString();
	}
	
	
	private AuthenticationService(){
	}
	
	Scanner scanner = new Scanner(System.in);
	
	
	/*public boolean loginUser(User user) {
		
		boolean result = false;
		
		
		Iterator<Integer> itr = users.keySet().iterator();
		while(itr.hasNext()) {
			Integer key = itr.next();
			
			User u = users.get(key);
			// Select * from User where email = '' and password = ''
			if(u.email.equals(user.email) && u.password.equals(user.password)) {
				// user will now point to a new Object i.e. referred by u
				user.name = u.name;
				user.type = u.type; 
				result = true;
				break;
			}
		
		}
		return result;
	}*/
	
	public boolean loginUser(User user) throws NoSuchAlgorithmException {
				
		String sql = "SELECT * FROM \"user\" WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
		List<User> users = dao.retrieve(sql);
		
		if(users.size() > 0) {
			User u = users.get(0);
			user.id = u.id;
			user.name = u.name;
			user.phone = u.phone;
			user.email = u.email;
			user.address = u.address;
			user.department = u.department;
			user.type = u.type;
			user.status = u.status;
			user.lastUpdatedOn = u.lastUpdatedOn;
			return true;
		}
		
		return false; 
	}
	
	public boolean registerUser(User user) throws NoSuchAlgorithmException {
		//int result = dao.insert(user);
		//return result > 0;
		return dao.insert(user) > 0;
	}
	
	public boolean updateUser(User user) throws NoSuchAlgorithmException {
		return dao.update(user) > 0;
	}
	
	public void showAllUsers() {
		List<User> objects = null;
		objects = dao.retrieve();
		for (User object : objects) {
			object.prettyPrint();
		}
	}
	
	
	public void activateDeactivateUsers() {
		System.out.println("Select User to activate/deactivate");
		showAllUsers();

		System.out.println("Enter User ID: ");
		int UserId = Integer.parseInt(scanner.nextLine());

		String sql = "Select * from \"User\" where id=" + UserId;

		List<User> users = dao.retrieve(sql);

		User user = users.get(0);

		System.out.println("Selected user: ");
		user.prettyPrint();

		System.out.println("1: Activate");
		System.out.println("2: Deactivate");
		System.out.println("Enter Approval Choice: ");
		int status = Integer.parseInt(scanner.nextLine());
		int updateUser = 0;

		if (status == 1) {
			user.setStatus(1);
			updateUser = dao.update(user);
		} else if (status == 2) {
			user.setStatus(2);
			updateUser = dao.update(user);
		}

		if (updateUser > 0) {
			System.out.println("User Updated Successfully");
		} else {
			System.out.println("Updating User Request Failed. Try Again..");
		}
	}

}
