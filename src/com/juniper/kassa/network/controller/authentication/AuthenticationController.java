package com.juniper.kassa.network.controller.authentication;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

import com.juniper.kassa.network.PostRequest;
import com.juniper.kassa.network.controller.Controller;
import com.juniper.kassa.network.controller.authentication.LoginResult.Type;

public class AuthenticationController {

	public LoginResult attemptLogin(String username, String password) {
		String route = "/Authentication/Login";

		JSONObject json = new JSONObject();
		json.put("userName", username);
		json.put("password", password);
		
		try {
			PostRequest request = new PostRequest(route);
			request.sendJSON(json);
			
			HttpResponse<String> response = (HttpResponse<String>) request.send();
			
			if(response.statusCode() == 200)
				return new LoginResult(Type.Success, response);		
		} catch(ConnectException ce) {
			return new LoginResult(Type.NoConnection, null, ce);
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return new LoginResult(Type.NoPermission, null);
	}

}