package edu.cis.ksu.acad.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.model.PlayedIn;


public class PlayedInDAO {
	
	public int addMovieToTheatre(PlayedIn show) {
		int show_added = 0;		
        try {
        	// creating database connection, connection object in dbConn
        	Database db = new Database();
            Connection dbConn = db.openConnection();
            
            String insert_show_query = "INSERT INTO PLAYED_IN "
            				+	"VALUES (?, ?, ?, ?)";
            PreparedStatement add_show_ps = dbConn.prepareStatement(insert_show_query);
            add_show_ps.setInt(1, show.getTheatre_id());
            add_show_ps.setInt(2, show.getMovie_id());
            add_show_ps.setString(3, show.getShow_time());
            add_show_ps.setDate(4, show.getDate());
            
            show_added = add_show_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
            	System.out.println("This showtime for selected date and theatre is occupied");
            	return show_added;
           } 

    	return show_added;
		
	}
}
