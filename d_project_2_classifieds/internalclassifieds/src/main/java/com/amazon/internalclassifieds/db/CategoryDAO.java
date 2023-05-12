package com.amazon.internalclassifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.internalclassifieds.model.Category;

public class CategoryDAO implements DAO<Category> {
	
	DB db = DB.getInstance();


	public int insert(Category object) {
		String sql = "INSERT INTO category (title) VALUES ('" + object.title + "')";
		return db.executeSQL(sql);

	}

	public int update(Category object) {
		String sql = "UPDATE category set name = '" + object.title + "' WHERE id = '" + object.id + "'";
		return db.executeSQL(sql);
	}

	public int delete(Category object) {
		String sql = "DELETE FROM category WHERE id = '" + object.id + "'";
		return db.executeSQL(sql);
	}

	public List<Category> retrieve() {
		String sql = "SELECT * from category";

		ResultSet set = db.executeQuery(sql);

		ArrayList<Category> categories = new ArrayList<Category>();

		try {
			while (set.next()) {

				Category category = new Category();

				// Read the row from ResultSet and put the data into User Object
				category.id = set.getInt("id");
				category.title = set.getString("title");
				category.lastUpdatedOn = set.getString("lastUpdatedOn");

				categories.add(category);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return categories;
	}

	public List<Category> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);

		ArrayList<Category> categories = new ArrayList<Category>();

		try {
			while (set.next()) {

				Category category = new Category();

				// Read the row from ResultSet and put the data into User Object
				category.id = set.getInt("id");
				category.title = set.getString("title");
				category.lastUpdatedOn = set.getString("lastUpdatedOn");

				categories.add(category);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: " + e);
		}

		return categories;
	}

}
