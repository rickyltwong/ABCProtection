package com.abcprotection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abcprotection.model.Claim;
import com.abcprotection.util.DBConnectionUtil;

/**
 * DAO class for handling database operations related to claims.
 */
public class ClaimDAO {

	private Connection conn;

	public ClaimDAO() {
		// Establish a connection to the database
		conn = DBConnectionUtil.getConnection();
	}

	public boolean addClaim(Claim claim) {
		String sql = "INSERT INTO Claims (registration_id, date_of_claim, description, status) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, claim.getRegistrationId());
			pstmt.setDate(2, new java.sql.Date(claim.getDateOfClaim().getTime()));
			pstmt.setString(3, claim.getDescription());
			pstmt.setString(4, claim.getStatus());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Claim getClaimById(int claimId) {
		String sql = "SELECT * FROM Claims WHERE claim_id = ?";
		Claim claim = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, claimId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				claim = new Claim(rs.getInt("claim_id"), rs.getInt("registration_id"), rs.getDate("date_of_claim"),
						rs.getString("description"), rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return claim;
	}

	public boolean updateClaim(Claim claim) {
		String sql = "UPDATE Claims SET registration_id = ?, date_of_claim = ?, description = ?, status = ? WHERE claim_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, claim.getRegistrationId());
			pstmt.setDate(2, new java.sql.Date(claim.getDateOfClaim().getTime()));
			pstmt.setString(3, claim.getDescription());
			pstmt.setString(4, claim.getStatus());
			pstmt.setInt(5, claim.getClaimId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteClaim(int claimId) {
		String sql = "DELETE FROM Claims WHERE claim_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, claimId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Claim> getAllClaims() {
		List<Claim> claims = new ArrayList<>();
		String sql = "SELECT * FROM Claims";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Claim claim = new Claim(rs.getInt("claim_id"), rs.getInt("registration_id"),
						rs.getDate("date_of_claim"), rs.getString("description"), rs.getString("status"));
				claims.add(claim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return claims;
	}
}
