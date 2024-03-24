package com.services;

import java.util.ArrayList;

import com.beans.AddressBean;

public interface AddressService {
	public ArrayList<AddressBean> getCustomerAddress(int customerID) ;
}
