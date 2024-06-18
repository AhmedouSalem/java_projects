package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutAdmin
 */
@WebServlet(asyncSupported = true, name = "logout", urlPatterns = { "/logout" })
public class LogoutAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		sess.invalidate();
		response.sendRedirect("./login_admin.jsp");
	}

}
