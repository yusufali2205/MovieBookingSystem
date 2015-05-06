package edu.ksu.cis.acad.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.User;

public class UserDAO {
	
	public int createUser(User user) {
		int user_created = 0;
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "INSERT INTO USER "
            				+	"VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement add_user_ps = dbConn.prepareStatement(query);
            add_user_ps.setString(1, user.getUsername());
            add_user_ps.setString(2, user.getFirst_name());
            add_user_ps.setString(3, user.getLast_name());
            add_user_ps.setString(4, user.getType());
            add_user_ps.setString(5, user.getPassword());
            add_user_ps.setString(6, user.getEmail());
            
            user_created = add_user_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return user_created;

	}
	
	// if credentials match a user object will be returned with user attributed
	// else an empty user object will be returned (user object with no attributes initialized)
	public User login(String username, String password) {
		User user = new User();
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "SELECT * FROM USER "
            				+	"WHERE username=? AND password=?";
            PreparedStatement get_user_ps = dbConn.prepareStatement(query);
            get_user_ps.setString(1, username);
            get_user_ps.setString(2, password);
            ResultSet user_rs = get_user_ps.executeQuery();
            user_rs.next();
            
            user.setUsername(user_rs.getString(1));
            user.setFirst_name(user_rs.getString(2));
            user.setLast_name(user_rs.getString(3));
            user.setType(user_rs.getString(4));
            user.setEmail(user_rs.getString(6));
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return user;

	}
	
	// I don't think we require update user
}
