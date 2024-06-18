package com.services;

import java.util.List;

import com.beans.BrandsBean;



public interface BrandServices {
	public String createBrand(BrandsBean brand);
	public List<BrandsBean> getAllBrands();
	public String deleteBrand(Long id);
	public BrandsBean editBrand(BrandsBean brand);
	public BrandsBean getBrandById(Long id);
}