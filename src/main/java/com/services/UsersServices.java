package com.services;

import java.util.List;

import com.beans.UsersBean;

public interface UsersServices {
	public List<UsersBean> getAllUsers();

	public String deleteUser(Long id);

	public String activeCompteUser(UsersBean user);

	public UsersBean getUserById(Long id);
}
