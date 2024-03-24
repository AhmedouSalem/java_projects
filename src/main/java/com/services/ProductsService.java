package com.services;
import java.io.InputStream;
import java.util.List;
import com.beans.ProductBean;
public interface ProductsService {
	public String addProduct(String productNameFr, int brandId, int categoryId, short modelYear, double listPrice,
    		String prodCategory, String prodBrand,int prodQuantity, InputStream prodImage);

	public String addProduct(ProductBean product);

	public String removeProduct(int prodId);

	public String updateProduct(ProductBean prevProduct, ProductBean updatedProduct);

	public String updateProductPrice(int prodId, double updatedPrice);

	public List<ProductBean> getAllProducts();

	public List<ProductBean> getAllProductsByType(String type);

	public List<ProductBean> searchAllProducts(String search);

	public byte[] getImage(String prodId);

	public ProductBean getProductDetails(int prodId);

	public String updateProductWithoutImage(String prevProductId, ProductBean updatedProduct);

	public double getProductPrice(int prodId);

	public boolean sellNProduct(int prodId, int n);

	public int getProductQuantity(int prodId);
}
