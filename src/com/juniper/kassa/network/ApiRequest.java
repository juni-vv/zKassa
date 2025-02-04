package com.juniper.kassa.network;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import com.juniper.kassa.configuration.Configuration;
import com.juniper.kassa.configuration.ConfigurationHandler;

public abstract class ApiRequest {
	
	protected final String route;
	
	protected final String ip;
	protected final String port;
	
	public ApiRequest(String route) {
		this.route = route;
		
		Configuration config = ConfigurationHandler.loadConfig("network.yml");
		this.ip = config.get("ip");
		this.port = config.get("port");
	}

	public abstract HttpResponse<?> send() throws IOException, InterruptedException;

}
