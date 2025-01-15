package com.juniper.kassa.network.controller;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import org.json.JSONObject;

import com.juniper.kassa.configuration.Configuration;
import com.juniper.kassa.configuration.ConfigurationHandler;

public abstract class Controller {

	protected final String ip, port;

	protected HttpRequest postRequest(String route, JSONObject json) {
		return HttpRequest.newBuilder().uri(URI.create(ip + ":" + port + route)).header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
	}

	protected HttpRequest getRequest(String route, String jwt) {
		return HttpRequest.newBuilder().uri(URI.create(ip + ":" + port + route)).header("Authorization", "Bearer " + jwt).GET().build();
	}

	public Controller() {
		Configuration config = ConfigurationHandler.loadConfig("network.yml");
		this.ip = config.get("ip");
		this.port = config.get("port");
	}

}
