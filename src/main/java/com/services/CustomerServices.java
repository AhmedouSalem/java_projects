package com.services;

import com.beans.CustomerBean;

public interface CustomerServices {
	/*
	 * private String userName; private Long mobileNo; private String emailId;
	 * private String address; private int pinCode; private String password;
	 */

	public String registerUser(String userName, Long mobileNo, String emailId, String address, int pinCode,
			String password);

	public String registerUser(CustomerBean customer);

	public boolean isRegistered(String emailId);

	public String isValidCredential(String emailId, String password);

	public CustomerBean getCustomerDetails(String emailId, String password);

	public String getFName(String emailId);

	public String getUserAddr(String userId);
	public String isApprouvedUser(String email, int verifyCode, Boolean signUpOrResetPassword);
	public String resetPassword(String email, String password);
}
