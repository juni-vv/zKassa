package com.juniper.kassa.network.controller.authentication;

import java.net.http.HttpResponse;

import org.json.JSONObject;

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
	
	public String getToken() {
		JSONObject responseObject = new JSONObject(response.body());
		return responseObject.getString("jwt");
	}

	public enum Type {
		Success, NoPermission, NoConnection
	}

}
