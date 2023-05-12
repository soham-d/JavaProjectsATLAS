package com.amazon.dmataccountmanger.model;

public class Share {
	
	public int id;
	public String symbol;
	public String companyName;
	public float price;
	public String lastUpdatedOn;
	
	public Share() {
		// TODO Auto-generated constructor stub
	}

	public Share(int id, String symbol, String companyName, float price, String lastUpdatedOn) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.companyName = companyName;
		this.price = price;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public void prettyPrint() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("ID:\t\t"+id);
		System.out.println("Symbol:\t\t"+symbol);
		System.out.println("Company Name:\t"+"Rs. "+companyName);
		System.out.println("Price:\t\t"+price);
		System.out.println("Last Updated:\t"+lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
