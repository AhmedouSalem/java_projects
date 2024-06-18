package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.logging.Logger;

import com.beans.UsersBean;
import com.services.UsersServices;
import com.services.implement.UserSerImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(asyncSupported = true, name = "users", urlPatterns = { "/users" })
public class UserServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(UserServlet.class.getName());
	private static final long serialVersionUID = 1L;
	private UsersServices userService;

	public void init() throws ServletException {
		userService = new UserSerImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userName") == null) {
			response.sendRedirect("./login_admin.jsp");
			return;
		}

		List<UsersBean> userList = userService.getAllUsers();
		if (userList != null) {
			logger.info("User list size: " + userList.size());
		} else {
			logger.warning("User list is null");
		}
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/users.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("delete".equals(action)) {
			Long userId = Long.parseLong(request.getParameter("userId"));
			userService.deleteUser(userId);
		} else if ("activate".equals(action)) {
			Long userId = Long.parseLong(request.getParameter("userId"));
			UsersBean user = userService.getUserById(userId);
			if (user != null) {
				user.setActive(!user.isActive());
				userService.activeCompteUser(user);
			}
		}

		response.sendRedirect("/users");
	}

}
