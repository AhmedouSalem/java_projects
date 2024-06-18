package com.services;

import java.util.List;

import com.beans.CategoriesBean;



public interface CategorieServices {
	public String createCat(CategoriesBean cat);
	public List<CategoriesBean> getAllCategorie();
	public String deleteCategorie(Long id);
	public CategoriesBean editCatgorie(CategoriesBean cat);
	public CategoriesBean getCatById(Long id);
}
