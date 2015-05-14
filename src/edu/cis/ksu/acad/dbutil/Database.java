package edu.cis.ksu.acad.dbutil;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Database {

    public ResultSet result(String query) throws Exception {
    	Connection dbConn = openConnection();
    	PreparedStatement ps = dbConn.prepareStatement(query);
    	System.out.println(query);
    	ResultSet rs = ps.executeQuery();
        return rs;
    }
    private static Properties configProps = new Properties();

	private static String MySqlServerDriver;
	private static String MySqlServerUrl;
    private static String MySqlServerUser;
	private static String MySqlServerPassword;

		public Connection openConnection() throws Exception {
			// DB Connection
			Connection _mydb;
			configProps.load(new FileInputStream("/Users/ashkrishna/git/MovieWithRecommendation_neww/config/dbconn.config"));
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
