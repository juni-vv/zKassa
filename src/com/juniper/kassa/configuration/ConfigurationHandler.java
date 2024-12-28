package com.juniper.kassa.configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConfigurationHandler {

	private static List<Configuration> configurations = new CopyOnWriteArrayList<Configuration>();

	public static Configuration loadConfig(String path) {
		File file = new File("C:/zKassa/" + path);

		if(!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			throw new RuntimeException("Failed to create directories for: " + file.getParent());
		}
		
		if (!file.exists()) {
	        try {
	            if (!file.createNewFile()) {
	                throw new RuntimeException("Failed to create file: " + file.getAbsolutePath());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Error creating file: " + file.getAbsolutePath(), e);
	        }
	    }

		synchronized (configurations) {
			for(Configuration configuration : configurations)
				if(configuration.getFilePath().equals(file.getPath()))
					return configuration;

			Configuration config = new Configuration(file);
			configurations.add(config);
			return config;
		}
	}

}
