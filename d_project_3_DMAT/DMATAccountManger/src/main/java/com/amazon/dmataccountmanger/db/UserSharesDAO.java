package com.amazon.dmataccountmanger.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanger.model.UserShares;

public class UserSharesDAO implements DAO<UserShares> {


	DB db = DB.getInstance();

	public int insert(UserShares object) {
		String sql = "INSERT INTO usershares (userid, shareid,sharecount,companyname) VALUES (" + object.userId + ", " + object.shareId + ","+object.shareCount+",'"+object.companyName+"');";
		return db.executeSQL(sql);
	}

	public int update(UserShares object) {
		String sql = "UPDATE usershares set userid =" + object.userId + ", shareid=" + object.shareId + ""
				+ "sharecount=" + object.shareCount +", companyName='"+object.companyName+"' where id = "+object.id+";";
		return db.executeSQL(sql);
	}

	public int delete(UserShares object) {
		String sql = "DELETE FROM usershares WHERE id = '" + object.id + "'";
		return db.executeSQL(sql);
	}

	public List<UserShares> retrieve() {
		String sql = "SELECT * from UserShares";

		ResultSet set = db.executeQuery(sql);

		ArrayList<UserShares> usershares = new ArrayList<UserShares>();

		try {
			while (set.next()) {

				UserShares ushare = new UserShares();

				// Read the row from ResultSet and put the data into User Object
				ushare.id = set.getInt("id");
				ushare.userId = set.getInt("userid");
				ushare.shareId = set.getInt("shareid");
				ushare.shareCount = set.getInt("sharecount");
				ushare.companyName = set.getString("companyName");

				usershares.add(ushare);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return usershares;
	}

	public List<UserShares> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);

		ArrayList<UserShares> usershares = new ArrayList<UserShares>();

		try {
			while (set.next()) {

				UserShares ushare = new UserShares();

				// Read the row from ResultSet and put the data into User Object
				ushare.id = set.getInt("id");
				ushare.userId = set.getInt("userid");
				ushare.shareId = set.getInt("shareid");
				ushare.shareCount = set.getInt("sharecount");
				ushare.companyName = set.getString("companyName");

				usershares.add(ushare);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return usershares;
	}


}
