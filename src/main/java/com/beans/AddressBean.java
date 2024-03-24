package com.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AddressBean implements Serializable {

	private int addressId;
	private int customerAddress;
	private String addressStreet;
	private String addressCity;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(int customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
}
