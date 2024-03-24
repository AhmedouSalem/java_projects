package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CustomerBean;
import com.beans.ProductBean;
import com.services.implement.ProductsViewImplement;

/**
 * Servlet implementation class ProductsView
 */
//@WebServlet(asyncSupported = true, description = "Controller de la vue des produits", urlPatterns = { "/ProductsView" })
public class ProductsView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductsView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupérer la liste des produits
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		ProductsViewImplement productService = new ProductsViewImplement();
		ArrayList<ProductBean> productList = productService.getAllProducts();

		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("isRegistred"));
		String message = "Bienvenue sur notre l'espace de nos magasins";
		if (request.getParameter("message") != null) {
			message = request.getParameter("message");
		}

		// Transférer la liste des produits à la page JSP
		request.setAttribute("productList", productList);
		int cartCount = 0;
		if (session.getAttribute("userdata") != null) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
			cartCount = customerBean.getCartCount();
		}

		request.setAttribute("message", message);
		request.setAttribute("cartCount", cartCount);

		// Dispatch vers la page JSP
		request.getRequestDispatcher("/index.jsp").forward(request, response);
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
