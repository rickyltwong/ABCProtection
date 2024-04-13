package com.abcprotection.model;

/**
 * Model class for User. Represents a user entity as per the database schema.
 */
public class User {
	private int userId;
	private String username;
	private String password;
	private String cellphoneNo;
	private String email;
	private String name;
	private String address;

	public User() {
	}

	public User(int userId, String username, String password, String cellphoneNo, String email, String name,
			String address) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.cellphoneNo = cellphoneNo;
		this.email = email;
		this.name = name;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphoneNo() {
		return cellphoneNo;
	}

	public void setCellphoneNo(String cellphoneNo) {
		this.cellphoneNo = cellphoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", username='" + username + '\'' + ", password='" + password + '\''
				+ ", cellphoneNo='" + cellphoneNo + '\'' + ", email='" + email + '\'' + ", name='" + name + '\''
				+ ", address='" + address + '\'' + '}';
	}
}
