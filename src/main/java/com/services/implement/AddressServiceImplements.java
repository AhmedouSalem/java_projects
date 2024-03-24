package com.services.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.AddressBean;
import com.services.AddressService;
import com.utility.DBUtil;

public class AddressServiceImplements implements AddressService {

	@Override
	public ArrayList<AddressBean> getCustomerAddress(int customerID) {
		ArrayList<AddressBean> address = new ArrayList<AddressBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("select * from address where customer_address=?");

			ps.setInt(1, customerID);

			rs = ps.executeQuery();

			while (rs.next()) {
				AddressBean addressBean = new AddressBean();

				addressBean.setAddressId(rs.getInt("address_id"));
				addressBean.setCustomerAddress(rs.getInt("customer_address"));
				addressBean.setAddressStreet(rs.getString("address_street"));
				addressBean.setAddressCity(rs.getString("address_city"));
				address.add(addressBean);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return address;
	}

}
