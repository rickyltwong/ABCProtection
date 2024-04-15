package com.abcprotection.model;

import java.util.Date;
import java.util.List;

/**
 * Model class for Registrations. Represents a registration of a product by a
 * user. Includes claims related to the registration.
 */
public class Registration {
	private int registrationId;
	private String username;
	private String productName;
	private String serialNo;
	private Date purchaseDate;
	private List<Claim> claims; // List to hold associated claims

	public Registration() {
	}

	public Registration(int registrationId, String username, String productName, String serialNo, Date purchaseDate) {
		this.registrationId = registrationId;
		this.username = username;
		this.productName = productName;
		this.serialNo = serialNo;
		this.purchaseDate = purchaseDate;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims; // Set the claims associated with this registration
	}
}
