package edu.ksu.cis.acad.control;
import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Theatre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheatreDAO {

    public int addTHEATRE(Theatre theatre) {
        int rows_updated = 0;
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "INSERT INTO THEATRE "
            				+	"VALUES (?, ?, ?)";
            PreparedStatement add_theatre_ps = dbConn.prepareStatement(query);
            add_theatre_ps.setString(1, theatre.getTheatre_id());
            add_theatre_ps.setString(2, theatre.getTheatre_name());
            add_theatre_ps.setString(3, theatre.getShow_time());
            
            rows_updated = add_theatre_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return rows_updated;
    }
    
    public int deleteTheatre(String theatre_id) {
    	int rows_deleted = 0;
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "DELETE FROM THEATRE "
    				+ "WHERE theatre_id = ?";
    
			PreparedStatement delete_theatre_ps = dbConn.prepareStatement(query);
			delete_theatre_ps.setString(1, theatre_id);
			
			rows_deleted = delete_theatre_ps.executeUpdate();
            
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return rows_deleted;
    }

    public int editTheatres(Theatre theatre) {
    	int rows_edited = 0;
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "UPDATE THEATRE set theatre_name = ?, show_time = ?  "
    				+ "WHERE theatre_id = ?";
    
			PreparedStatement edit_theatre_ps = dbConn.prepareStatement(query);
			edit_theatre_ps.setString(3, theatre.getTheatre_id());
			edit_theatre_ps.setString(2,theatre.getShow_time());
			edit_theatre_ps.setString(1,theatre.getTheatre_name());
			rows_edited = edit_theatre_ps.executeUpdate();
            
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return rows_edited;
    }

    
    public ArrayList<Theatre> getAllTheatres() {
    	ArrayList<Theatre> theatres = new ArrayList<Theatre>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM THEATRE";
    
			PreparedStatement update_theatre_ps = dbConn.prepareStatement(query);
			
			ResultSet rows_selected = update_theatre_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Theatre theatre = new Theatre();
				theatre.setTheatre_id(rows_selected.getString(1));
				theatre.setTheatre_name(rows_selected.getString(2));
				theatre.setShow_time(rows_selected.getString(3));
				theatres.add(theatre);
			} 
			
            dbConn.close();
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return theatres;
    }



}



