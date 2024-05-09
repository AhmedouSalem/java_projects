package com.services.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.StoresBean;
import com.services.StoreService;
//import com.services.StoreService;
import com.utility.DBUtil;

public class StoreServiceImpl implements StoreService {

	@Override
	public StoresBean createStore(StoresBean store) {
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("insert into stores (store_name_ar,store_name_fr,phone,email,street,city,stores_active,credit)"
					+ " values (?,?,?,?,?,?,?,?)");
			ps.setString(1, store.getStoreNameAr());
			ps.setString(2, store.getStoreNameFr());
			ps.setString(3, store.getPhone());
			ps.setString(4, store.getEmail());
			ps.setString(5, store.getStreet());
			ps.setString(6, store.getCity());
			ps.setByte(7, store.getStoresActive());
			ps.setBigDecimal(8, store.getCredit());
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = cnx.prepareStatement("select max(store_id) as MAX_ID from stores ");
			ResultSet res = ps2.executeQuery();
			if(res.next()) {
				store.setID(res.getLong("MAX_ID"));
			}
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return store;
	
	}

	@Override
	public List<StoresBean> getAllStore() {
		Connection cnx = DBUtil.provideConnection();
		List<StoresBean> stores = new ArrayList<StoresBean>();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from stores");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				StoresBean s = new StoresBean();
				s.setID(res.getLong("store_id"));
				s.setStoreNameAr(res.getString("store_name_ar"));
				s.setStoreNameFr(res.getString("store_name_fr"));
				s.setPhone(res.getString("phone"));
				s.setEmail(res.getString("email"));
				s.setStreet(res.getString("street"));
				s.setCity(res.getString("city"));
				s.setStoresActive(res.getByte("stores_active"));
				s.setCredit(res.getBigDecimal("credit"));
				
				stores.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return stores;
	}

	@Override
	public void deleteStore(Long id) {
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("delete from stores where store_id=? ");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public StoresBean editStore(StoresBean store) {
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("update stores set store_name_ar=?,store_name_fr=?,phone=?,email=?,street,city=?,stores_active=?,credit=?"
					+ " where store_id=?");
			ps.setString(1, store.getStoreNameAr());
			ps.setString(2, store.getStoreNameFr());
			ps.setString(3, store.getPhone());
			ps.setString(4, store.getEmail());
			ps.setString(5, store.getStreet());
			ps.setString(6, store.getCity());
			ps.setByte(7, store.getStoresActive());
			ps.setBigDecimal(8, store.getCredit());
			ps.setLong(8, store.getID());
			
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return store;
	}
	
//	store_name_ar
	@Override
	public List<StoresBean> storeParNameAr(String mc) {
		Connection cnx = DBUtil.provideConnection();
		List<StoresBean> stores = new ArrayList<StoresBean>();
		try {
			PreparedStatement ps = cnx.prepareStatement("SELECT * FROM stores WHERE store_name_ar COLLATE utf8mb4_general_ci LIKE ? ");
			ps.setString(1, mc);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				StoresBean s = new StoresBean();
				s.setID(res.getLong("store_id"));
				s.setStoreNameAr(res.getString("store_name_ar"));
				s.setStoreNameFr(res.getString("store_name_fr"));
				s.setPhone(res.getString("phone"));
				s.setEmail(res.getString("email"));
				s.setStreet(res.getString("street"));
				s.setCity(res.getString("city"));
				s.setStoresActive(res.getByte("stores_active"));
				s.setCredit(res.getBigDecimal("credit"));
				
				stores.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();		
		}
		return stores;
	}
	
	
	public StoresBean getStoreById(Long id) {
		Connection cnx = DBUtil.provideConnection();
		StoresBean s = new StoresBean();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from stores where store_id=?");
			ps.setLong(1, id);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				s.setID(res.getLong("store_id"));
				s.setStoreNameAr(res.getString("store_name_ar"));
				s.setStoreNameFr(res.getString("store_name_fr"));
				s.setPhone(res.getString("phone"));
				s.setEmail(res.getString("email"));
				s.setStreet(res.getString("street"));
				s.setCity(res.getString("city"));
				s.setStoresActive(res.getByte("stores_active"));
				s.setCredit(res.getBigDecimal("credit"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();		
		}
		return s;
	}

}
