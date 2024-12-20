package com.juniper.kassa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.juniper.kassa.exception.ConnectionException;
import com.juniper.kassa.exception.RepairTool;

public class DatabaseConnection {

	private Connection _connection;

	public DatabaseConnection(String username, String password) throws ConnectionException {
		System.out.println(RepairTool.run(username, password));
		
		if(password == "") { //TODO: Remove after testing repair tool is complete
			try {
				establishDatabaseConnection(username, password);
			} catch(SQLException e) {
				throw new ConnectionException(e.getSQLState());
			}

			Database.setConnection(this);
		}
	}

	public boolean close() {
		try {
			if(_connection != null && !_connection.isClosed()) {
				_connection.close();
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private void establishDatabaseConnection(String username, String password) throws SQLException {
		String     url        = "jdbc:mysql://localhost:3306/terry_db";
		Connection connection = DriverManager.getConnection(url, username, password);
		_connection = connection;
	}

}
