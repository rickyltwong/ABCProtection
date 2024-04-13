package com.abcprotection.model;

import java.util.Date;

/**
 * Model class for Registrations. Represents a registration of a product by a
 * user.
 */
public class Registration {
	private int registrationId;
	private int userId;
	private int productId;
	private String serialNo;
	private Date purchaseDate;

	public Registration() {
	}

	public Registration(int registrationId, int userId, int productId, String serialNo, Date purchaseDate) {
		this.registrationId = registrationId;
		this.userId = userId;
		this.productId = productId;
		this.serialNo = serialNo;
		this.purchaseDate = purchaseDate;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
