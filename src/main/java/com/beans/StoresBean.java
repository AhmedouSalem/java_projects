package com.beans;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class StoresBean implements Serializable {
	private Long ID;
	private String storeNameFr;
	private String storeNameAr;
	private String phone;
	private String email;
	private String street;
	private String city;
	private byte storesActive;
	private BigDecimal credit;

	public StoresBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoresBean(String storeNameAr, String storeNameFr, String phone, String email, String street, String city,
			byte storesActive, BigDecimal credit) {
		super();
		this.storeNameFr = storeNameFr;
		this.storeNameAr = storeNameAr;
		this.phone = phone;
		this.email = email;
		this.street = street;
		this.city = city;
		this.storesActive = storesActive;
		this.credit = credit;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getStoreNameFr() {
		return storeNameFr;
	}

	public void setStoreNameFr(String storeNameFr) {
		this.storeNameFr = storeNameFr;
	}

	public String getStoreNameAr() {
		return storeNameAr;
	}

	public void setStoreNameAr(String storeNameAr) {
		this.storeNameAr = storeNameAr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte getStoresActive() {
		return storesActive;
	}

	public void setStoresActive(byte storesActive) {
		this.storesActive = storesActive;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "StoresBean [ID=" + ID + ", storeNameFr=" + storeNameFr + ", storeNameAr=" + storeNameAr + ", phone="
				+ phone + ", email=" + email + ", street=" + street + ", city=" + city + ", storesActive="
				+ storesActive + ", credit=" + credit + "]";
	}

}
