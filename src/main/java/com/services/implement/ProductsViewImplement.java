package com.services.implement;

import java.util.List;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.ProductBean;
import com.services.ProductsService;
import com.utility.DBUtil;

public class ProductsViewImplement implements ProductsService {

	@Override
	public String addProduct(String productNameFr, int brandId, int categoryId, short modelYear, double listPrice,
			String prodCategory, String prodBrand, int prodQuantity, InputStream prodImage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addProduct(ProductBean product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeProduct(int prodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProduct(ProductBean prevProduct, ProductBean updatedProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProductPrice(int prodId, double updatedPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ProductBean> getAllProducts() {
		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from productdetailsview");

			rs = ps.executeQuery();

			while (rs.next()) {

				ProductBean product = new ProductBean();

				product.setProductId(rs.getInt("product_id"));
				product.setProductNameFr(rs.getString("product_name_fr"));
				product.setBrandId(rs.getInt("brand_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setModelYear(rs.getShort("model_year"));
				product.setListPrice(rs.getDouble("list_price"));
				product.setProdImage(rs.getAsciiStream("product_image"));
				product.setProdBrand(rs.getString("brand_name_fr"));
				product.setProdCategory(rs.getString("category_name_fr"));
				product.setProdQuantity(rs.getInt("quantity"));
				product.setDescription(rs.getNString("description"));

				products.add(product);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return products;
	}

	@Override
	public List<ProductBean> getAllProductsByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBean> searchAllProducts(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getImage(String prodId) {
		byte[] image = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT product_image FROM products where  product_id=?");

			ps.setInt(1, Integer.parseInt(prodId));

			rs = ps.executeQuery();

			if (rs.next())
				image = rs.getBytes("product_image");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return image;
	}

	@SuppressWarnings("null")
	@Override
	public ProductBean getProductDetails(int prodId) {
		ProductBean product = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from products where product_id=?");

			ps.setInt(1, prodId);
			rs = ps.executeQuery();

			if (rs.next()) {

				product.setProductId(rs.getInt("product_id"));
				product.setProductNameFr(rs.getString("product_name_fr"));
				product.setBrandId(rs.getInt("brand_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setModelYear(rs.getShort("model_year"));
				product.setListPrice(rs.getDouble("list_price"));
				product.setProdImage(rs.getAsciiStream("product_image"));
				product.setProdBrand(rs.getString("brand_name_fr"));
				product.setProdCategory(rs.getString("category_name_fr"));
				product.setProdQuantity(rs.getInt("quantity"));
				product.setDescription(rs.getString("description"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return product;
	}

	@Override
	public String updateProductWithoutImage(String prevProductId, ProductBean updatedProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getProductPrice(int prodId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean sellNProduct(int prodId, int n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getProductQuantity(int prodId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
