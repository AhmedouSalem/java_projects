package com.services;

import java.util.List;

import com.beans.StoresBean;

public interface StoreService {
	public StoresBean createStore(StoresBean store);
	public List<StoresBean> getAllStore();
	public void deleteStore(Long id);
	public StoresBean editStore(StoresBean store);
	public List<StoresBean> storeParNameAr(String mc);
	public StoresBean getStoreById(Long id);
}
