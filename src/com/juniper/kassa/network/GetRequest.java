package com.juniper.kassa.network;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class GetRequest extends ApiRequest {

	private HttpRequest.Builder requestBuilder;
	
	public GetRequest(String route) {
		super(route);
		
		requestBuilder = HttpRequest.newBuilder().uri(URI.create(ip + ":" + port + route)).GET();
	}
	
	public void useToken(String token) {
		requestBuilder.header("Authorization", "Bearer " + token);
	}

	@Override
	public HttpResponse<?> send() throws IOException, InterruptedException {		
		return HttpClient.newHttpClient().send(requestBuilder.build(), BodyHandlers.ofString());
	}

}
