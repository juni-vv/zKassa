package com.juniper.kassa.exception;

import java.sql.SQLException;

public class ConnectionException extends SQLException {
	
	private String _reason = "[%S] An unknown error has occurred, contact the helpdesk.";
	
	public ConnectionException(String sqlState) {
		if(sqlState.equalsIgnoreCase("28000"))
			_reason = "Username or password invalid.";
		if(sqlState.equalsIgnoreCase("08001") || sqlState.equalsIgnoreCase("08006"))
			_reason = "[%S] The server could not be reached. Try again.";
		if(sqlState.equalsIgnoreCase("08S01") || sqlState.equalsIgnoreCase("HYT00"))
			_reason = "[%S] Connection refused. Try again.";
		
		if(sqlState.equalsIgnoreCase("42000"))
			_reason = "[%S] The database could not be found, contact the helpdesk.";
		
		if(sqlState.equalsIgnoreCase("42S02"))
			_reason = "[%S] The selected table or view could not be found, contact the helpdesk.";
		if(sqlState.equalsIgnoreCase("42S22"))
			_reason = "[%S] The selected column could not be found, contact the helpdesk.";
		
		_reason = _reason.replace("%S", sqlState.toUpperCase());
		System.out.println(_reason);
    }
	
	public ConnectionException(String sqlState, String customReason) {
		_reason = "[%S] " + customReason;
		_reason = _reason.replace("%S", sqlState.toUpperCase());
		System.out.println(_reason);
	}

	public String getReason() {
		return _reason;
	}
	
}