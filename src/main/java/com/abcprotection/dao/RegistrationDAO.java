package com.abcprotection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abcprotection.model.Registration;
import com.abcprotection.util.DBConnectionUtil;

/**
 * DAO class for handling database operations related to registrations.
 */
public class RegistrationDAO {

	private Connection conn;

	public RegistrationDAO() {
		conn = DBConnectionUtil.getConnection();
	}

	public boolean addRegistration(Registration registration) {
		String sql = "INSERT INTO Registrations (username, product_name, serial_no, purchase_date) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, registration.getUsername());
			pstmt.setString(2, registration.getProductName());
			pstmt.setString(3, registration.getSerialNo());
			pstmt.setDate(4, new java.sql.Date(registration.getPurchaseDate().getTime()));
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Registration> getAllRegistrations() {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM Registrations";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Registration registration = new Registration(rs.getInt("registration_id"), rs.getString("username"),
						rs.getString("product_name"), rs.getString("serial_no"), rs.getDate("purchase_date"));
				registrations.add(registration);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registrations;
	}

	public boolean updateRegistration(Registration registration) {
		String sql = "UPDATE Registrations SET user_id = ?, product_id = ?, serial_no = ?, purchase_date = ? WHERE registration_id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, registration.getUsername());
			pstmt.setString(2, registration.getProductName());
			pstmt.setString(3, registration.getSerialNo());
			pstmt.setDate(4, new java.sql.Date(registration.getPurchaseDate().getTime()));
			pstmt.setInt(5, registration.getRegistrationId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteRegistration(int registrationId) {
		String sql = "DELETE FROM Registrations WHERE registration_id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, registrationId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Registration> getAllRegistrationsUser(String username) {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM Registrations WHERE username = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Registration registration = new Registration(rs.getInt("registration_id"), rs.getString("username"),
						rs.getString("product_name"), rs.getString("serial_no"), rs.getDate("purchase_date"));
				registrations.add(registration);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registrations;
	}
}
