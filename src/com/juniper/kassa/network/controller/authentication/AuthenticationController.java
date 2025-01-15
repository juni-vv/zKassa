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

import com.juniper.kassa.network.controller.Controller;
import com.juniper.kassa.network.controller.authentication.LoginResult.Type;

public class AuthenticationController extends Controller {

	public LoginResult attemptLogin(String passcode) {
		String route = "/Authentication/Login";

		JSONObject json = new JSONObject();
		json.put("UserName", passcode);

		HttpRequest request = postRequest(route, json);
		
		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

			if(response.statusCode() == 200)
				return new LoginResult(Type.Success, response);
			
			
		} catch(ConnectException ce) {
			return new LoginResult(Type.NoConnection, null);
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return new LoginResult(Type.NoPermission, null);
	}

}