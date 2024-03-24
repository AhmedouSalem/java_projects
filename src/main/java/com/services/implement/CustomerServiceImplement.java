package com.services.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.beans.CustomerBean;
import com.services.CustomerServices;
import com.utility.DBUtil;
import com.utility.MailMessage;

public class CustomerServiceImplement implements CustomerServices {

	@Override
	public String registerUser(String userName, Long mobileNo, String emailId, String address, int pinCode,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerUser(CustomerBean customer) {
		String status = "User Registration Failed!";

		boolean isRegtd = isRegistered(customer.getCustomerEmail());

		if (isRegtd) {
			status = "Email existe déja!";
			return status;
		}
		Connection conn = DBUtil.provideConnection();
		PreparedStatement ps = null;
		if (conn != null) {
			System.out.println("Connected Successfully!");
		}

		try {
			Random random = new Random();
			int verifyCode = random.nextInt(1000, 9999);

			ps = conn.prepareStatement(
					"insert into customers(customer_name, customer_phone, customer_email, customer_password, verify_code, is_active) values(?, ?, ?, md5(?), ? , ?)");

			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getCustomerPhone());
			ps.setString(3, customer.getCustomerEmail());
			ps.setString(4, customer.getCustomerPassword());
			ps.setInt(5, verifyCode);
			ps.setInt(6, 0);

			int k = ps.executeUpdate();

			if (k > 0) {
				status = "Régistration effectuée avec succès!";
				MailMessage.registrationSuccess(customer.getCustomerEmail(), customer.getCustomerName(), verifyCode,
						"Régistration effectuée avec succès");
			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(ps);

		return status;
	}

	@Override
	public boolean isRegistered(String emailId) {
		boolean flag = false;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from customers where customer_email=?");

			ps.setString(1, emailId);

			rs = ps.executeQuery();

			if (rs.next())
				flag = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return flag;
	}

	@Override
	public String isValidCredential(String emailId, String password) {
		String status = "Connexion refusée ! Identifiant ou mot de passe incorrect";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from CustomerView where customer_email=? and customer_password=md5(?)");

			ps.setString(1, emailId);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				status = "Vous avez été connecté avec succès";
			}
		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		return status;
	}

	@Override
	public CustomerBean getCustomerDetails(String emailId, String password) {
		CustomerBean customer = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from CustomerView where customer_email=? and customer_password=md5(?)");
			ps.setString(1, emailId);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				customer = new CustomerBean();
				customer.setCustomerId(String.valueOf(rs.getInt("customer_id")));
				customer.setCustomerName(rs.getString("customer_name"));
				customer.setCustomerEmail(rs.getString("customer_email"));
				customer.setCustomerPhone(rs.getString("customer_phone"));
				customer.setCustomerPassword(rs.getString("customer_password"));
				customer.setAddressStreet(rs.getString("address_street"));
				customer.setAddressCity(rs.getString("address_city"));
				customer.setAddressId(String.valueOf(rs.getInt("address_id")));
				customer.setCartCount(rs.getInt("num_products_in_cart"));
				customer.setIsActive(rs.getInt("is_active"));
				customer.setVerifyCode(rs.getInt("verify_code"));

				return customer;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return customer;
	}

	public int getCustomerID(String email) {
		int customerID = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from CustomerView where customer_email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				customerID = rs.getInt("customer_id");
				return customerID;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return customerID;
	}

	@Override
	public String getFName(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserAddr(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String isApprouvedUser(String email, int verifyCode, Boolean signUpOrResetPassword) {
		String status = "Le code de vérification est incorrect";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from customers where customer_email=? AND verify_code = ?");

			ps.setString(1, email);
			ps.setInt(2, verifyCode);

			rs = ps.executeQuery();

			if (rs.next()) {
				if (signUpOrResetPassword) {
					ps1 = con.prepareStatement("update customers set is_active = 1 where customer_email = ?");
					ps1.setString(1, email);
					ps1.executeUpdate();
					status = "Votre compte a été approuvé avec succès! Veuillez se connecter pour y acceder";
				} else {
					status = "Votre compte a été approuvé avec succès! Veuillez réuinillialiser votre mot de passe";
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return status;
	}

	public void resendCodeVerification(String email) {
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from customers where customer_email=? AND is_active = 0");

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				int verifyCode = rs.getInt("verify_code");
				String username = rs.getString("customer_name");
				MailMessage.registrationSuccess(email, username, verifyCode,
						"Demande de renvoi du code de verification");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
	}

	public void addAddress(int customerID, String street, String city) {
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(
					"insert into address (customer_address, address_street, address_city) values(?, ?, ?)");

			ps.setInt(1, customerID);
			ps.setString(2, street);
			ps.setString(3, city);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
	}

	@Override
	public String resetPassword(String email, String password) {
		String status = "Echec de réuinillalisation de mot de passe, veuillez essayer ultériement";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("update customers set customer_password = md5(?) where customer_email = ?");

			ps.setString(1, password);
			ps.setString(2, email);

			int k = ps.executeUpdate();

			if (k > 0) {
				status = "Le mot de passe a été réuinillialisé avec succès, veuillez se connecter pour acceder à votre compte";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return status;
	}
}
