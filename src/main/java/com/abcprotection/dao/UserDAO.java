package com.abcprotection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abcprotection.model.User;
import com.abcprotection.util.DBConnectionUtil;

/**
 * DAO class for handling database operations related to users.
 */
public class UserDAO {

	private Connection conn;

	public UserDAO() {
		conn = DBConnectionUtil.getConnection();
	}

	public boolean addUser(User user) {
		String sql = "INSERT INTO Users (username, password, cellphone_no, email, name, address) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getCellphoneNo());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getName());
			pstmt.setString(6, user.getAddress());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getUserById(int userId) {
		String sql = "SELECT * FROM Users WHERE user_id = ?";
		User user = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
						rs.getString("cellphone_no"), rs.getString("email"), rs.getString("name"),
						rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(User user) {
		String sql = "UPDATE Users SET username = ?, cellphone_no = ?, email = ?, name = ?, address = ? WHERE user_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getUsername());
//			pstmt.setString(2, user.getPassword());
			pstmt.setString(2, user.getCellphoneNo());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getAddress());
			pstmt.setInt(6, user.getUserId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(int userId) {
		String sql = "DELETE FROM Users WHERE user_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM Users where role != 'admin'";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
						rs.getString("cellphone_no"), rs.getString("email"), rs.getString("name"),
						rs.getString("address"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
