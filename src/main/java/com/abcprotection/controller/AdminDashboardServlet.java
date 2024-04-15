package com.abcprotection.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abcprotection.dao.ClaimDAO;
import com.abcprotection.dao.ProductDAO;
import com.abcprotection.dao.UserDAO;
import com.abcprotection.model.Claim;
import com.abcprotection.model.Product;
import com.abcprotection.model.User;

/**
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	private ProductDAO productDAO;
	private ClaimDAO claimDAO;
	// Initialization of DAO
    public void init() {
        userDAO = new UserDAO(); 
        productDAO = new ProductDAO();
        claimDAO = new ClaimDAO();
    }

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false); // Get session if exists, do not create a new one
        if (session != null && session.getAttribute("admin") != null) {
            // Session exists and user has been authenticated as admin
        	String section = request.getParameter("section");
        	
        	
        	if ("users".equals(section)) {
        		String searchUser = request.getParameter("searchUser");
        		List<User> users = new ArrayList<>();
                if (searchUser != null && !searchUser.isBlank()) {
                    User user = userDAO.getUserById(Integer.parseInt(searchUser));  
                    if (user != null) {
                    	users.add(user);
                    } 
                } else {
                	users = userDAO.getAllUsers();
                	
                }
                request.setAttribute("usersList", users);
                if (users.isEmpty()) {
                	request.setAttribute("errorMessage", "User not found!");
                }
            } else if ("products".equals(section)) {
            	String searchProduct = request.getParameter("searchProduct");
            	List<Product> products = new ArrayList<>();
            	if (searchProduct != null && !searchProduct.isBlank()) {
                    Product product = productDAO.getProductByName(searchProduct);
                    if (product != null) {
                    	products.add(product);
                    } 
                } else {
                	products = productDAO.getAllProducts();
                	
                }
                request.setAttribute("products", products);
                if (products.isEmpty()) {
                	request.setAttribute("errorMessage", "Product not found!");
                }
            } else if ("claims".equals(section)) {
            	String claimStatus = request.getParameter("status");
            	List<Claim> claims = new ArrayList<>();
            	if (claimStatus != null && !claimStatus.isBlank()) {
                    claims = claimDAO.getClaimByStatus(claimStatus);
//                    if (claim != null) {
//                    	claims.add(claim);
//                    } 
                } else {
                	claims = claimDAO.getAllClaims();
                	
                }
                request.setAttribute("claims", claims);
                if (claims.isEmpty()) {
                	request.setAttribute("errorMessage", "Claim not found!");
                }
            	
            } 
        	request.setAttribute("section", section);
            request.getRequestDispatcher("/WEB-INF/views/admin/adminDashboard.jsp").forward(request, response);
        } else {
            // No session or not logged in as admin, redirect to login page
            response.sendRedirect(request.getContextPath() + "/admin/login"); // Adjust the URL as per your servlet mapping
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
