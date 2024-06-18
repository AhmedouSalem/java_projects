package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.AdminServices;
import com.services.implement.AdminSerImp;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet(asyncSupported = true, name = "loginAdmin", urlPatterns = { "/loginAdmin" })
public class AdminLogin extends HttpServlet {
//  private static final Logger logger = Logger.getLogger(AdminLogin.class.getName());
	private AdminServices adminSer;
	private static final long serialVersionUID = 1L;

	public AdminLogin() {
		super();
	}

	public void init() throws ServletException {
		adminSer = new AdminSerImp();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		boolean rememberMe = request.getParameter("remember_me") != null;

//      logger.info("Received login request for username: " + userName + "password : " + password);

		String status = adminSer.login(userName, password);
		request.setAttribute("status", status.equals("Vous avez été connecté avec succès") ? "success" : "failed");

		if (status.equals("Vous avez été connecté avec succès")) {
//      	logger.info("Login successful for username: " + userName + "password : " + password);
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			if (rememberMe) {
				Cookie emailCookie = new Cookie("username", userName);
				Cookie passwordCookie = new Cookie("password", password);
				Cookie rememberMeCookie = new Cookie("remember_me", "true");
				emailCookie.setMaxAge(30 * 24 * 60 * 60); // 30 jours
				passwordCookie.setMaxAge(30 * 24 * 60 * 60);// 30 jours
				rememberMeCookie.setMaxAge(30 * 24 * 60 * 60); // 30 jours
				response.addCookie(emailCookie);
				response.addCookie(passwordCookie);
				response.addCookie(rememberMeCookie);
			}
			response.sendRedirect("./admin_index.jsp");

//          RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//          dispatcher.forward(request, response);
		} else {
//          logger.info("Login failed for username: " + userName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./login_admin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
