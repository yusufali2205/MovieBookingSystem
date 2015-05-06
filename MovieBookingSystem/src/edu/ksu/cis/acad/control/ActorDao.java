package edu.ksu.cis.acad.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Actor;

public class ActorDao {

	public ArrayList<Actor> getActors(int movie_id) {
		ArrayList<Actor> actors = new ArrayList<Actor>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT ACTOR.* FROM ACTOR_MOVIES, ACTOR "
							+ "WHERE ACTOR_MOVIES.movie_id=? "
							+ "AND ACTOR_MOVIES.actor_id=ACTOR.actor_id";
    
			PreparedStatement get_actor_ps = dbConn.prepareStatement(query);
			get_actor_ps.setInt(1, movie_id);
			
			ResultSet rows_selected = get_actor_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Actor actor = new Actor();
				actor.setActor_id(rows_selected.getInt(1));
				actor.setActor_name(rows_selected.getString(2));
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
