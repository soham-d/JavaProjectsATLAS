package com.amazon.internalclassifieds.model;

public class User {

	// Attributes
	public int id;
	public String name;
	public String phone;
	public String email;
	public String password;
	public String address;
	public String department;
	public int type; // 1 = Admin User, 2 = Normal User
	public int status; // 1 = Active, 2 = Inactive
	public String lastUpdatedOn;

	public User() {

	}



	public User(int id, String name, String phone, String email, String password, String address, String department,
			int type, int status, String lastUpdatedOn) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.department = department;
		this.type = type;
		this.status = status;
		this.lastUpdatedOn = lastUpdatedOn;
	}



	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("User ID:\t" + id);
		System.out.println("Name:\t\t" + name);
		System.out.println("Phone:\t\t" + phone);
		System.out.println("Email:\t\t" + email);
		System.out.println("Address:\t" + address);
		System.out.println("Department:\t" + department);
		System.out.println("Type:\t\t" + ((type==1)?"Admin User":"User"));
		System.out.println("Status:\t\t" + ((status==1)?"Active":"Inactive"));
		System.out.println("lastUpdatedOn:\t" + lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", department=" + department + ", type=" + ((type==1)?"Admin User":"User") + ", status=" + ((status==1)?"Active":"Inactive")
				+ ", lastUpdatedOn=" + lastUpdatedOn + "]";
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}



	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	


}
