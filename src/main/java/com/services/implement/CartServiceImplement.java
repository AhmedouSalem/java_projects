package com.services.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.beans.CouponBean;
import com.beans.ShoppingCartBean;
import com.services.CartService;
import com.utility.DBUtil;

public class CartServiceImplement implements CartService {

	@Override
	public String addProductToCart(int customerID, int prodId, int prodQty) {
		String status = "Échec de l'ajout au panier";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("select * from shopping_cart where customer_id=? and product_id=?");

			ps.setInt(1, customerID);
			ps.setInt(2, prodId);

			rs = ps.executeQuery();

			if (rs.next()) {

				int cartQuantity = rs.getInt("quantity");

				prodQty += cartQuantity;

				status = updateProductToCart(customerID, prodId, prodQty);
			} else {
				status = updateProductToCart(customerID, prodId, prodQty);
			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		DBUtil.closeConnection(ps2);

		return status;
	}

	@Override
	public String updateProductToCart(int customerID, int productId, int prodQty) {
		String status = "Échec de l'ajout au panier";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("select * from shopping_cart where customer_id=? and product_id=?");

			ps.setInt(1, customerID);
			ps.setInt(2, productId);

			rs = ps.executeQuery();

			if (rs.next()) {

				if (prodQty > 0) {
					ps2 = con.prepareStatement(
							"update shopping_cart set quantity=? where customer_id=? and product_id=?");

					ps2.setInt(1, prodQty);

					ps2.setInt(2, customerID);

					ps2.setInt(3, productId);

					int k = ps2.executeUpdate();

					if (k > 0)
						status = "Produit a été mis à jour avec succès dans le panier !";
				} else if (prodQty == 0) {
					ps2 = con.prepareStatement("delete from shopping_cart where customer_id=? and product_id=?");

					ps2.setInt(1, customerID);

					ps2.setInt(2, productId);

					int k = ps2.executeUpdate();

					if (k > 0)
						status = "Produit a été  mis à jour avec succès dans le panier !";
				}
			} else {

				ps2 = con
						.prepareStatement("insert into shopping_cart(product_id, customer_id, quantity) values(?,?,?)");

				ps2.setInt(1, productId);

				ps2.setInt(2, customerID);

				ps2.setInt(3, prodQty);

				int k = ps2.executeUpdate();

				if (k > 0) {
					status = "Produit a été  mis à jour avec succès dans le panier !";
				}

			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		DBUtil.closeConnection(ps2);

		return status;
	}

	public int getProductCount(int customerID, int prodId) {
		int count = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select sum(quantity) from shoppingcart where customer_id=? and product_id=?");
			ps.setInt(1, customerID);
			ps.setInt(2, prodId);
			rs = ps.executeQuery();

			if (rs.next() && !rs.wasNull())
				count = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public ArrayList<ShoppingCartBean> getAllCartItems(int customerID) {
		ArrayList<ShoppingCartBean> items = new ArrayList<ShoppingCartBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("select * from shoppingcartview where customer_id=?");

			ps.setInt(1, customerID);

			rs = ps.executeQuery();

			while (rs.next()) {
				ShoppingCartBean shoppingCartBean = new ShoppingCartBean();

				shoppingCartBean.setProductId(rs.getInt("product_id"));
				shoppingCartBean.setCustomerId(rs.getInt("customer_id"));
				shoppingCartBean.setQuantity(rs.getInt("quantity"));
				shoppingCartBean.setProductNameFr(rs.getString("product_name_fr"));
				shoppingCartBean.setBrandNameFr(rs.getString("brand_name_fr"));
				shoppingCartBean.setCategoryNameFr(rs.getString("category_name_fr"));
				shoppingCartBean.setModelYear(rs.getInt("model_year"));
				shoppingCartBean.setListPrice(rs.getDouble("list_price"));
				shoppingCartBean.setProductImage(rs.getAsciiStream("product_image"));
				shoppingCartBean.setCustomerName(rs.getString("customer_name"));
				shoppingCartBean.setCustomerPhone(rs.getString("customer_phone"));
				shoppingCartBean.setCustomerEmail(rs.getString("customer_email"));
				items.add(shoppingCartBean);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return items;
	}

	@Override
	public int getCartCount(int customerID) {
		int count = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select sum(quantity) from shopping_cart where customer_id=?");

			ps.setInt(1, customerID);

			rs = ps.executeQuery();

			if (rs.next() && !rs.wasNull())
				count = rs.getInt(1);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return count;
	}

	@Override
	public int getCartItemCount(String customerID, String productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String removeProductFromCart(int customerID, int productId) {
		String status = "Échec de la suppression du produit";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("select * from shopping_cart where customer_id=? and product_id=?");

			ps.setInt(1, customerID);
			ps.setInt(2, productId);

			rs = ps.executeQuery();

			if (rs.next()) {

				int prodQuantity = rs.getInt("quantity");

				prodQuantity -= 1;

				if (prodQuantity > 0) {
					ps2 = con.prepareStatement(
							"update shopping_cart set quantity=? where customer_id=? and product_id=?");

					ps2.setInt(1, prodQuantity);

					ps2.setInt(2, customerID);

					ps2.setInt(3, productId);

					int k = ps2.executeUpdate();

					if (k > 0)
						status = "Produit retiré du panier avec succès !";
				} else if (prodQuantity <= 0) {

					ps2 = con.prepareStatement("delete from shopping_cart where customer_id=? and product_id=?");

					ps2.setInt(1, customerID);

					ps2.setInt(2, productId);

					int k = ps2.executeUpdate();

					if (k > 0)
						status = "Produit retiré du panier avec succès !";
				}

			} else {

				status = "Produit non disponible dans le panier !";

			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		DBUtil.closeConnection(ps2);

		return status;
	}

	@Override
	public boolean removeAProduct(int customerID, int productId) {
		boolean flag = false;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("delete from shopping_cart where customer_id=? and product_id=?");
			ps.setInt(1, customerID);
			ps.setInt(2, productId);

			int k = ps.executeUpdate();

			if (k > 0)
				flag = true;

		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return flag;
	}

	@Override
	public CouponBean applyDiscount(String promoCode) {

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		CouponBean couponBean = new CouponBean();
		ResultSet rs = null;
		// Obtenir la date et l'heure actuelles
		Date currentDate = new Date();

		// Créer un objet SimpleDateFormat pour formater la date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Formatter la date actuelle
		String formattedDate = dateFormat.format(currentDate);

		try {

			ps = con.prepareStatement(
					"SELECT * FROM coupon where coupon_name=? AND coupon_expiredate > ? AND coupon_count > 0");
			ps.setString(1, promoCode);
			ps.setString(2, formattedDate);

			rs = ps.executeQuery();

			if (rs.next()) {
				couponBean.setCouponId(rs.getInt("coupon_id"));
				couponBean.setCouponName(rs.getString("coupon_name"));
				couponBean.setCouponCount(rs.getInt("coupon_count"));
				couponBean.setCouponExpireDate(rs.getDate("coupon_expiredate"));
				couponBean.setCouponDiscount(rs.getInt("coupon_discount"));
			}else {
				couponBean = null;
			}
		} catch (SQLException e) {
//			flag = false;
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return couponBean;
	}

	@Override
	public double totalPrice(int customerID) {
		double count = 0.00;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT SUM(sc.list_price * sc.quantity) AS total_price FROM  shoppingcartview sc  WHERE sc.customer_id = ?;");

			ps.setInt(1, customerID);

			rs = ps.executeQuery();

			if (rs.next() && !rs.wasNull())
				count = rs.getInt(1);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return count;
	}

}
