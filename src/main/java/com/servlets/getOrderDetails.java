package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.OrderBean;
import com.google.gson.Gson;
import com.services.implement.OrderServiceImplements;

/**
 * Servlet implementation class getOrderDetails
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/getOrderDetails" })
public class getOrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getOrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupérer l'identifiant de la commande à partir des paramètres de requête
		int orderId = Integer.parseInt(request.getParameter("orderId"));

		// Obtenir les détails de la commande à partir de la base de données
		OrderServiceImplements orderService = new OrderServiceImplements();
		OrderBean order = orderService.getOrderDetails(orderId);

		// Convertir l'objet OrderBean en format JSON
		Gson gson = new Gson();
		String json = gson.toJson(order);

		// Écrire la réponse JSON
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
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
