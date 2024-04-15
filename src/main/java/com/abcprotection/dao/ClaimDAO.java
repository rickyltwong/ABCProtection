package com.abcprotection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
		String sql = "INSERT INTO Claims (registration_id, date_of_claim, description) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, claim.getRegistrationId());
			java.sql.Date sqlDate = new java.sql.Date(claim.getDateOfClaim().getTime());
			pstmt.setDate(2, sqlDate);
			pstmt.setString(3, claim.getDescription());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Claim> getClaimsByRegistrationIds(List<Integer> registrationIds) throws SQLException {
		if (registrationIds == null || registrationIds.isEmpty()) {
			return new ArrayList<>(); // Return an empty list if no IDs are provided
		}

		// Convert list of integers to comma-separated string for SQL IN clause
		String ids = registrationIds.stream().map(Object::toString).collect(Collectors.joining(", "));

		List<Claim> claims = new ArrayList<>();
		String sql = "SELECT * FROM Claims WHERE registration_id IN (" + ids + ")";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int claimId = rs.getInt("claim_id");
				int registrationId = rs.getInt("registration_id");
				Date dateOfClaim = rs.getDate("date_of_claim");
				String description = rs.getString("description");
				String status = rs.getString("status");
				claims.add(new Claim(claimId, registrationId, dateOfClaim, description, status));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Consider more sophisticated error handling
			throw e; // Rethrow to let the caller handle it
		}
		return claims;
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

	public List<Claim> getClaimByStatus(String status) {
		List<Claim> claims = new ArrayList<>();
		String sql = "SELECT * FROM Claims WHERE status = ?";
		Claim claim = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				claim = new Claim(rs.getInt("claim_id"), rs.getInt("registration_id"), rs.getDate("date_of_claim"),
						rs.getString("description"), rs.getString("status"));
				claims.add(claim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return claims;
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

	public boolean updateClaimStatus(int id, String status) {
		String sql = "UPDATE Claims SET status = ? WHERE claim_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, status);
			pstmt.setInt(2, id);
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

	public List<Claim> getAllClaimsUser(String username) {
		List<Claim> claims = new ArrayList<>();
		String sql = "SELECT * FROM Claims c JOIN Registrations r ON c.registration_id = r.registration_id WHERE r.username = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
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

	public List<Claim> getClaimsByRegistrationId(int registrationId) {
		List<Claim> claims = new ArrayList<>();
		String sql = "SELECT * FROM Claims WHERE registration_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, registrationId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Claim claim = new Claim();
				claim.setClaimId(rs.getInt("claim_id"));
				claim.setRegistrationId(rs.getInt("registration_id"));
				claim.setDateOfClaim(rs.getDate("date_of_claim"));
				claim.setDescription(rs.getString("description"));
				claim.setStatus(rs.getString("status"));
				claims.add(claim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return claims;
	}

	public List<Claim> getRecentClaimsByRegistrationId(int registrationId) {
		List<Claim> claims = new ArrayList<>();
		String sql = "SELECT * FROM Claims WHERE registration_id = ? AND date_of_claim >= ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, registrationId);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -5);
			pstmt.setDate(2, new java.sql.Date(cal.getTimeInMillis()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Claim claim = new Claim();
				claim.setClaimId(rs.getInt("claim_id"));
				claim.setRegistrationId(rs.getInt("registration_id"));
				claim.setDateOfClaim(rs.getDate("date_of_claim"));
				claim.setDescription(rs.getString("description"));
				claim.setStatus(rs.getString("status"));
				claims.add(claim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return claims;
	}

}
