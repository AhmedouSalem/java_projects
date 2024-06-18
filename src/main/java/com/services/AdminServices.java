package com.services;

import com.beans.Admin;

public interface AdminServices {
	public String login(String userName, String password);
	public Admin getDetails(String userName, String password);
}
