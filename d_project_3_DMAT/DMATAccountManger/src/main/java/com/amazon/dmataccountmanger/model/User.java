package com.amazon.dmataccountmanger.model;

public class User {
	
	public int id;
	public String name;
	public int accountNumber;
	public String password;
	public int accountBalance;
	public String lastUpdatedOn;
	
	// Encrypted user123: 6a61f19b124b54829f7ab0a78980058283e6a67813df49b28000e1ecdb96e56c
	
	
	public void prettyPrint() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Name:\t\t\t"+name);
		System.out.println("Account Number:\t\t"+accountNumber);
		System.out.println("Current Balance:\t"+"Rs. "+accountBalance);
		System.out.println("Last Updated On:\t"+lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}


	public User(int id, String name, int accountNumber, String password, int accountBalance, String lastUpdatedOn) {
		super();
		this.id = id;
		this.name = name;
		this.accountNumber = accountNumber;
		this.password = password;
		this.accountBalance = accountBalance;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public User() {
		//Default Constructor
	}
	
	
	

}
