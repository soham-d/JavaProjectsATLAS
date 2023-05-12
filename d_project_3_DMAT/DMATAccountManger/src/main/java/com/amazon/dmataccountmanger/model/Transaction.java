package com.amazon.dmataccountmanger.model;

public class Transaction {
	
	public int id;
	public int userId;
	public int shareId;
	public int shareCount;
	public float pricePerShare;
	public String transactedOn; 
	public float transactionCharges;
	public float sttCharges;
	public int type;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id,int userId, int shareId, int shareCount, float pricePerShare, String transactedOn,
			float transactionCharges, float sttCharges, int type) {
		super();
		this.id = id;
		this.userId = userId;
		this.shareId = shareId;
		this.shareCount = shareCount;
		this.pricePerShare = pricePerShare;
		this.transactedOn = transactedOn;
		this.transactionCharges = transactionCharges;
		this.sttCharges = sttCharges;
		this.type = type;
	}
	
	public void prettyPrint() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("ID :\t\t\t"+id);
		System.out.println("User ID :\t\t"+userId);
		System.out.println("share ID:\t\t"+shareId);
		System.out.println("Share Count:\t\t"+shareCount);
		System.out.println("Price Per Share\t\t"+pricePerShare);
		System.out.println("Transacted On\t\t"+transactedOn);
		System.out.println("Transaction Charges\t"+transactionCharges);
		System.out.println("STT Charges\t\t"+sttCharges);
		String status =  (type == 1) ? "BUY" : "SELL";
		System.out.println("Transaction type\t"+status);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	

}
