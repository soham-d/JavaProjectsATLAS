package com.amazon.internalclassifieds.model;

public class Category {
	
	public int id;
	public String title;
	public String lastUpdatedOn;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int id, String title, String lastUpdatedOn) {
		super();
		this.id = id;
		this.title = title;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public void prettyPrint() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Category ID:\t\t" + id);
		System.out.println("Category:\t\t" + title);
		System.out.println("Last Updated On:\t" + lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", lastUpdatedOn=" + lastUpdatedOn + "]";
	}

	
	
	
}
