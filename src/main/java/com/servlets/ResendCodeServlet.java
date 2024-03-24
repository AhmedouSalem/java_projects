package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.implement.CustomerServiceImplement;

/**
 * Servlet implementation class ResendCodeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ResendCodeServlet" })
public class ResendCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String email ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResendCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		email = request.getParameter("email");
		CustomerServiceImplement customerServiceImplement = new CustomerServiceImplement();
		customerServiceImplement.resendCodeVerification(email);
		response.sendRedirect("./verifycode.jsp?email=" + email);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
