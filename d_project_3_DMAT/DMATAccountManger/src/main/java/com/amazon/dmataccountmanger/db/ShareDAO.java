package com.amazon.dmataccountmanger.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanger.model.Share;
import com.amazon.dmataccountmanger.model.User;

public class ShareDAO implements DAO<Share>{
	
	DB db = DB.getInstance();

	public int insert(Share object) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO share(symbol, companyName, price) "
				+ "VALUES ('"+object.symbol+"', "+object.companyName+", "+object.price+")";
		return db.executeSQL(sql);
	}

	public int update(Share object) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE share set symbol = '"+object.symbol+"', companyName='"+object.companyName+"', "
				+ "price="+object.price+" WHERE id = "+object.id+";";
		return db.executeSQL(sql);
	}

	public int delete(Share object) {
		String sql = "DELETE FROM share WHERE id = "+object.id+";";
		return db.executeSQL(sql);
	}

	public List<Share> retrieve() {
		// Method to retrieve all shares in the share table.
		String sql = "SELECT * from share";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Share> shares = new ArrayList<Share>();
		
		try {
			while(set.next()) {
				
				Share share = new Share();
				
				// Read the row from ResultSet and put the data into User Object
				
				share.id = set.getInt("id");
				share.symbol = set.getString("symbol");
				share.companyName = set.getString("companyName");
				share.price = set.getFloat("price");
				share.lastUpdatedOn = set.getString("lastUpdatedOn");

				
				shares.add(share);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return shares;

	}

	public List<Share> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Share> shares = new ArrayList<Share>();
		
		try {
			while(set.next()) {
				
				Share share = new Share();
				
				// Read the row from ResultSet and put the data into User Object
				
				share.id = set.getInt("id");
				share.symbol = set.getString("symbol");
				share.companyName = set.getString("companyName");
				share.price = set.getFloat("price");
				share.lastUpdatedOn = set.getString("lastUpdatedOn");

				
				shares.add(share);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return shares;
	}

}
