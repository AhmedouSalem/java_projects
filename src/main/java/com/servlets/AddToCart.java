package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CustomerBean;
import com.services.implement.CartServiceImplement;

/**
 * Servlet implementation class AddToCart
 */

//@WebServlet(asyncSupported = true, description = "Add Product to Cart", urlPatterns = { "/AddToCart" })
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
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
		if (session.getAttribute("customerID") != null) {
			if (request.getParameter("redirect") == null) {
				int customer_ID = (int) session.getAttribute("customerID");
				String email = (String) session.getAttribute("email");
				String password = (String) session.getAttribute("password");
				CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");

				boolean isRegistred = (Boolean) session.getAttribute("isRegistred");
				if (email == null || password == null || isRegistred != true) {
					response.sendRedirect(
							"/ProductsView?message=Session expirée, connectez-vous à nouveau pour continuer !");
					return;
				}

				// login Check Successfull

				int customerID = customer_ID;
				String prodId = request.getParameter("productID");
				int pQty = Integer.parseInt(request.getParameter("pqty")); // 1

				CartServiceImplement cartServiceImplement = new CartServiceImplement();

				String status = cartServiceImplement.addProductToCart(customerID, Integer.parseInt(prodId), pQty);
				int cartCount = cartServiceImplement.getCartCount(customerID);
				customerBean.setCartCount(cartCount);
				response.sendRedirect(
						request.getContextPath() + "/ProductsView?message=" + URLEncoder.encode(status, "UTF-8"));
			} else {
				request.getRequestDispatcher("/ViewCart").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/ProductsView?message=Vous devriez d'abord se connecter").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
