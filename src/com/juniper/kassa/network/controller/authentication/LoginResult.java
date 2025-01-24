package com.juniper.kassa.network.controller.authentication;

import java.net.ConnectException;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.juniper.kassa.model.UserRole;

public class LoginResult {

	private final Type                 type;
	private final HttpResponse<String> response;
	private final ConnectException  ce;

	public LoginResult(Type type, HttpResponse<String> response) {
		this.type = type;
		this.response = response;
		this.ce = null;
	}

	public LoginResult(Type type, HttpResponse<String> response, ConnectException ce) {
		this.type = type;
		this.response = response;
		this.ce = ce;
	}

	public Type getType() {
		return type;
	}

	public String getToken() {
		JSONObject responseObject = new JSONObject(response.body());
		return responseObject.getString("jwt");
	}
	
	public UserRole getUserRole() {
		JSONObject responseObject = new JSONObject(response.body());
		return UserRole.fromInt(responseObject.getInt("role"));
	}

	public enum Type {
		Success, NoPermission, NoConnection
	}
	
	public ConnectException getConnectionException() {
		return ce;
	}

}
