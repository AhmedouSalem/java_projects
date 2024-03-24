package com.services.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;

import com.beans.OrderBean;
import com.beans.ShoppingCartBean;
import com.services.OrderService;
import com.utility.DBUtil;

public class OrderServiceImplements implements OrderService {

	@Override
	public ArrayList<String> sendOrder(int customerID, int discount, double totalPrice, int orderAddress,
			String receiver, double orderPriceDelivery, String requiredDate,
			ArrayList<ShoppingCartBean> arrayListShoppingCart) {
		String status = "Votre commande a été passée avec succès!"; // Initialisez la variable de statut avec succès
		ArrayList<String> returnResults = new ArrayList<String>();

		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		int orderId = 0;

		try {
			// Créer la déclaration SQL avec l'option RETURN_GENERATED_KEYS
			ps = con.prepareStatement(
					"INSERT INTO orders (customer_id, discount, total_price, receiver, order_address, orders_price_delevery, required_date) VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, customerID);
			ps.setInt(2, discount);
			ps.setDouble(3, totalPrice);
			ps.setString(4, receiver);
			ps.setInt(5, orderAddress);
			ps.setDouble(6, orderPriceDelivery);
			ps.setString(7, requiredDate);

			int rowsAffected = ps.executeUpdate(); // Exécuter la première requête

			if (rowsAffected > 0) { // Vérifiez si l'insertion dans la table orders a réussi
				// Récupérer l'ID de la dernière ligne insérée
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						orderId = generatedKeys.getInt(1);
						for (ShoppingCartBean shoppingCartBean : arrayListShoppingCart) {
							ps2 = con.prepareStatement(
									"INSERT INTO order_items(order_id, product_id, quantity, list_price) VALUES(?, ?, ?, ?);");
							ps2.setInt(1, orderId);
							ps2.setInt(2, shoppingCartBean.getProductId());
							ps2.setInt(3, shoppingCartBean.getQuantity());
							ps2.setDouble(4, shoppingCartBean.getListPrice());

							int rowsInserted = ps2.executeUpdate(); // Exécuter l'insertion dans la table order_items

							if (rowsInserted <= 0) { // Vérifiez si l'insertion dans la table order_items a échoué
								status = "Erreur lors de l'insertion dans la table order_items";
								break; // Sortir de la boucle si une insertion a échoué
							}
						}
						if ("Votre commande a été passée avec succès!".equalsIgnoreCase(status)) {
							ps3 = con.prepareStatement("DELETE FROM shopping_cart where `customer_id` = ?");
							ps3.setInt(1, customerID);
							ps3.executeUpdate();
						}
					}
				}
			} else {
				status = "Erreur lors de l'insertion dans la table orders";
			}
		} catch (SQLException e) {
			status = "Erreur: " + e.getMessage();
			e.printStackTrace();
		} finally {
			// Fermer les ressources de base de données
			DBUtil.closeConnection(con);
			DBUtil.closeConnection(ps);
			DBUtil.closeConnection(rs);
			DBUtil.closeConnection(ps2);
		}

		returnResults.add(status);
		returnResults.add(String.valueOf(orderId));
		return returnResults; // Retourner le statut final
	}

	@Override
	public ArrayList<OrderBean> getAllOrders(int customerID) {
		ArrayList<OrderBean> orders = new ArrayList<OrderBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from orders where customer_id = ?");
			ps.setInt(1, customerID);
			rs = ps.executeQuery();

			while (rs.next()) {

				OrderBean orderBean = new OrderBean();

				orderBean.setOrderId(rs.getInt("order_id"));
				orderBean.setCustomerId(rs.getInt("customer_id"));
				orderBean.setOrderStatus(rs.getInt("order_status"));
				orderBean.setRequiredDate(rs.getDate("required_date"));
				orderBean.setShippedDate(rs.getDate("shipped_date"));
				orderBean.setDiscount(rs.getInt("discount"));
				orderBean.setTotalPrice(rs.getBigDecimal("total_price"));
				orderBean.setReceiver(rs.getString("receiver"));
				orderBean.setOrderAddress(rs.getInt("order_address"));
				orderBean.setOrderDate(rs.getDate("order_date"));
				orderBean.setOrderPriceDelivery(rs.getBigDecimal("orders_price_delevery"));

				orders.add(orderBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return orders;
	}

	public OrderBean getOrderDetails(int orderId) {
		return null;
	}
}
