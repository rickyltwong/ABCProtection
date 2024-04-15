package com.abcprotection.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abcprotection.dao.ProductDAO;
import com.abcprotection.dao.RegistrationDAO;
import com.abcprotection.model.Registration;
import com.abcprotection.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistrationDAO registrationDAO;

	public RegistrationServlet() {
		super();
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

		String username = ((User) session.getAttribute("user")).getUsername();
		String productName = request.getParameter("productName");
		String serialNo = request.getParameter("serialNo");
		String purchaseDateStr = request.getParameter("purchaseDate");

		Date purchaseDate = null;
		try {
			purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format.");
			return;
		}

		Registration registration = new Registration();
		registration.setUsername(username);
		registration.setProductName(productName);
		registration.setSerialNo(serialNo);
		registration.setPurchaseDate(purchaseDate);

		boolean result = registrationDAO.addRegistration(registration);
		if (result) {
			response.sendRedirect(request.getContextPath() + "/registrationSuccess.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/registrationError.jsp");
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

		ProductDAO productDAO = new ProductDAO();
		List<String> productNames = productDAO.getAllProductNames();
		request.setAttribute("productNames", productNames);

		request.getRequestDispatcher("/WEB-INF/views/user/registerProduct.jsp").forward(request, response);
	}

}
