package com.amazon.internalclassifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.internalclassifieds.ClassifiedsSessionUser;
import com.amazon.internalclassifieds.model.Classifieds;

public class ClassifiedsDAO implements DAO<Classifieds> {
	
	DB db = DB.getInstance();
	
	
	public boolean checkObjectValidity(Classifieds object) {
		
		object.userId = ClassifiedsSessionUser.user.id;
		
		/*
		This method checks validty in of the classifed with respect to given constraints on attribute length.
		*/
		//System.out.println("IN the validity chekcer...");
		
		
		//Checking for length of String constraints
		if (object.brand.length() > 25) return false;
		if (object.headLine.length() > 100 ) return false;
		if (object.productName.length() > 50) return false;
		if (object.description.length() > 500) return false;
		
		//Checking count of pictures
		String picLinks = object.getPictures();
		String[] picArr = picLinks.split(" ");
		
		if(picArr.length > 4 || object.pictures.length()>500) {
			System.err.println("Maximum picture count of 4 / total length of 500 chars exceeded...");
			return false;
		}
	
		//If all checks are passed, return true.
		return true;
	}
	
	public int insert(Classifieds object) {
		String sql = "INSERT INTO classifieds (categoryId,status,headline,productName,brand,condition,description,price,pictures,userid) VALUES"
				+ " ('" + object.categoryId + "',"+object.status+",'"+object.headLine+"'"
						+ ",'"+object.productName+"','"+object.brand+"',"+object.condition+","
				+ "'"+object.description+"','"+object.price+"','"+object.pictures+"',"+ClassifiedsSessionUser.user.id+")";
		
		if(checkObjectValidity(object)) { 
			return db.executeSQL(sql);
		}else {
			return 0;
		}
	}

	public int update(Classifieds object) {
		String sql = "UPDATE classifieds  set categoryId='" + object.categoryId + "',status="+object.status+","
				+ "headline='"+object.headLine+"',"
						+ "productName='"+object.productName+"',brand='"+object.brand+"',"
								+ "condition="+object.condition+",description='"+object.description+"'"
										+ ",pictures='"+object.pictures+"',price='"+object.price+"',userid="+ClassifiedsSessionUser.user.id+" WHERE id = " + object.id + "";
		if(checkObjectValidity(object)) { 
			return db.executeSQL(sql);
		}else {
			return 0;
		}
	}

	public int delete(Classifieds object) {
		String sql = "DELETE FROM classifieds WHERE id ='" + object.id + "'";
		return db.executeSQL(sql);
	}

	public List<Classifieds> retrieve() {
		String sql = "SELECT * from classifieds";

		ResultSet set = db.executeQuery(sql);

		ArrayList<Classifieds> classifieds = new ArrayList<Classifieds>();

		try {
			while (set.next()) {

				Classifieds classified = new Classifieds();

				// Read the row from ResultSet and put the data into User Object
				classified.id = set.getInt("id");
				classified.categoryId = set.getInt("categoryId");
				classified.headLine = set.getString("headline");
				classified.productName = set.getString("productName");
				classified.brand = set.getString("brand");
				classified.description = set.getString("description");
				classified.status = set.getInt("status");
				classified.condition = set.getInt("condition");
				classified.pictures = set.getString("pictures");
				classified.price = set.getString("price");
				classified.userId = set.getInt("userId");
				classified.lastUpdatedOn = set.getString("lastUpdatedOn");

				classifieds.add(classified);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return classifieds;
	}


	public List<Classifieds> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);

		ArrayList<Classifieds> classifieds = new ArrayList<Classifieds>();

		try {
			while (set.next()) {

				Classifieds classified = new Classifieds();

				// Read the row from ResultSet and put the data into User Object
				classified.id = set.getInt("id");
				classified.categoryId = set.getInt("categoryId");
				classified.headLine = set.getString("headline");
				classified.productName = set.getString("productName");
				classified.brand = set.getString("brand");
				classified.description = set.getString("description");
				classified.status = set.getInt("status");
				classified.condition = set.getInt("condition");
				classified.pictures = set.getString("pictures");
				classified.price = set.getString("price");
				classified.userId = set.getInt("userId");
				classified.lastUpdatedOn = set.getString("lastUpdatedOn");

				classifieds.add(classified);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return classifieds;
	}

}

