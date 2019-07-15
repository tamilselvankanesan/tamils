package com.success;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NdbMySqlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// auto close connection
		one();
		/*
		 * try (Connection conn = DriverManager.getConnection(
		 * "jdbc:mysql://ndb-new.ca2moh2lvovn.us-east-1.rds.amazonaws.com:3306/ndb_dev",
		 * "ndb_app", "Ndb_test1")) { if (conn != null) {
		 * System.out.println("Connected to the database!"); } else {
		 * System.out.println("Failed to make connection!"); }
		 * 
		 * } catch (SQLException e) { System.err.format("SQL State: %s\n%s",
		 * e.getSQLState(), e.getMessage()); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}
	
	private static void one() {
		 // This will load the MySQL driver, each DB has its own driver
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
	        Connection conn = DriverManager
	                .getConnection(
	                        "jdbc:mysql://ndb-new.ca2moh2lvovn.us-east-1.rds.amazonaws.com:3306/ndb_dev", "ndb_app", "Ndb_test1");
	        if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

	        // Statements allow to issue SQL queries to the database
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
