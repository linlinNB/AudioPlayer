package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public final class DbUtil {
	private  String url ="jdbc:mysql://localhost:3306/ajax";
	private String user = "root";
	private  String password = "123456";
	private static DbUtil instance = new DbUtil();
	
	private DbUtil() {};
	static {
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	

	public static DbUtil getInstance() {
		return instance;
	}
	
	
	public  Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public  void free(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}