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

import com.beans.CategoriesBean;
import com.services.CategorieServices;
import com.services.implement.CatagorieSerImpl;

/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet(asyncSupported = true, name = "categories", urlPatterns = { "/categories" })
public class CategoriesServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(CategoriesServlet.class.getName());
	private static final long serialVersionUID = 1L;
	private CategorieServices categorieService;

	public CategoriesServlet() {
		super();
	}

	public void init() throws ServletException {
		categorieService = new CatagorieSerImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userName") == null) {
			response.sendRedirect("./login_admin.jsp");
			return;
		}

		List<CategoriesBean> categorieList = categorieService.getAllCategorie();
		if (categorieList != null) {
			logger.info("Categorie list size: " + categorieList.size());
		} else {
			logger.warning("Categorie list is null");
		}
		request.setAttribute("categorieList", categorieList);
		request.getRequestDispatcher("./categories.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userName") == null) {
			response.sendRedirect("./login_admin.jsp");
			return;
		}

		String action = request.getParameter("action");

		if ("create".equals(action)) {
			String categorieName = request.getParameter("categorieName");
			CategoriesBean newCategorie = new CategoriesBean();
			newCategorie.setCategorieName(categorieName);
			categorieService.createCat(newCategorie);
		} else if ("delete".equals(action)) {
			Long categorieId = Long.parseLong(request.getParameter("categorieId"));
			categorieService.deleteCategorie(categorieId);
		} else if ("edit".equals(action)) {
			Long categorieId = Long.parseLong(request.getParameter("categorieId"));
			String categorieName = request.getParameter("categorieName");
			CategoriesBean categorie = categorieService.getCatById(categorieId);
			if (categorie != null) {
				categorie.setCategorieName(categorieName);
				categorieService.editCatgorie(categorie);
			}
		}

		response.sendRedirect("./categories");
	}

}
