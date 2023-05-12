package com.amazon.internalclassifieds.model;

public class Order {
	
	public int id;
	public int classifiedId;
	public int fromUserId;
	public int toUserId;
	public int proposedPrice;
	public String lastUpdatedOn;
	public int status;	// 0: Requested for Purchase, 
					    // 1: Approved / Agreed to sell
						// 2: Rejected / Not interested to sell.
						// 3: Payment Processed & Product Sold.
	public Order(int id, int classifiedId, int fromUserId, int toUserId, int proposedPrice, String lastUpdatedOn,
			int status) {
		super();
		this.id = id;
		this.classifiedId = classifiedId;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.proposedPrice = proposedPrice;
		this.lastUpdatedOn = lastUpdatedOn;
		this.status = status;
	}
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStatus(int status) {
		
		if(status == 0) {
			return "Requested for Purchase";
		}else if(status == 1) {
			return "Proposal Approved by Seller";
		}else if(status == 2) {
			return "Proposal Rejected by Seller";
		}else if(status == 3) {
			return "Payment Processed & Product Sold Successfully...";
		}
		return "Invalid Status";
		
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", classifiedId=" + classifiedId + ", fromUserId=" + fromUserId + ", toUserId="
				+ toUserId + ", proposedPrice=" + proposedPrice + ", lastUpdatedOn=" + lastUpdatedOn + ", status="
				+ getStatus(status) + "]";
	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Order ID:\t" + id);
		System.out.println("Classified ID:\t" + classifiedId);
		System.out.println("From User ID:\t" + fromUserId);
		System.out.println("To User ID:\t" + toUserId);
		System.out.println("Proposed Price:\t" + proposedPrice);
		System.out.println("Order Status:\t" + getStatus(status));
		System.out.println("Updated On:\t" + lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassifiedId() {
		return classifiedId;
	}

	public void setClassifiedId(int classifiedId) {
		this.classifiedId = classifiedId;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}

	public int getProposedPrice() {
		return proposedPrice;
	}

	public void setProposedPrice(int proposedPrice) {
		this.proposedPrice = proposedPrice;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	
	
	

}
