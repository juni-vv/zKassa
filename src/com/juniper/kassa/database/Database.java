package com.juniper.kassa.database;

public class Database {

	private static DatabaseConnection _connection;

	protected static void setConnection(DatabaseConnection connection) {
		_connection = connection;
	}
	
	public static DatabaseConnection getConnection() {
		return _connection;
	}
}
