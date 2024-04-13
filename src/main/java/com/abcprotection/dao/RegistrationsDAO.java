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
public class RegistrationsDAO {

	private Connection conn;

	public RegistrationsDAO() {
		conn = DBConnectionUtil.getConnection();
	}

	public boolean addRegistration(Registration registration) {
		String sql = "INSERT INTO Registrations (user_id, product_id, serial_no, purchase_date) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, registration.getUserId());
			pstmt.setInt(2, registration.getProductId());
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
				Registration registration = new Registration(rs.getInt("registration_id"), rs.getInt("user_id"),
						rs.getInt("product_id"), rs.getString("serial_no"), rs.getDate("purchase_date"));
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
			pstmt.setInt(1, registration.getUserId());
			pstmt.setInt(2, registration.getProductId());
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
}
