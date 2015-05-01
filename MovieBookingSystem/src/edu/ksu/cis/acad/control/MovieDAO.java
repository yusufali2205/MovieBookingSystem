package edu.ksu.cis.acad.control;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDAO {

    public int addMovie(Movie movie) {
        int rows_updated = 0;
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "INSERT INTO MOVIES "
            				+	"VALUES (?, ?, ?, ?, ?)";
            PreparedStatement add_movie_ps = dbConn.prepareStatement(query);
            add_movie_ps.setString(1, movie.getMid());
            add_movie_ps.setString(2, movie.getMname());
            add_movie_ps.setDate(3, movie.getRelease_date());
            add_movie_ps.setString(4, movie.getGenre());
            add_movie_ps.setBoolean(5, movie.isRunning());
            
            rows_updated = add_movie_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return rows_updated;
    }
    
    public int deleteMovie(String movie_id) {
    	int rows_deleted = 0;
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "DELETE FROM MOVIES "
    				+ "WHERE movie_id = ?";
    
			PreparedStatement delete_movie_ps = dbConn.prepareStatement(query);
			delete_movie_ps.setString(1, movie_id);
			
			rows_deleted = delete_movie_ps.executeUpdate();
            
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return rows_deleted;
    }
    
    public int editMovie(String movie_id) {
    	int rows_deleted = 0;
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "DELETE FROM MOVIES "
    				+ "WHERE movie_id = ?";
    
			PreparedStatement delete_movie_ps = dbConn.prepareStatement(query);
			delete_movie_ps.setString(1, movie_id);
			
			rows_deleted = delete_movie_ps.executeUpdate();
            
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return rows_deleted;
    }

}
