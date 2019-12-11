package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConn {
	

	public static Connection  getconn() {
		Connection conn=null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=dream";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, "sa", "yhf4454");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}
}
