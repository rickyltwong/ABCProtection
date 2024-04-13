package com.abcprotection.model;

import java.util.Date;

/**
 * Model class for Claim. Represents a claim entity as per the database schema.
 */
public class Claim {
	private int claimId;
	private int registrationId;
	private Date dateOfClaim;
	private String description;
	private String status; // Status of the claim (Pending, Approved, Rejected)

	public Claim() {
	}

	public Claim(int claimId, int registrationId, Date dateOfClaim, String description, String status) {
		this.claimId = claimId;
		this.registrationId = registrationId;
		this.dateOfClaim = dateOfClaim;
		this.description = description;
		this.status = status;
	}

	// Getters and Setters
	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public Date getDateOfClaim() {
		return dateOfClaim;
	}

	public void setDateOfClaim(Date dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Claim{" + "claimId=" + claimId + ", registrationId=" + registrationId + ", dateOfClaim=" + dateOfClaim
				+ ", description='" + description + '\'' + ", status='" + status + '\'' + '}';
	}
}
