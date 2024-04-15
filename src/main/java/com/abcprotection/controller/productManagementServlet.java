package com.abcprotection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abcprotection.dao.ProductDAO;
import com.abcprotection.dao.UserDAO;
import com.abcprotection.model.Product;
import com.abcprotection.model.User;

/**
 * Servlet implementation class productManagementServlet
 */
@WebServlet("/admin/products")
public class productManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;
	// Initialization of DAO
    public void init() {
    	productDAO = new ProductDAO(); 
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Received productId: " + req.getParameter("productId"));
		int productId = Integer.parseInt(req.getParameter("productId"));
		boolean success = productDAO.deleteProduct(productId);
		if (!success) {
			req.setAttribute("errorMessage", "Failed to delete.");
        } 
		resp.sendRedirect(req.getContextPath() + "/admin/dashboard?section=products");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Received productId: " + req.getParameter("productId"));
		int productId = Integer.parseInt(req.getParameter("productId"));
        String productName = req.getParameter("productName");
        String model = req.getParameter("model");
              
        Product product = new Product(productId, productName, model);

        boolean success = productDAO.updateProduct(product);
        if (!success) {
        	req.setAttribute("errorMessage", "Failed to update");
        } 
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard?section=products");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("_method");		

	    if ("PUT".equals(method)) {
	        doPut(request, response);
	    } else if ("DELETE".equals(method)) {
	        doDelete(request, response);
	    } else {
	    	String productName = request.getParameter("addProductName");
	        String model = request.getParameter("addModel");

	        if (productName == null || model == null || productName.isBlank() || model.isBlank()) {
	            // Handle missing parameters error
	            request.setAttribute("errorMessage", "Product name and model are required!");
	            request.getRequestDispatcher(request.getContextPath() + "/admin/dashboard?section=products").forward(request, response);
	            return;
	        }

	        Product product = new Product();  // Assuming you have a default constructor
	        product.setProductName(productName);
	        product.setModel(model);

	        boolean isSuccess = productDAO.addProduct(product);  // Implement this method in your DAO

	        if (isSuccess) {
	            response.sendRedirect(request.getContextPath() + "/admin/dashboard?section=products&success=Product added successfully");
	        } else {
	            // Handle adding product error
	            request.setAttribute("errorMessage", "Failed to add product. Please try again!");
	            request.getRequestDispatcher(request.getContextPath() + "/admin/dashboard?section=products").forward(request, response);
	        }
	    }
	}

}
