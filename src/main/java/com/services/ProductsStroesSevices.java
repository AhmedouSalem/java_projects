package com.services;

import java.util.List;

import com.beans.ProductBean;

public interface ProductsStroesSevices {
	// chicko
	public ProductBean createProd(ProductBean product);
	
	// chicko
	public void removeProduct(Long prodId);
	
	// chicko
	public ProductBean updateProduct(ProductBean prdo);
	
	// chicko
	public ProductBean getProductDetails(Long prodId);
	
	
	// chicko
	public List<ProductBean> getAllProductsByNameFr(String nameFr);
	
	// chicko
	public List<ProductBean> getAllProd();
	
	// chicko
	public byte[] getImage(Long prodId);
	
	// chicko
	public ProductBean getProductById(Long prodId);
	
}
