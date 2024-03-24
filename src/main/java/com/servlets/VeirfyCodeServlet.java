package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.implement.CustomerServiceImplement;

/**
 * Servlet implementation class VeirfyCodeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/VeirfyCodeServlet" })
public class VeirfyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String code1;
	private String code2;
	private String code3;
	private String code4;
	private String email;
	private String forgetPassword;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VeirfyCodeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		CustomerServiceImplement customerServiceImplement = new CustomerServiceImplement();
		String verifyCode = "" + code1 + code2 + code3 + code4 + "";
		if (forgetPassword != null) {
			String status = customerServiceImplement.isApprouvedUser(email, Integer.parseInt(verifyCode), false);
			System.out.println(status);
			if ("Votre compte a été approuvé avec succès! Veuillez réuinillialiser votre mot de passe"
					.equalsIgnoreCase(status)) {
				response.sendRedirect("./resetpassword.jsp?email=" + email);

			} else {
				response.sendRedirect("./verifycode.jsp?email=" + email + "&error="
						+ URLEncoder.encode("Le code de vérification est incorrect ", "UTF-8"));
			}
		} else {
			String status = customerServiceImplement.isApprouvedUser(email, Integer.parseInt(verifyCode), true);
			if ("Votre compte a été approuvé avec succès! Veuillez se connecter pour y acceder"
					.equalsIgnoreCase(status)) {
				response.sendRedirect("./ProductsView?message=" + status);

			} else {
				response.sendRedirect("./verifycode.jsp?email=" + email + "&error="
						+ URLEncoder.encode("Le code de vérification est incorrect ", "UTF-8"));
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("forgetPassword") == null) {
			// Récupérer les données du formulaire
			code1 = request.getParameter("code1");
			code2 = request.getParameter("code2");
			code3 = request.getParameter("code3");
			code4 = request.getParameter("code4");
			email = request.getParameter("email");

			// Vous pouvez ensuite utiliser ces données comme vous le souhaitez, par exemple
			// les enregistrer dans une base de données ou effectuer une autre action basée
			// sur ces données.

			// Exemple : Afficher les codes dans la console
			System.out.println("Code 1: " + code1);
			System.out.println("Code 2: " + code2);
			System.out.println("Code 3: " + code3);
			System.out.println("Code 4: " + code4);
			System.out.println(code1 + code2 + code3 + code4);
			System.out.println(email);
		} else {
			// Récupérer les données du formulaire
			code1 = request.getParameter("code1");
			code2 = request.getParameter("code2");
			code3 = request.getParameter("code3");
			code4 = request.getParameter("code4");
			email = request.getParameter("email");
			forgetPassword = request.getParameter("forgetPassword");

			// Vous pouvez ensuite utiliser ces données comme vous le souhaitez, par exemple
			// les enregistrer dans une base de données ou effectuer une autre action basée
			// sur ces données.

			// Exemple : Afficher les codes dans la console
			System.out.println("Code 1: " + code1);
			System.out.println("Code 2: " + code2);
			System.out.println("Code 3: " + code3);
			System.out.println("Code 4: " + code4);
			System.out.println(code1 + code2 + code3 + code4);
			System.out.println(email);
			System.out.println(forgetPassword);
		}
		doGet(request, response);
	}

}
