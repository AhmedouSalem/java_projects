package com.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.CustomerBean;
import com.services.implement.CustomerServiceImplement;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(asyncSupported = true, description = "Le servlet pour la création d'un compte", urlPatterns = {
		"/SignUpServlet" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String phoneNumber;
	private String password;
	private String confirmPassword;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
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
		String status = "";
		if (password != null && password.equals(confirmPassword)) {
			CustomerBean user = new CustomerBean(username, phoneNumber, email, password, "", "", 0, 0);

			CustomerServiceImplement customerServiceImplement = new CustomerServiceImplement();

			status = customerServiceImplement.registerUser(user);
			if ("Régistration effectuée avec succès!".equalsIgnoreCase(status)) {

				response.sendRedirect("./verifycode.jsp?message=" + status + "&email=" + email);
			} else {

				response.sendRedirect("./ProductsView?message=" + status);
			}
		} else {
			status = "Le mot de passe ne correspond pas!";

			response.sendRedirect("./ProductsView?message=" + status);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		username = request.getParameter("username");
		email = request.getParameter("email");
		phoneNumber = request.getParameter("phoneNumber");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		doGet(request, response);
	}

}
