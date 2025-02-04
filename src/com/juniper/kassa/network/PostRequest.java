package com.juniper.kassa.network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

public class PostRequest extends ApiRequest {

	private HttpRequest.Builder requestBuilder;
	
	public PostRequest(String route) {
		super(route);
		
		requestBuilder = HttpRequest.newBuilder().uri(URI.create(ip + ":" + port + route)).header("Content-Type", "application/json");
	}
	
	public void sendJSON(JSONObject object) {
		requestBuilder.POST(BodyPublishers.ofString(object.toString()));
	}

	@Override
	public HttpResponse<?> send() throws IOException, InterruptedException, ConnectException {
		return HttpClient.newHttpClient().send(requestBuilder.build(), BodyHandlers.ofString());
	}
	
}
