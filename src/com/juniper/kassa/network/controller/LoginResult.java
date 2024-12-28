package com.juniper.kassa.network.controller;

public class LoginResult {
	
	private final String jwt;
	private final Type type;
	
	public LoginResult(Type type) {
		this.type = type;
		this.jwt = null;
	}
	
	public LoginResult(Type type, String jwt) {
		this.type = type;
		this.jwt = jwt;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getJWT() {
		return jwt;
	}
	
	public enum Type {
		Success, NoPermission, NoConnection
	}

}
