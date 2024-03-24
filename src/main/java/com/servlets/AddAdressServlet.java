package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CustomerBean;
import com.services.implement.CustomerServiceImplement;

/**
 * Servlet implementation class AddAdressServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddAdressServlet" })
public class AddAdressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String totalPrice;
	private String discount;
	private String cartCount;
	private String street;
	private String city;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAdressServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
			System.out.println(customerBean.getCustomerId());
			System.out.println(street);
			System.out.println(city);
			System.out.println(totalPrice);
			System.out.println(discount);
			System.out.println(cartCount);
			CustomerServiceImplement customerServiceImplement = new CustomerServiceImplement();
			customerServiceImplement.addAddress(customerBean.getCustomerId(), street.trim(), city.trim());
//			request.setAttribute("cartCount", cartCount);
//			request.setAttribute("totalPrice", totalPrice);
//			request.setAttribute("discount", discount);
			response.sendRedirect("./OrderUserServlets?totalPrice=" + totalPrice + "&discount=" + discount
					+ "&cartCount=" + cartCount);
		} else {
			// Gérer le cas où la session est nulle
			response.sendRedirect("./ProductsView");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		street = request.getParameter("street");
		city = request.getParameter("city");
		totalPrice = request.getParameter("totalPrice");
		discount = request.getParameter("discount");
		cartCount = request.getParameter("cartCount");
		doGet(request, response);
	}

}
