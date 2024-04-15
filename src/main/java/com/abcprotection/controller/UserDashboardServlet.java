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
			response.sendRedirect("login");
			return;
		}

		User user = (User) session.getAttribute("user");
		List<Registration> registrations = registrationDAO.getAllRegistrationsUser(user.getUsername());
		ClaimDAO claimDAO = new ClaimDAO();

		for (Registration reg : registrations) {
			List<Claim> claims = claimDAO.getClaimsByRegistrationId(reg.getRegistrationId());
			reg.setClaims(claims);
		}

		request.setAttribute("productsList", registrations);
		request.getRequestDispatcher("/WEB-INF/views/user/dashboard.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
