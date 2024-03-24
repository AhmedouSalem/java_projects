package com.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.catalina.connector.Response;

import com.beans.CustomerBean;
import com.beans.ProductBean;
import com.beans.StoresBean;
import com.google.gson.Gson;
//import com.google.gson.Gson;
import com.services.ProductsStroesSevices;
import com.services.StoreService;
import com.services.implement.ProductsStroesImplement;
//import com.services.StoreService;
import com.services.implement.StoreServiceImpl;

@WebServlet(name = "stores", urlPatterns = { "*.st" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class StoreProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreService storesService;
	private ProductsStroesSevices productSer;
	private int cartCount;

	public StoreProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		storesService = new StoreServiceImpl();
		productSer = new ProductsStroesImplement();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userdata") != null) {
			CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
			cartCount = customerBean.getCartCount();
		}

		String path = request.getServletPath();

		if (path.equals("/index.st")) {
			List<StoresBean> stores = storesService.getAllStore();
			request.setAttribute("stores", stores);
			request.setAttribute("cartCount", cartCount);
			request.getRequestDispatcher("./stores/stores.jsp").forward(request, response);

		} else if (path.equals("/chercher.st")) {
			String mc = request.getParameter("motCle");
			List<StoresBean> stores = storesService.storeParNameAr("%" + mc + "%");
			request.setAttribute("stores", stores);
			response.sendRedirect("index.st");
		} else if ((path.equals("/saveStore.st")) && (request.getMethod().equals("POST"))) {

			String storeNameFr = request.getParameter("storeNameFr");
			String storeNameAr = request.getParameter("storeNameAr");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String storesActiveParam = request.getParameter("storesActive");
			byte storesActive = 0;
			if (storesActiveParam != null) {
				storesActive = Byte.parseByte(storesActiveParam);
			}
			BigDecimal credit = new BigDecimal(request.getParameter("credit"));

			StoresBean store = storesService.createStore(
					new StoresBean(storeNameAr, storeNameFr, phone, email, street, city, storesActive, credit));

			request.setAttribute("store", store);
			request.setAttribute("cartCount", cartCount);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);

		} else if (path.equals("/supprimer.st")) {
			Long id = Long.parseLong(request.getParameter("ID"));
			storesService.deleteStore(id);
			response.sendRedirect("index.st");

		} else if (path.equals("/Edit.st")) {

			Long id = Long.parseLong(request.getParameter("ID"));
			StoresBean store = storesService.getStoreById(id);
			Gson gson = new Gson();
			String jsonData = gson.toJson(store);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonData);

		} else if (path.equals("/EditStore.st")) {
			Long id = Long.parseLong(request.getParameter("ID"));
			String storeNameFr = request.getParameter("storeNameFr");
			String storeNameAr = request.getParameter("storeNameAr");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String storesActiveParam = request.getParameter("storesActive");
			byte storesActive = 0;
			if (storesActiveParam != null) {
				storesActive = Byte.parseByte(storesActiveParam);
			}
			BigDecimal credit = new BigDecimal(request.getParameter("credit"));

			StoresBean store = new StoresBean(storeNameAr, storeNameFr, phone, email, street, city, storesActive,
					credit);
			store.setID(id);
			storesService.editStore(store);

			response.sendRedirect("index.st");
		}

		else if (path.equals("/produits.st")) {
			List<ProductBean> products = productSer.getAllProd();
			request.setAttribute("products", products);
			request.setAttribute("cartCount", cartCount);
			request.getRequestDispatcher("stores/produit.jsp").forward(request, response);
		} else if (path.equals("/chercherProd.st")) {
			String mc = request.getParameter("motCle");
			List<ProductBean> products = productSer.getAllProductsByNameFr("%" + mc + "%");
			request.setAttribute("products", products);
			request.setAttribute("cartCount", cartCount);
			response.sendRedirect("stores/produit.jsp");
		} else if ((path.equals("/saveProduit.st")) && (request.getMethod().equals("POST"))) {
			// Récupérer les données du formulaire
			String productNameFr = request.getParameter("productNameFr");
			short modelYear = Short.parseShort(request.getParameter("modelYear"));
			double listPrice = Double.parseDouble(request.getParameter("listPrice"));
			int brandId = Integer.parseInt(request.getParameter("brandId"));
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Part prodImage = request.getPart("prodImage");
			if (prodImage != null && prodImage.getSize() > 0) {
				// Créer un objet ProductBean
				ProductBean product = new ProductBean();
				product.setProductNameFr(productNameFr);
				product.setModelYear(modelYear);
				product.setListPrice(listPrice);
				product.setBrandId(brandId);
				product.setCategoryId(categoryId);

				try (InputStream inputStream = prodImage.getInputStream()) {

					// Lire les données de l'image en tant que tableau de bytes
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					byte[] data = new byte[1024];
					int nRead;
					while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();
					byte[] imageBytes = buffer.toByteArray();

					// Définir les données de l'image dans le produit
					product.setProdImage(new ByteArrayInputStream(imageBytes));

					// Appeler la méthode createProd pour insérer le produit dans la base de données
					ProductBean createdProduct = productSer.createProd(product);

					// Rediriger vers une page de confirmation
					request.setAttribute("product", createdProduct);
					request.setAttribute("cartCount", cartCount);
					request.getRequestDispatcher("confirmationProd.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("masla7 chi");
			}

		} else if (path.equals("/supprimerProd.st")) {
			Long id = Long.parseLong(request.getParameter("ID"));
			productSer.removeProduct(id);

			response.sendRedirect("produits.st");
		}

		else if (path.equals("/EditProd.st")) {
			Long id = Long.parseLong(request.getParameter("ID"));
			ProductBean prod = productSer.getProductById(id);
			Gson gson = new Gson();
			String jsonData = gson.toJson(prod);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonData);

		} else if (path.equals("/modifierProduit.st")) {
			// Récupérer les données du formulaire
			int id = Integer.parseInt(request.getParameter("productId"));
			String productNameFr = request.getParameter("productNameFr");
			short modelYear = Short.parseShort(request.getParameter("modelYear"));
			double listPrice = Double.parseDouble(request.getParameter("listPrice"));
			int brandId = Integer.parseInt(request.getParameter("brandId"));
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Part prodImage = request.getPart("prodImage");

			// Créer un objet ProductBean
			ProductBean product = new ProductBean();
			product.setProductNameFr(productNameFr);
			product.setModelYear(modelYear);
			product.setListPrice(listPrice);
			product.setBrandId(brandId);
			product.setCategoryId(categoryId);
			product.setProdImage(prodImage.getInputStream());
			product.setProductId(id);

			// Appeler la méthode createProd pour insérer le produit dans la base de données
			productSer.updateProduct(product);
 
			response.sendRedirect("produits.st");
		}

		else {
			response.sendError(Response.SC_NOT_FOUND);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
