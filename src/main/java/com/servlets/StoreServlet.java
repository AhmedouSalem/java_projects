package com.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.catalina.connector.Response;

import com.beans.CustomerBean;
import com.beans.StoresBean;
import com.google.gson.Gson;
import com.services.StoreService;
import com.services.implement.StoreServiceImpl;

//@WebServlet(name="stores",urlPatterns = {"*.st"})
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreService storesService ;
	private int cartCount;
       
    
    public StoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	storesService = new StoreServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String path = request.getServletPath(); 
		  HttpSession session = request.getSession();
			if (session.getAttribute("userdata") != null) {
				CustomerBean customerBean = (CustomerBean) session.getAttribute("userdata");
				cartCount = customerBean.getCartCount();
			}
		  	
		  	if(path.equals("/index.st")){
				List<StoresBean> stores = storesService.getAllStore();
				request.setAttribute("stores", stores);
				request.setAttribute("cartCount", cartCount);
				request.getRequestDispatcher("./stores/stores.jsp").forward(request, response);
			
			} 
		  	else if (path.equals("/chercher.st")) { String mc =
				  request.getParameter("motCle"); List<StoresBean> stores =
				  storesService.storeParNameAr("%"+mc+"%"); request.setAttribute("stores",
				  stores); 
				  response.sendRedirect("./stores/stores.jsp?cartCount=" + cartCount);
		  	}				 
			else if((path.equals("/saveStore.st")) && (request.getMethod().equals("POST")) ) {
					 
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
					  
					  StoresBean store = storesService.createStore(new StoresBean(storeNameAr,
					  storeNameFr, phone, email,street, city, storesActive, credit));
					  
					  request.setAttribute("store",store);
						request.setAttribute("cartCount", cartCount);
					  
					  response.sendRedirect("confirmation.jsp");
					  
			  
			  }				
			  else if(path.equals("/supprimer.st")){ 
				  Long id = Long.parseLong(request.getParameter("ID")); 
				  storesService.deleteStore(id);
				  response.sendRedirect("./stores/stores.jsp?cartCount=" + cartCount);
				  
				  } 
			  else if(path.equals("/Edit.st")) {
				  
				  Long id = Long.parseLong(request.getParameter("ID")); 
				  StoresBean store = storesService.getStoreById(id); 
				  Gson gson = new Gson();
				  String jsonData = gson.toJson(store);
				  
				  response.setContentType("application/json");
				  response.setCharacterEncoding("UTF-8");
				  response.getWriter().write(jsonData);
				  
			  }
			  else if(path.equals("/EditStore.st")){ 
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
				  
				  StoresBean store = new StoresBean(storeNameAr, storeNameFr, phone, email,
				  street, city, storesActive, credit); store.setID(id);
				  storesService.editStore(store);
				  
				  response.sendRedirect("confirmation.jsp?cartCount=" + cartCount);
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
	
	
	
	
	
