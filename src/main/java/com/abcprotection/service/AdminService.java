package com.abcprotection.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.abcprotection.util.DBConnectionUtil;

public class AdminService {
    public static boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        // Check if user exists and has admin role
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? AND role = 'admin'";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // returns true if a row exists (admin user found)
            try (ResultSet rs = pstmt.executeQuery()) {                 
                isAuthenticated = rs.next();  
            } 

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }  

        return isAuthenticated;
    }
}
