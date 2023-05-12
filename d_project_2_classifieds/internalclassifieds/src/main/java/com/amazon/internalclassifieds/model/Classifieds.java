package com.amazon.internalclassifieds.model;

import com.amazon.internalclassifieds.controller.ClassifiedsService;

public class Classifieds {
	
	public int id;
	public int categoryId;
	public int userId;
	public int status;
	// Status ->
	//	status -> 0 pending
	//	status -> 1 approved
	//	status -> 2 rejected
	//	status -> 3 -> sold :)
	
	public String headLine;
	public String productName;
	public String brand;
	public int condition;
	// condition -> 
	//1. Brand New(Seal Packed), 
	//2. Lightly Used, 
	//3. Moderately Used, 
	//4. Heavily Used, 
	//5. Damaged/Dented, 
	//6. Not Working
	
	public String description;
	public String price; //(If recurring, mention recurrence unit. For example, if it is an apartment for
						 // rent, mention the price as Rs. 20,000 per month)
	public String pictures;
	public String lastUpdatedOn;
	
	
	public Classifieds() {
		// TODO Auto-generated constructor stub
	}


	public Classifieds(int id, int categoryId, int status, String headLine, String productName, String brand,
			int condition, String description, String price, String pictures,int userId, String lastUpdatedOn) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.userId = userId;
		this.status = status;
		this.headLine = headLine;
		this.productName = productName;
		this.brand = brand;
		this.condition = condition;
		this.description = description;
		this.price = price;
		this.pictures = pictures;
		this.lastUpdatedOn = lastUpdatedOn;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Classifieds [id=" + id + ", categoryId=" + categoryId + ", status=" + status + ", headLine=" + headLine
				+ ", productName=" + productName + ", brand=" + brand + ", condition=" + condition + ", description="
				+ description + ", price=" + price + ", pictures=" + pictures + ", lastUpdatedOn=" + lastUpdatedOn
				+ "]";
	}
	
	public String getCategory(int categoryId) {
		
		if(categoryId==1) {
			return "Furniture";
		}else if(categoryId == 2) {
			return "Electronics";
		}else if(categoryId == 3) {
			return "Vehicles";
		}else if(categoryId == 4) {
			return "Event Tickets";
		}else if(categoryId == 5) {
			return "Real Esate";
		}else {
			return "Others";
		}
	}
	
	public String getStatuString(int status) {
		
		/*
		 * status -> 0 pending 
		 * status -> 1 approved 
		 * status -> 2 rejected 
		 * status -> 3 ->
		 * sold :)
		 */
		
		if(status == 0) {
			return "Pending";
		}else if(status == 1) {
			return "Approved";
		}else if(status == 2) {
			return "Rejected";
		}else if(status == 3) {
			return "Sold";
		}
		return "";
	}
	
	public String getConditionString(int condition) {
		// condition -> Brand New(Seal Packed), Lightly Used, Moderately Used, Heavily Used, Damaged/Dented, Not Working
		
		if(condition == 1) {
			return "Brand New (Seal Pakced)";
		}else if(condition == 2) {
			return "Lightly Used";
		}else if(condition == 3) {
			return "Moderately Used";
		}else if(condition == 4) {
			return "Heavily Used";
		}else if(condition == 5) {
			return "Damaged/Dented";
		}else if(condition == 6) {
			return "Not Working";
		}
		
		return "";
	}
	
	public void prettyPrint() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Classified ID \t\t"+id);
		System.out.println("Category\t\t"+getCategory(categoryId));
		System.out.println("Headline:\t\t" + headLine);
		System.out.println("Status: \t\t"+getStatuString(status));
		System.out.println("Brand:\t\t\t" + brand);
		System.out.println("Price:\t\t\t" + price);
		System.out.println("Description:\t\t" + description);
		System.out.println("Condition:\t\t" +getConditionString(condition));
		System.out.println("Picture:\t\t" + pictures);
		System.out.println("Posted by User ID: \t"+userId);
		System.out.println("Last Updated On:\t" + lastUpdatedOn);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getHeadLine() {
		return headLine;
	}


	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public int getCondition() {
		return condition;
	}


	public void setCondition(int condition) {
		this.condition = condition;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getPictures() {
		return pictures;
	}


	public void setPictures(String pictures) {
		this.pictures = pictures;
	}


	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}


	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	
	

}
