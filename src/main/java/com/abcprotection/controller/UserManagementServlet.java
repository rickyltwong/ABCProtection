package com.abcprotection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abcprotection.dao.UserDAO;
import com.abcprotection.model.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/admin/users")
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	// Initialization of DAO
    public void init() {
        userDAO = new UserDAO(); 
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
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
		String method = request.getParameter("_method");

	    if ("PUT".equals(method)) {
	        doPut(request, response);
	    } else if ("DELETE".equals(method)) {
	        doDelete(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String cellphoneNo = request.getParameter("cellphoneNo");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        
        User user = new User(userId, username, cellphoneNo, email, name, address);

        boolean success = userDAO.updateUser(user);
        if (!success) {
        	request.setAttribute("errorMessage", "Failed to update.");
        } 
        response.sendRedirect(request.getContextPath() + "/admin/dashboard?section=users");
    
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
		boolean success = userDAO.deleteUser(userId);
		if (!success) {
			request.setAttribute("errorMessage", "Failed to update.");
        } 
		response.sendRedirect(request.getContextPath() + "/admin/dashboard?section=users");
	}

}
