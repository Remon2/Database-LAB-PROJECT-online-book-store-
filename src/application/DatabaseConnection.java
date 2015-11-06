//STEP 1. Import required packages
package application;

import java.sql.*;

public class DatabaseConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	private static Statement stmt = null;
	public static Connection conn = null;

	public static void connect() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS STUDENTS";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");

			String dbName = "book_store";
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL + dbName, USER, PASS);

			System.out.println("Connected database successfully...");

			stmt = conn.createStatement();

			System.out.println("Creating table...");
			sql = "create table if not exists student(id integer not null,name varchar(20),age integer,phone varchar(15))";
			// stmt.execute(sql);
			System.out.println("Table created successfully...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main

	public static ResultSet execute(String query) throws SQLException {
		 stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	public static void execute2(String query) throws SQLException {
		 stmt = conn.createStatement();
		stmt.executeUpdate(query);

	}
	
}// end JDBCExample

