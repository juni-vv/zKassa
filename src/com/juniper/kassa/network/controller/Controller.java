package com.juniper.kassa.network.controller;

import com.juniper.kassa.configuration.Configuration;
import com.juniper.kassa.configuration.ConfigurationHandler;

public abstract class Controller {
	
	protected final String ip, port;
	
	public Controller() {
		Configuration config = ConfigurationHandler.loadConfig("network.yml");
		this.ip = config.get("ip");
		this.port = config.get("port");
	}

}
