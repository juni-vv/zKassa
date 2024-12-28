package com.juniper.kassa.network.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

import com.juniper.kassa.network.controller.LoginResult.Type;

public class AuthenticationController extends Controller {

	public LoginResult attemptLogin(String passcode) {
		String route = "/Authentication/Login";

		JSONObject json = new JSONObject();
		json.put("UserName", passcode);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ip + ":" + port + route)).header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
		
		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

			if(response.statusCode() == 200)
				return new LoginResult(Type.Success, response.body());
		} catch(ConnectException ce) {
			return new LoginResult(Type.NoConnection);
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return new LoginResult(Type.NoPermission);
	}

}