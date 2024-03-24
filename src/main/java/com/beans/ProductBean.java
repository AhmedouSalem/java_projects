package com.beans;

import java.io.InputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductBean implements Serializable {
	private String productId;
	private String productNameFr;
	private int brandId;
	private int categoryId;
	private short modelYear;
	private double listPrice;
	private String prodCategory;
	private String prodBrand;
	private int prodQuantity;
	private InputStream prodImage;

	// Constructors
	public ProductBean() {
	}

	public ProductBean(int productId, String productNameFr, int brandId, int categoryId, short modelYear,
			double listPrice, String prodCategory, String prodBrand, int prodQuantity, InputStream prodImage) {
		this.productId = Integer.toString(productId);
		this.productNameFr = productNameFr;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.modelYear = modelYear;
		this.listPrice = listPrice;
		this.prodCategory = prodCategory;
		this.prodBrand = prodBrand;
		this.prodQuantity = prodQuantity;
		this.prodImage = prodImage;
	}

	public ProductBean(String productNameFr, short modelYear, double listPrice, String prodCategory, String prodBrand,
			InputStream prodImage) {
		super();
		this.productNameFr = productNameFr;
		this.modelYear = modelYear;
		this.listPrice = listPrice;
		this.prodCategory = prodCategory;
		this.prodBrand = prodBrand;
		this.prodImage = prodImage;
	}
	
	// Getters and Setters
	public String getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = Integer.toString(productId);
	}

	public String getProductNameFr() {
		return productNameFr;
	}

	public void setProductNameFr(String productNameFr) {
		this.productNameFr = productNameFr;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public short getModelYear() {
		return modelYear;
	}

	public void setModelYear(short modelYear) {
		this.modelYear = modelYear;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public String getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public int getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public InputStream getProdImage() {
		return prodImage;
	}

	public void setProdImage(InputStream prodImage) {
		this.prodImage = prodImage;
	}
}
