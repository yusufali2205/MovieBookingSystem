package edu.cis.ksu.acad.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.model.Recommendation;
import edu.cis.ksu.acad.model.Theatre;

public class RecommendationDAO {
	public ArrayList<Recommendation> generateRecommendationForUser(String username) {
		ArrayList<Recommendation> recommended_movies = new ArrayList<Recommendation>();
    	Database db = new Database();
        try {
			Connection dbConn = db.openConnection();
			
			// get max genre seen by the user
			String get_user_prefered_genre = "SELECT max(count), genre FROM "
							+ "( "
							+ "		SELECT genre, count(genre) as count FROM MOVIE "
							+ "		WHERE movie_id IN "
							+ "		(SELECT movie_id FROM BOOKINGS WHERE username = ? "
							+ "				) "
							+ "				GROUP BY genre "
							+ "		) as X";
			
			PreparedStatement get_genre = dbConn.prepareStatement(get_user_prefered_genre);
			get_genre.setString(1, username);
			
			ResultSet genre_result = get_genre.executeQuery();
            genre_result.next();
			
            String genre = genre_result.getString(2);
            
			
			// get movies with max rating for a genre
			String get_recommended_movie = "SELECT m.movie_id, AVG(r.rating) as avg, m.genre, r.username, m.movie_name "
							+ "FROM REVIEWS r, MOVIE m WHERE m.movie_id=r.movie_id AND m.genre= ? GROUP BY m.movie_id "
							+ "order by avg desc LIMIT 2";
			
			PreparedStatement get_rec_ps = dbConn.prepareStatement(get_recommended_movie);
			get_rec_ps.setString(1, genre);
			
			ResultSet reco_result = get_rec_ps.executeQuery();
            
			while (reco_result.next()) {
				Recommendation reco_movie = new Recommendation();
				reco_movie.setMovie_id(reco_result.getInt(1));
				reco_movie.setAverage_rating(reco_result.getLong(2));
				reco_movie.setGenre(reco_result.getString(3));
				reco_movie.setUsername(reco_result.getString(4));
				reco_movie.setMovie_name(reco_result.getString(5));
				recommended_movies.add(reco_movie);
			}
            
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return recommended_movies;
    
	}
}
