package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CustomerBean;
import com.services.implement.CustomerServiceImplement;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean rememberMe = request.getParameter("remember_me") != null;

		String status = "L'email ou le mot de passe est incorrect";
		CustomerServiceImplement csi = new CustomerServiceImplement();

		status = csi.isValidCredential(email, password);
		response.setContentType("text/html");

		if (status.equalsIgnoreCase("Vous avez été connecté avec succès")) {
			// valid user
			CustomerBean customer = csi.getCustomerDetails(email, password);
			
			System.out.println("customer active : " + customer.getIsActive());
			if (customer.getIsActive() == 1) {
				HttpSession session = request.getSession();

				session.setAttribute("userdata", customer);

				session.setAttribute("customerID", customer.getCustomerId());
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				session.setAttribute("isRegistred", true);
				// Si l'utilisateur a choisi "Se souvenir de moi", enregistrer les informations
				// dans un cookie
				if (rememberMe) {
					Cookie emailCookie = new Cookie("email", email);
					Cookie passwordCookie = new Cookie("password", password);
					Cookie rememberMeCookie = new Cookie("remember_me", "true");
					emailCookie.setMaxAge(30 * 24 * 60 * 60); // 30 jours
					passwordCookie.setMaxAge(30 * 24 * 60 * 60);// 30 jours
					rememberMeCookie.setMaxAge(30 * 24 * 60 * 60); // 30 jours
					response.addCookie(emailCookie);
					response.addCookie(passwordCookie);
					response.addCookie(rememberMeCookie);
				}
				request.setAttribute("message", status);

				response.sendRedirect(
						request.getContextPath() + "/ProductsView?message=" + URLEncoder.encode(status, "UTF-8"));
			} else {
				response.sendRedirect(request.getContextPath() + "/verifycode.jsp?email=" + customer.getCustomerEmail());
			}

		} else {
			// invalid user;
			response.sendRedirect(
					request.getContextPath() + "/ProductsView?message=" + URLEncoder.encode(status, "UTF-8"));
		}
	}

}
