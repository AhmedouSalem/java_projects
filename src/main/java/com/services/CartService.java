package com.services;

import java.util.ArrayList;

import com.beans.CouponBean;
import com.beans.ShoppingCartBean;

public interface CartService {

	public String addProductToCart(int customerID, int prodId,int prodQty);

	public String updateProductToCart(int customerID, int productId, int prodQty);

	public ArrayList<ShoppingCartBean> getAllCartItems(int customerID);

	public int getCartCount(int customerID);

	public int getCartItemCount(String customerID, String productId);

	public String removeProductFromCart(int customerID, int productId);

	public boolean removeAProduct(int customerID, int productId);
	
	public CouponBean applyDiscount(String promoCode) ; 
	public double totalPrice(int customerID);

}