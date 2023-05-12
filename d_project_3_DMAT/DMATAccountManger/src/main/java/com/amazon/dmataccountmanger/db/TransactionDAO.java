package com.amazon.dmataccountmanger.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanger.model.Transaction;

public class TransactionDAO implements DAO<Transaction> {
	
	DB db = DB.getInstance();

	public int insert(Transaction object) {
		
		//id	shareid	sharecount	pricePerShare	transactedOn	transactionCharges	sttCharges	type
		String sql = "INSERT INTO \"Transaction\" (userid,shareid,sharecount,pricePerShare,transactionCharges,sttCharges,type) "
				+ "VALUES ("+object.userId+","+object.shareId+","+object.shareCount+","
						+ ""+object.pricePerShare+","+object.transactionCharges+","+object.sttCharges+","+object.type+")";
		return db.executeSQL(sql);
	}

	public int update(Transaction object) {
		String sql = "UPDATE \"Transaction\"share set shareid = "+object.shareCount+", "
				+ "shareCount = "+object.shareCount+", pricepershare ="+object.pricePerShare+","
						+ "transactionCharges ="+object.transactionCharges+", sttCharges = "+object.sttCharges+","
								+ "type = "+object.type+", userId="+object.userId+" where id ="+object.id+";";
		
		return db.executeSQL(sql);
	}

	public int delete(Transaction object) {
		// TODO Auto-generated method stub
		String sql = "DELETE from \"Transaction\" WHERE id="+object.id+";";
		return db.executeSQL(sql);
	}
	
	public List<Transaction> retrieve() {
		String sql = "SELECT * from transaction";

		ResultSet set = db.executeQuery(sql);

		ArrayList<Transaction> transactions = new ArrayList<Transaction>();

		try {
			while (set.next()) {

				Transaction transaction = new Transaction();

				// Read the row from ResultSet and put the data into User Object

				transaction.id = set.getInt("id");
				transaction.userId = set.getInt("userId");
				transaction.shareId = set.getInt("shareid");
				transaction.shareCount = set.getInt("sharecount");
				transaction.pricePerShare = set.getFloat("pricepershare");
				transaction.transactedOn = set.getString("transactedon");
				transaction.transactionCharges = set.getFloat("transactioncharges");
				transaction.sttCharges = set.getFloat("sttcharges");
				transaction.type = set.getInt("type");

				transactions.add(transaction);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return transactions;
	}

	
	

	public List<Transaction> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);

		ArrayList<Transaction> transactions = new ArrayList<Transaction>();

		try {
			while (set.next()) {

				Transaction transaction = new Transaction();

				// Read the row from ResultSet and put the data into User Object

				transaction.id = set.getInt("id");
				transaction.userId = set.getInt("userId");
				transaction.shareId = set.getInt("shareid");
				transaction.shareCount = set.getInt("sharecount");
				transaction.pricePerShare = set.getFloat("pricepershare");
				transaction.transactedOn = set.getString("transactedon");
				transaction.transactionCharges = set.getFloat("transactioncharges");
				transaction.sttCharges = set.getFloat("sttcharges");
				transaction.type = set.getInt("type");

				transactions.add(transaction);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return transactions;
	}

}