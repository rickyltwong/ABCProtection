package com.abcprotection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abcprotection.model.Product;
import com.abcprotection.util.DBConnectionUtil;

/**
 * DAO class for handling database operations related to products.
 */
public class ProductDAO {

	private Connection conn;

	public ProductDAO() {
		// Get a connection to the database
		conn = DBConnectionUtil.getConnection();
	}

	public boolean addProduct(Product product) {
		String sql = "INSERT INTO Products (product_name, model) VALUES (?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getModel());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Product getProductById(int productId) {
		String sql = "SELECT * FROM Products WHERE product_id = ?";
		Product product = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, productId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("model"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public boolean updateProduct(Product product) {
		String sql = "UPDATE Products SET product_name = ?, model = ? WHERE product_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getModel());
			pstmt.setInt(3, product.getProductId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteProduct(int productId) {
		String sql = "DELETE FROM Products WHERE product_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM Products";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Product product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("model"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
