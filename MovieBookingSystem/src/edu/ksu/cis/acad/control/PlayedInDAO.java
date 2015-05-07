package edu.ksu.cis.acad.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.PlayedIn;

public class PlayedInDAO {
	
	public int addMovieToTheatre(PlayedIn show) {
		int show_added = 0;		
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
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
