package com.beans;

import java.io.Serializable;

public class BrandsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long brandId;
	private String BrandName;

	public BrandsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BrandsBean(String brandName) {
		super();
		BrandName = brandName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	@Override
	public String toString() {
		return "BrandsBean [brandId=" + brandId + ", BrandName=" + BrandName + "]";
	}

}
