package com.juniper.kassa.model;

import com.juniper.kassa.network.controller.authentication.UserRole;

public class User {
	
	private UserRole role;
	private String jwt;
	
	public User(UserRole role, String jwt) {
		this.role = role;
		this.jwt = jwt;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public String getToken() {
		return jwt;
	}

}
