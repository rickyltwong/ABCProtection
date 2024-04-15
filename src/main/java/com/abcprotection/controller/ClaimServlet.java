package com.abcprotection.controller;

import java.io.IOException;
import java.util.Date;
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
 * Servlet implementation class ClaimServlet
 */
@WebServlet("/ClaimServlet")
public class ClaimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClaimDAO claimDAO;
	private RegistrationDAO registrationDAO;

	public ClaimServlet() {
		super();
		claimDAO = new ClaimDAO();
		registrationDAO = new RegistrationDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}

		int registrationId = Integer.parseInt(request.getParameter("registrationId"));
		String description = request.getParameter("description");

		Date dateOfClaim = new Date();

		Claim claim = new Claim();
		claim.setRegistrationId(registrationId);
		claim.setDateOfClaim(dateOfClaim);
		claim.setDescription(description);

		boolean result = claimDAO.addClaim(claim);
		if (result) {
			response.sendRedirect(request.getContextPath() + "/claimSuccess.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/claimError.jsp");
		}
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
		request.setAttribute("registrations", registrations);
		request.getRequestDispatcher("/WEB-INF/views/user/fileClaim.jsp").forward(request, response);
	}
}
