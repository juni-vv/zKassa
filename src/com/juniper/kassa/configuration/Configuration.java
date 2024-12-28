package com.juniper.kassa.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private File                configFile;
	private Map<String, String> keyValues;

	public Configuration(File configFile) {
		this.configFile = configFile;
		this.keyValues = new HashMap<>();

		load();
	}

	private void load() {
		if(!configFile.exists())
			return;

		try(BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
			String line;
			while((line = reader.readLine()) != null) {
				line = line.trim();

				if(line.isEmpty() || line.startsWith("#"))
					continue;

				String[] parts = line.split("=", 2);
				if(parts.length == 2)
					keyValues.put(parts[0].trim(), parts[1].trim());
			}
		} catch(IOException e) {
			throw new RuntimeException("Failed to load configuration file: " + configFile.getPath(), e);
		}
	}

	public String get(String key) {
		return keyValues.get(key);
	}

	public String getFilePath() {
		return configFile.getPath();
	}

}
