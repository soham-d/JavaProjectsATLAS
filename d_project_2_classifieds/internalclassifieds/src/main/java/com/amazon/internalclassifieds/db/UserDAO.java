package com.amazon.internalclassifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.amazon.internalclassifieds.model.User;

public class UserDAO implements DAO<User> {

	DB db = DB.getInstance();
	
	public int insert(User object) {
		String sql = "INSERT INTO \"User\" (name, phone, email, password, address, department, type,status) VALUES ('"+object.name+"', '"+object.phone+"',"
				+ " '"+object.email+"', '"+object.password+"', '"+object.address+"', '"+object.department+"', "+object.type+","+object.status+")";
		return db.executeSQL(sql);
	}

	public int update(User object) {
		String sql = "UPDATE \"User\" set name = '"+object.name+"', phone='"+object.phone+"', password='"+object.password+"', address='"+object.address+"',"
				+ " department='"+object.department+"', status = "+object.status+" WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	public int delete(User object) {
		String sql = "DELETE FROM \"User\" WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	public List<User> retrieve() {
		
		String sql = "SELECT * from \"User\"";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.department = set.getString("department");
				user.type = set.getInt("type");
				user.status = set.getInt("status");
				user.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
	
	public List<User> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.department = set.getString("department");
				user.type = set.getInt("type");
				user.status = set.getInt("status");
				user.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
	

}
