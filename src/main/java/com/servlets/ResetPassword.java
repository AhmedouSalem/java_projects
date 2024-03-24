package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.implement.CustomerServiceImplement;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ResetPassword" })
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String confirmPassword;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		if (password != null && password.equals(confirmPassword)) {
			CustomerServiceImplement customerServiceImplement = new CustomerServiceImplement();
			String status = customerServiceImplement.resetPassword(email, password);
			if ("Le mot de passe a été réuinillialisé avec succès, veuillez se connecter pour acceder à votre compte".equalsIgnoreCase(status)) {
				response.sendRedirect("./ProductsView?message=" + status) ;
			} else {
				response.sendRedirect("./resetpassword.jsp?email="+email + "&error="+status);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		email = request.getParameter("email");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		System.out.println(email);
		System.out.println(password);
		System.out.println(confirmPassword);
		doGet(request, response);
	}

}
