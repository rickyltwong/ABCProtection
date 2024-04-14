package com.abcprotection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abcprotection.dao.ClaimDAO;
import com.abcprotection.dao.RegistrationDAO;
import com.abcprotection.model.Claim;
import com.abcprotection.model.Registration;
import com.abcprotection.model.User;

/**
 * Servlet implementation class UserDashboardServlet
 */
@WebServlet("/UserDashboardServlet")
public class UserDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RegistrationDAO registrationDAO;
	private ClaimDAO claimDAO;

	public UserDashboardServlet() {
		super();
		// Initialize DAOs
		registrationDAO = new RegistrationDAO();
		claimDAO = new ClaimDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			// Not logged in, redirect to the login page
			response.sendRedirect("login");
			return;
		}

		User currentUser = (User) session.getAttribute("user");
		String username = currentUser.getUsername();

		// Fetch registrations for the logged-in user
		List<Registration> productsList = registrationDAO.getAllRegistrationsUser(username);
		List<Claim> claimsList = claimDAO.getAllClaims();

		request.setAttribute("productsList", productsList);
		request.setAttribute("claimsList", claimsList);

		request.getRequestDispatcher("/WEB-INF/views/user/dashboard.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
