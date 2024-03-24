package com.services.implement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.ProductBean;
import com.services.ProductsStroesSevices;
import com.utility.DBUtil;

public class ProductsStroesImplement implements ProductsStroesSevices {
	// chicko
	@Override
	public ProductBean createProd(ProductBean product) {
		Connection con = DBUtil.provideConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into products (product_name_fr,model_year,list_price,brand_id,category_id ,product_image) "
					+ "values (?,?,?,?,?,?)");
			ps.setString(1, product.getProductNameFr());
			ps.setShort(2,product.getModelYear());
			ps.setDouble(3,product.getListPrice());
			ps.setLong(4,product.getBrandId());
			ps.setLong(5,product.getCategoryId());
			ps.setBlob(6, product.getProdImage());
		
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = con.prepareStatement("select max(product_id) as MAX_ID from products ");
			ResultSet res = ps2.executeQuery();
			if(res.next()) {
				product.setProductId(res.getInt("MAX_ID"));
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return product;
	}
	
	// chicko
	@Override
	public List<ProductBean> getAllProductsByNameFr(String nameFr){
		
		Connection con = DBUtil.provideConnection();
		List<ProductBean> products = new ArrayList<>();
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("select product_id,product_name_fr,model_year,list_price,brand_name_fr,category_name_fr ,product_image "
					+ "from products as p ,brands as b , categories as c  "
					+ "where p.brand_id = b.brand_id and p.category_id=c.category_id and "
					+ "product_name_fr COLLATE utf8mb4_general_ci LIKE ? ");
			ps.setString(1, nameFr);
			ResultSet res = ps.executeQuery();

			while(res.next()) {
				ProductBean product = new ProductBean();
                product.setProductId(res.getInt("product_id"));
                product.setProductNameFr(res.getString("product_name_fr"));
                product.setModelYear(res.getShort("model_year"));
                product.setListPrice(res.getDouble("list_price"));
                product.setProdBrand(res.getString("brand_name_fr"));
                product.setProdCategory(res.getString("category_name_fr"));
                product.setProdImage(res.getBinaryStream("product_image"));
                
                products.add(product);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	// chicko
	@Override
	public void removeProduct(Long prodId) {
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("delete from products where product_id=? ");
			ps.setLong(1, prodId);
			
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// chicko 
	@Override
	public ProductBean updateProduct(ProductBean product) {
		Connection con = DBUtil.provideConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("update products set product_name_fr=?, model_year=?, list_price=?, brand_id=?, category_id=?, product_image=? where product_id=?");

			ps.setString(1, product.getProductNameFr());
			ps.setShort(2,product.getModelYear());
			ps.setDouble(3,product.getListPrice());
			ps.setLong(4,product.getBrandId());
			ps.setLong(5,product.getCategoryId());

			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	        int nRead;
	        byte[] data = new byte[1024];
	        while ((nRead = product.getProdImage().read(data, 0, data.length)) != -1) {
	            buffer.write(data, 0, nRead);
	        }
	        buffer.flush();
	        byte[] imageBytes = buffer.toByteArray();
	        
	        // Définir le champ prodImage avec le tableau de bytes obtenu
	        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
	        ps.setBinaryStream(6, inputStream, imageBytes.length);
	        
	        ps.setLong(7,Integer.parseInt(product.getProductId()));
			
			ps.executeUpdate();
		
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return product;
	}
	
	// chicko
	@Override
	public byte[] getImage(Long prodId) {
		byte[] image = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT product_image FROM productdetailsview where  product_id=?");

			ps.setLong(1,prodId);

			rs = ps.executeQuery();

			if (rs.next())
				image = rs.getBytes("product_image");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return image;
	}
	
	// chicko
		@Override
		public List<ProductBean> getAllProd() {
			Connection con = DBUtil.provideConnection();
			List<ProductBean> products = new ArrayList<>();
			
			PreparedStatement ps;
			
			try {
				ps = con.prepareStatement("select product_id,product_name_fr,model_year,list_price,brand_name_fr,category_name_fr ,product_image "
						+ "from products as p ,brands as b , categories as c "
						+ "where p.brand_id = b.brand_id and p.category_id=c.category_id");
				ResultSet res = ps.executeQuery();
				
				while(res.next()) {
					ProductBean product = new ProductBean();
	                product.setProductId(res.getInt("product_id"));
	                product.setProductNameFr(res.getString("product_name_fr"));
	                product.setModelYear(res.getShort("model_year"));
	                product.setListPrice(res.getDouble("list_price"));
	                product.setProdBrand(res.getString("brand_name_fr"));
	                product.setProdCategory(res.getString("category_name_fr"));
	                product.setProdImage(res.getBinaryStream("product_image"));
	                
	                products.add(product);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return products;
		}
	
	// chicko
		@Override
		public ProductBean getProductById(Long prodId) {
			ProductBean product = new ProductBean();
		    Connection con = DBUtil.provideConnection();
		    PreparedStatement ps = null;
//		    ResultSet rs = null;

		    try {
		    	ps = con.prepareStatement("select * from products where product_id=?");
		    	ps.setLong(1,prodId);
				ResultSet res = ps.executeQuery();
				
				if(res.next()) {
					
	                product.setProductId(res.getInt("product_id"));
	                product.setProductNameFr(res.getString("product_name_fr"));
	                product.setModelYear(res.getShort("model_year"));
	                product.setListPrice(res.getDouble("list_price"));
	                product.setBrandId(res.getInt("brand_id"));
	                product.setCategoryId(res.getInt("category_id"));
	                product.setProdImage(res.getBinaryStream("product_image"));
	                
	                
					
			}
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Fermer les ressources JDBC dans le bloc finally
		        DBUtil.closeConnection(ps);
		    }

		    return product;
		}

	@Override
	public ProductBean getProductDetails(Long prodId) {
		// TODO Auto-generated method stub
		return null;
	}

}
