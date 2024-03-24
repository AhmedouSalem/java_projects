package com.services;

import java.util.ArrayList;

import com.beans.OrderBean;
import com.beans.ShoppingCartBean;

public interface OrderService {
	public ArrayList<String> sendOrder(int customerID, int discount, double totalPrice, int orderAddress, String receiver,
			double orderPriceDelivery, String requiredDate, ArrayList<ShoppingCartBean> arrayListShoppingCart);
	public ArrayList<OrderBean> getAllOrders(int customerID);
	public OrderBean getOrderDetails(int orderId);
}
