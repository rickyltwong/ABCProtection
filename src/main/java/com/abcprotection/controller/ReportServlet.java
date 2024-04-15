package com.abcprotection.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abcprotection.dao.ClaimDAO;
import com.abcprotection.dao.RegistrationDAO;
import com.abcprotection.dao.UserDAO;
import com.abcprotection.model.User;
import com.abcprotection.model.Claim;
import com.abcprotection.model.Registration;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/admin/reports")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private RegistrationDAO registrationDAO;
	private ClaimDAO claimDAO;
	// Initialization of DAO
    public void init() {
        userDAO = new UserDAO(); 
        registrationDAO = new RegistrationDAO();
        claimDAO = new ClaimDAO();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   String userId = request.getParameter("userId");
		    if (userId == null || userId.isBlank()) {
		        request.setAttribute("errorMessage", "User ID is required.");
		        request.getRequestDispatcher("/admin/dashboard?section=reports").forward(request, response);
		        return;
		    }

		    try {
		        int parsedUserId = Integer.parseInt(userId);
		        User user = userDAO.getUserById(parsedUserId);

		        if (user != null) {
		            List<Registration> registrations = registrationDAO.getAllRegistrationsUser(user.getUsername());
		            List<Integer> registrationIds = registrations.stream().map(Registration::getRegistrationId).collect(Collectors.toList());
		            List<Claim> claims = claimDAO.getClaimsByRegistrationIds(registrationIds);

		            request.setAttribute("user", user);
		            request.setAttribute("products", registrations);
		            request.setAttribute("claims", claims);
		        } else {
		            request.setAttribute("errorMessage", "No user found with ID: " + userId);
		        }
		    } catch (NumberFormatException e) {
		        request.setAttribute("errorMessage", "Invalid User ID format.");
		    } catch (SQLException e) {
		        request.setAttribute("errorMessage", "Database error occurred.");
		        e.printStackTrace(); // Log to server logs
		    }

		    request.getRequestDispatcher("/admin/dashboard?section=reports").forward(request, response);
	}

}
