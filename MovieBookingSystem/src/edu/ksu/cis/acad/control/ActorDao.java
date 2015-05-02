package edu.ksu.cis.acad.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Actor;

public class ActorDao {

	public ArrayList<Actor> getActors(String movie_id) {
		ArrayList<Actor> actors = new ArrayList<Actor>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM ACTOR	WHERE movie_id=?";
    
			PreparedStatement get_actor_ps = dbConn.prepareStatement(query);
			
			ResultSet rows_selected = get_actor_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Actor actor = new Actor();
				actor.setActor_id(rows_selected.getString(1));
				actor.setActor_name(rows_selected.getString(2));
				actor.setMovie_id(rows_selected.getString(3));
				actors.add(actor);
			}
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return actors;
	}
}
