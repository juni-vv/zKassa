package com.juniper.kassa.network.controller.authentication;

import java.net.http.HttpResponse;

public class LoginResult {

	private final Type                 type;
	private final HttpResponse<String> response;

	public LoginResult(Type type, HttpResponse<String> response) {
		this.type = type;
		this.response = response;
	}

	public Type getType() {
		return type;
	}
	
	public HttpResponse<String> getResponse() {
		return response;
	}

	public enum Type {
		Success, NoPermission, NoConnection
	}

}
