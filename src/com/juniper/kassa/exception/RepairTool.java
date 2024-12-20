package com.juniper.kassa.exception;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RepairTool {
	
	private static boolean isRunning = false;
	
	public static String run(String username, String password) {
		if(isRunning)
			return "RepairTool is already running.";
		
		isRunning = true;
		
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "java -jar lib/RepairTool.jar " + username + " " + password);
			pb.redirectErrorStream(true);
			
			Process process = pb.start();
			
			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			int exitCode = process.waitFor();
			return "RepairTool exited with code " + exitCode;
			
		} catch(Exception e) {
			return "RepairTool exited unexpectedly:\n" + e.getStackTrace().toString();
		}
	}

}
