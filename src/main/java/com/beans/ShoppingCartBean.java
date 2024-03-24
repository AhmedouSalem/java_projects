package com.beans;

import java.io.InputStream;
import java.io.Serializable;

public class ShoppingCartBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int productId;
    private int customerId;
    private int quantity;
    private String productNameFr;
    private String brandNameFr;
    private String categoryNameFr;
    private int modelYear;
    private double listPrice;
    private InputStream productImage;
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    // Constructors, getters, and setters
    public ShoppingCartBean() {
    }

    public ShoppingCartBean(int productId, int customerId, int quantity, String productNameFr, String brandNameFr,
            String categoryNameFr, int modelYear, double listPrice, InputStream productImage, String customerName,
            String customerPhone, String customerEmail) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productNameFr = productNameFr;
        this.brandNameFr = brandNameFr;
        this.categoryNameFr = categoryNameFr;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
        this.productImage = productImage;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductNameFr() {
        return productNameFr;
    }

    public void setProductNameFr(String productNameFr) {
        this.productNameFr = productNameFr;
    }

    public String getBrandNameFr() {
        return brandNameFr;
    }

    public void setBrandNameFr(String brandNameFr) {
        this.brandNameFr = brandNameFr;
    }

    public String getCategoryNameFr() {
        return categoryNameFr;
    }

    public void setCategoryNameFr(String categoryNameFr) {
        this.categoryNameFr = categoryNameFr;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public InputStream getProductImage() {
        return productImage;
    }

    public void setProductImage(InputStream productImage) {
        this.productImage = productImage;
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
}
