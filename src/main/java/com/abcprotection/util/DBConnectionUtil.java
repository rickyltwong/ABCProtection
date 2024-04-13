package com.abcprotection.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing MySQL database connections.
 */
public class DBConnectionUtil {

	// Database parameters
	private static final String URL = "jdbc:mysql://j2ee-db.clrmmf4qzdam.us-east-2.rds.amazonaws.com:3306/abc";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "j2ee-password";

	/**
	 * This method returns a connection to the database.
	 * 
	 * @return A Connection object or null if a connection could not be established.
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("Database Driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * This method closes the given connection to the database.
	 * 
	 * @param conn The connection to close.
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Failed to close connection");
				e.printStackTrace();
			}
		}
	}
}
