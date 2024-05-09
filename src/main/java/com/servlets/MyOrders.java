package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CustomerBean;
import com.beans.OrderBean;
import com.services.implement.OrderServiceImplements;

/**
 * Servlet implementation class MyOrders
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/MyOrders" })
public class MyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
			System.out.println(customerBean.getCustomerId());
			OrderServiceImplements orderServiceImplements = new OrderServiceImplements();
			ArrayList<OrderBean> ordersList = orderServiceImplements.getAllOrders(customerBean.getCustomerId());
			request.setAttribute("ordersList", ordersList);
			request.setAttribute("cartCount", customerBean.getCartCount());
			// TODO Auto-generated method stub
			request.getRequestDispatcher("./myorders.jsp").forward(request, response);;
		} else {
			response.sendRedirect("./ProductsView");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
