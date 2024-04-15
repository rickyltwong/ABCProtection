package com.abcprotection.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abcprotection.dao.ClaimDAO;
import com.abcprotection.dao.ProductDAO;

/**
 * Servlet implementation class ClaimManagementServlet
 */
@WebServlet("/admin/claims")
public class ClaimManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClaimDAO claimDAO;
	// Initialization of DAO
    public void init() {
    	claimDAO = new ClaimDAO(); 
    }
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaimManagementServlet() {
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
		String status = request.getParameter("status");
		int claimId = Integer.parseInt(request.getParameter("claimId"));
		boolean success = claimDAO.updateClaimStatus(claimId, status);
		if (!success) {
			request.setAttribute("errorMessage", "Failed to update");
        } 
		response.sendRedirect(request.getContextPath() + "/admin/dashboard?section=claims");
		
	}

}
