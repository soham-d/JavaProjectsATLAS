package com.amazon.dmataccountmanger.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanger.model.User;

public class UserDAO implements DAO<User> {
	
	DB db = DB.getInstance();

	public int insert(User object) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO \"User\" (name, accountNumber, password, accountBalance) "
						+ "VALUES ('"+object.name+"', "+object.accountNumber+", '"+object.password+"',"+object.accountBalance+")";
		return db.executeSQL(sql);
	}

	public int update(User object) {
		// TODO Auto-generated method stub
		String sql = "UPDATE \"User\" set name = '"+object.name+"', password='"+object.password+"', accountBalance="+object.accountBalance+""
				+ " WHERE accountNumber = "+object.accountNumber+";";
		return db.executeSQL(sql);
	}

	public int delete(User object) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM \"User\" WHERE accountNumber = "+object.accountNumber+";";
		return db.executeSQL(sql);
	}

	public List<User> retrieve() {
		// Method to retrieve all users in the user table.
		String sql = "SELECT * from \"User\"";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.accountNumber = set.getInt("accountNumber");
				user.password = set.getString("password");
				user.accountBalance = set.getInt("accountBalance");
				user.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return users;
	}
	
	

	public List<User> retrieve(String sql) {
		// Method to retrieve Specified of user(s) by Passing SQL query.
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.accountNumber = set.getInt("accountNumber");
				user.password = set.getString("password");
				user.accountBalance = set.getInt("accountBalance");
				user.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return users;
	}
	

}
