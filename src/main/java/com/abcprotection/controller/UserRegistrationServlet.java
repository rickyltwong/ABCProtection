package com.abcprotection.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abcprotection.dao.UserDAO;
import com.abcprotection.model.User;

@WebServlet("/signup")
public class UserRegistrationServlet extends HttpServlet {

	private UserDAO userDAO;

	@Override
	public void init() {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/user/registrationForm.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String cellphone = request.getParameter("cellphone_no");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String address = request.getParameter("address");

		if (!password.equals(confirmPassword)) {
			request.setAttribute("NOTIFICATION", "Passwords do not match!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
			return;
		}

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setCellphoneNo(cellphone);
		newUser.setEmail(email);
		newUser.setName(name);
		newUser.setAddress(address);

		boolean result = userDAO.addUser(newUser);
		if (result) {
			request.setAttribute("NOTIFICATION", "User registered successfully! Please login.");
			response.sendRedirect("login.jsp");
		} else {
			request.setAttribute("NOTIFICATION", "User registration failed!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		}
	}

}
