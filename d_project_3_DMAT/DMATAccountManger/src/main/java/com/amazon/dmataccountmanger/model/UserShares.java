package com.amazon.dmataccountmanger.model;

public class UserShares {
	public int id;
	public int userId;
	public int shareId;
	public String companyName;
	public int shareCount;
	
	
	public UserShares() {
		// TODO Auto-generated constructor stub
	}


	public UserShares(int id, int userId, int shareId, String companyName, int shareCount) {
		super();
		this.id = id;
		this.userId = userId;
		this.shareId = shareId;
		this.companyName = companyName;
		this.shareCount = shareCount;
	}
	
	
	public void prettyPrint() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("ID:\t\t"+id);
		System.out.println("User ID:\t"+userId);
		System.out.println("Share ID:\t"+shareId);
		System.out.println("Company Name:\t"+companyName);
		System.out.println("share Count:\t"+shareCount);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
