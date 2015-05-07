package edu.ksu.cis.acad.control;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            add_movie_ps.setString(1, "");
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
    
    public int editMovie(Movie movie) {
    	int rows_updated = 0;
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "UPDATE MOVIES SET movie_name=?, release_date=?, genre=?, running=? "
    				+ "WHERE movie_id = ?";
    
			PreparedStatement update_movie_ps = dbConn.prepareStatement(query);
			update_movie_ps.setString(1, movie.getMname());
			update_movie_ps.setDate(2, movie.getRelease_date());
			update_movie_ps.setBoolean(3, movie.isRunning());
			update_movie_ps.setBoolean(4, movie.isRunning());
			update_movie_ps.setInt(5, movie.getMid());
			
			rows_updated = update_movie_ps.executeUpdate();
            
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return rows_updated;
    }
    
    public ArrayList<Movie> getAllMovies() {
    	ArrayList<Movie> movies = new ArrayList<Movie>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM MOVIE";
    
			PreparedStatement update_movie_ps = dbConn.prepareStatement(query);
			
			ResultSet rows_selected = update_movie_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Movie movie = new Movie();
				movie.setMid(rows_selected.getInt(1));
				movie.setMname(rows_selected.getString(2));
				movie.setRelease_date(rows_selected.getDate(3));
				movie.setGenre(rows_selected.getString(4)); 
				movie.setRunning(rows_selected.getBoolean(5));
				movies.add(movie);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return movies;
    }
    
    // need to add a method to get movie by (theatre, date, show time)
    public ArrayList<Movie> getMoviesForShow(int theatre_id, Date date, String show_time) {
    	ArrayList<Movie> movies = new ArrayList<Movie>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT MOVIE.* FROM MOVIE, PLAYED_IN "
							+ "WHERE PLAYED_IN.thetre_id=? "
							+ "AND PLAYED_IN.show_time=? "
							+ "AND last_date=? "
							+ "AND PLAYED_IN.movie_id=MOVIE.movie_id";
    
			PreparedStatement get_movies_ps = dbConn.prepareStatement(query);
			get_movies_ps.setInt(1, theatre_id);
			get_movies_ps.setDate(2, date);
			get_movies_ps.setString(3, show_time);
			
			ResultSet rows_selected = get_movies_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Movie movie = new Movie();
				movie.setMid(rows_selected.getInt(1));
				movie.setMname(rows_selected.getString(2));
				movie.setRelease_date(rows_selected.getDate(3));
				movie.setGenre(rows_selected.getString(4)); 
				movie.setRunning(rows_selected.getBoolean(5));
				movies.add(movie);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return movies;
    }

    
}
