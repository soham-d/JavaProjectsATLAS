package com.amazon.internalclassifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.internalclassifieds.model.Category;
import com.amazon.internalclassifieds.model.Order;

public class OrderDAO implements DAO<Order>{
	
	DB db = DB.getInstance();

	public int insert(Order object) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO \"order\" (classifiedId, fromUserId, toUserId, proposedPrice, status) "
				+ "VALUES ("+object.classifiedId+", "+object.fromUserId+", "+object.toUserId+", "+object.proposedPrice+", "+object.status+")";
		return db.executeSQL(sql);
	}

	public int update(Order object) {
		// TODO Auto-generated method stub
		String sql = "UPDATE \"order\" SET classifiedId="+object.classifiedId+" ,fromUserId="+object.fromUserId+" ,toUserId="+object.toUserId+" "
				+ ",proposedPrice="+object.proposedPrice+" ,status="+object.status+"  WHERE id="+object.id+";";
		
		return db.executeSQL(sql);
	}

	public int delete(Order object) {
		// TODO Auto-generated method stub
		
		String sql = "DELETE FROM \"order\" WHERE id="+object.id+";";
		return 0;
	}

	public List<Order> retrieve() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM \"order\"";
		
		ResultSet set = db.executeQuery(sql);

		ArrayList<Order> orders = new ArrayList<Order>();

		try {
			while (set.next()) {

				Order order = new Order();

				// Read the row from ResultSet and put the data into User Object
				order.id = set.getInt("id");
				order.classifiedId = set.getInt("classifiedId");
				order.fromUserId = set.getInt("fromUserId");
				order.toUserId = set.getInt("toUserId");
				order.proposedPrice = set.getInt("proposedPrice");
				order.status = set.getInt("status");
				order.lastUpdatedOn = set.getString("lastUpdatedOn");

				orders.add(order);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return orders;
		
	}

	public List<Order> retrieve(String sql) {
		// TODO Auto-generated method stub
		ResultSet set = db.executeQuery(sql);

		ArrayList<Order> orders = new ArrayList<Order>();

		try {
			while (set.next()) {

				Order order = new Order();

				// Read the row from ResultSet and put the data into User Object
				order.id = set.getInt("id");
				order.classifiedId = set.getInt("classifiedId");
				order.fromUserId = set.getInt("fromUserId");
				order.toUserId = set.getInt("toUserId");
				order.proposedPrice = set.getInt("proposedPrice");
				order.status = set.getInt("status");
				order.lastUpdatedOn = set.getString("lastUpdatedOn");

				orders.add(order);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return orders;
	}



}
