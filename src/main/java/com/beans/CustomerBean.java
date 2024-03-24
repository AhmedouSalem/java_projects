package com.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerBean implements Serializable {
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerPassword;
    private String addressStreet;
    private String addressCity;
    private int addressId;
    private int cartCount;
    private int verifyCode;
    private long isActive;

    // Constructors
    public CustomerBean() {
    }

    public CustomerBean(String customerName, String customerPhone, String customerEmail, String customerPassword, String addressStreet, String addressCity, int addressId, int cartCount) {
//        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressId = addressId;
        this.cartCount = cartCount;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = Integer.parseInt(customerId);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = Integer.parseInt(addressId);
    }
    
    public int getCartCount() {
		return cartCount;
	}
    
    public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
    
    public int getVerifyCode() {
		return verifyCode;
	}
    
    public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}
    
    public long getIsActive() {
		return isActive;
	}
    
    public void setIsActive(long isActive) {
		this.isActive = isActive;
	}
}
