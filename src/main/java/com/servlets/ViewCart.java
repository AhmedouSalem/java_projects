package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CouponBean;
import com.beans.CustomerBean;
import com.beans.ShoppingCartBean;
import com.services.implement.CartServiceImplement;

/**
 * Servlet implementation class ViewCart
 */
@WebServlet(asyncSupported = true, description = "Get all product in shopping cart", urlPatterns = { "/ViewCart" })
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartServiceImplement cartServiceImplement = new CartServiceImplement();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewCart() {
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
		HttpSession session = request.getSession(false);
		if (session != null) {
			String message = "Voir votre panier";
			int customerID = (Integer) session.getAttribute("customerID");
			CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
			String couponStatus = (String) request.getAttribute("couponStatus");
			int cartCount = 0;
			double totalPrice = 0.00;
			String status = "Echec...";
			String isDisabled = "";
			String initValue = "";
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			if (request.getParameter("deleteProduct") != null) {
				Boolean flag = cartServiceImplement.removeAProduct(customerID,
						Integer.parseInt(request.getParameter("deleteProduct")));
				status = flag ? "Produit retiré du panier avec succès !"
						: "Échec de la suppression du produit du panier !";
				cartCount = cartServiceImplement.getCartCount(customerID);
				totalPrice = cartServiceImplement.totalPrice(customerID);
				customerBean.setCartCount(cartCount);
//				request.setAttribute("message", status);
//				request.setAttribute("totalPrice", totalPrice);
				response.sendRedirect("./ViewCart?message=" + status);
			} else if (request.getParameter("incrementProduct") != null) {
				status = cartServiceImplement.addProductToCart(customerID,
						Integer.parseInt(request.getParameter("incrementProduct")), 1);
				cartCount = cartServiceImplement.getCartCount(customerID);
				totalPrice = cartServiceImplement.totalPrice(customerID);
				customerBean.setCartCount(cartCount);
//				request.setAttribute("message", status);
//				request.setAttribute("totalPrice", totalPrice);
				response.sendRedirect("./ViewCart?message=" + status);
			} else if (request.getParameter("decrementProduct") != null) {
				status = cartServiceImplement.removeProductFromCart(customerID,
						Integer.parseInt(request.getParameter("decrementProduct")));
				cartCount = cartServiceImplement.getCartCount(customerID);
				totalPrice = cartServiceImplement.totalPrice(customerID);
				customerBean.setCartCount(cartCount);
//				request.setAttribute("message", status);
//				request.setAttribute("totalPrice", totalPrice);
				response.sendRedirect("./ViewCart?message=" + status);
			} else {
				ArrayList<ShoppingCartBean> shoppingArrayList = cartServiceImplement.getAllCartItems(customerID);

				totalPrice = cartServiceImplement.totalPrice(customerID);

				if (request.getParameter("message") != null) {
					message = request.getParameter("message");
				}

				if ("Le code promo a été appliqué".equalsIgnoreCase(couponStatus)) {
					CouponBean couponBean = (CouponBean) request.getAttribute("couponBean");
					totalPrice = totalPrice - (totalPrice * couponBean.getCouponDiscount() / 100);
					message = couponStatus;
					isDisabled = "disabled";
					initValue = couponBean.getCouponName();
					request.setAttribute("discount", couponBean.getCouponDiscount());
				}

				System.out.println(couponStatus);
				System.out.println(totalPrice);

				// Transférer la liste des produits à la page JSP
				request.setAttribute("cartList", shoppingArrayList);
				if (session.getAttribute("userdata") != null) {
					cartCount = customerBean.getCartCount();
				}
				request.setAttribute("message", message);
				request.setAttribute("cartCount", cartCount);
				request.setAttribute("totalPrice", totalPrice);
				request.setAttribute("isDisabled", isDisabled);
				request.setAttribute("initValue", initValue);
				request.setAttribute("cartCount", customerBean.getCartCount());
				request.getRequestDispatcher("/cartview.jsp").forward(request, response);
			}
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
		String promoCode = request.getParameter("coupon_discount");
		String status = "Le code promo a été appliqué";
		CouponBean couponBean = cartServiceImplement.applyDiscount(promoCode);
		if (couponBean == null) {
			status = "Le code promo n'est pas valide";
		}
		request.setAttribute("couponStatus", status);
		request.setAttribute("couponBean", couponBean);
		doGet(request, response);
	}

}
