package com.abcprotection.model;

/**
 * Model class for Product. Represents a product entity as per the database
 * schema.
 */
public class Product {
	private int productId;
	private String productName;
	private String model;

	public Product() {
	}

	public Product(int productId, String productName, String model) {
		this.productId = productId;
		this.productName = productName;
		this.model = model;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Product{" + "productId=" + productId + ", productName='" + productName + '\'' + ", model='" + model
				+ '\'' + '}';
	}
}
