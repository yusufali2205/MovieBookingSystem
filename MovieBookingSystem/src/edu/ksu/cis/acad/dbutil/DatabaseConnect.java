package edu.ksu.cis.acad.dbutil;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnect {

		private static Properties configProps = new Properties();

		private static String MySqlServerDriver;
		private static String MySqlServerUrl;
	    private static String MySqlServerUser;
		private static String MySqlServerPassword;

		public Connection openConnection() throws Exception {
			// DB Connection
			Connection _mydb;
			configProps.load(new FileInputStream("/Users/ashkrishna/git/MovieBookingSystem/MovieBookingSystem/config/dbconn.config"));
			MySqlServerDriver    = configProps.getProperty("MySqlServerDriver");
			MySqlServerUrl 	   = configProps.getProperty("MySqlServerUrl");
			MySqlServerUser 	   = configProps.getProperty("MySqlServerUser");
			MySqlServerPassword  = configProps.getProperty("MySqlServerPassword");

			/* load jdbc drivers */
			Class.forName(MySqlServerDriver).newInstance();

			/* open a connection to your mySQL database that contains movie and the customer databases */
			_mydb = DriverManager.getConnection(MySqlServerUrl, // database
					MySqlServerUser, // user
					MySqlServerPassword); // password	

			return _mydb;
		}

}
