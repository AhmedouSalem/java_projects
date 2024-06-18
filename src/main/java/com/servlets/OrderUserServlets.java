package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.AddressBean;
import com.beans.CustomerBean;
import com.beans.ShoppingCartBean;
import com.services.implement.AddressServiceImplements;
import com.services.implement.CartServiceImplement;
import com.services.implement.OrderServiceImplements;
import com.utility.MailMessage;



/**
 * Servlet implementation class OrderUserServlets
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/OrderUserServlets" })
public class OrderUserServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cartCount;
	private String discount;
	private String totalPrice;
	private String selectedAddressId;
	private String recipientPhone;
	private String requiredDate;
	private int customerID;
	private ArrayList<String> status;
	private String sendOrder;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderUserServlets() {
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
			customerID = (int) session.getAttribute("customerID");
			if (sendOrder == null) {
				AddressServiceImplements addressServiceImplements = new AddressServiceImplements();
				ArrayList<AddressBean> addressList = addressServiceImplements
						.getCustomerAddress((Integer) session.getAttribute("customerID"));
				// Récupérer les paramètres envoyés par le formulaire
				if (cartCount == null) {
					discount = request.getParameter("discount");
					totalPrice = request.getParameter("totalPrice");
					cartCount = (String) request.getParameter("cartCount");
				}
				request.setAttribute("cartCount", Integer.parseInt(cartCount));
				request.setAttribute("addressList", addressList);
				request.getRequestDispatcher("./checkout.jsp").forward(request, response);
			} else {
				CartServiceImplement cartServiceImplement = new CartServiceImplement();
				ArrayList<ShoppingCartBean> shoppingArrayList = cartServiceImplement.getAllCartItems(customerID);
				if (shoppingArrayList != null) {
					OrderServiceImplements orderServiceImplements = new OrderServiceImplements();
					status = orderServiceImplements.sendOrder(customerID, Integer.parseInt(discount),
							Double.parseDouble(totalPrice), Integer.parseInt(selectedAddressId), recipientPhone,
							20.00, requiredDate, shoppingArrayList);
					if ("Votre commande a été passée avec succès!".equalsIgnoreCase(status.get(0))) {
						CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
						customerBean.setCartCount(0);
						MailMessage.transactionSuccess(customerBean.getCustomerEmail(), customerBean.getCustomerName(), status.get(1), Double.parseDouble(totalPrice) + 2000.00);
					}
					response.sendRedirect("./ProductsView?message=" + URLEncoder.encode(status.get(0), "UTF-8"));
				}
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
		// Récupérer les paramètres envoyés par le formulaire
		if (request.getParameter("sendOrder") == null) {
			discount = request.getParameter("discount");
			totalPrice = request.getParameter("totalPrice");
			cartCount = (String) request.getParameter("cartCount");
		}
		selectedAddressId = request.getParameter("radio"); // Pour récupérer l'ID de l'adresse sélectionnée
		recipientPhone = request.getParameter("bankily");
		requiredDate = request.getParameter("requiredDate");
		sendOrder = request.getParameter("sendOrder");
		doGet(request, response);
	}

}
