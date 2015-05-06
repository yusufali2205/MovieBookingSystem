package edu.ksu.cis.acad.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Movie;
import edu.ksu.cis.acad.model.Review;

public class ReviewDAO {

	public int addReview(Review review) {
		int review_added = 0;
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "INSERT INTO REVIEWS "
            				+	"VALUES (?, ?, ?, ?)";
            PreparedStatement add_review_ps = dbConn.prepareStatement(query);
            add_review_ps.setInt(1, review.getMovie_id());
            add_review_ps.setString(2, review.getUsername());
            add_review_ps.setInt(3, review.getRating());
            add_review_ps.setString(4, review.getComment());
            
            review_added = add_review_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return review_added;
	}

	// returns arraylist of all the reviews in database
	public ArrayList<Review> getAllReviews() {
		ArrayList<Review> reviews = new ArrayList<Review>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM REVIEWS";
    
			PreparedStatement get_all_reviews_ps = dbConn.prepareStatement(query);
			
			ResultSet rows_selected = get_all_reviews_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Review review = new Review();
				review.setMovie_id(rows_selected.getInt(1));
				review.setUsername(rows_selected.getString(2));
				review.setRating(rows_selected.getInt(3));
				review.setComment(rows_selected.getString(4)); 
				reviews.add(review);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return reviews;
    
	}
	
	// returns arraylist of reviews for a movie
	// we will require a join if we need movie name instead of movie id
	public ArrayList<Review> getReviewsByMovie(int movie_id) {
		ArrayList<Review> reviews = new ArrayList<Review>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM REVIEWS "
							+ "WHERE movie_id=?";
    
			PreparedStatement get_reviews_by_movie_ps = dbConn.prepareStatement(query);
			get_reviews_by_movie_ps.setInt(1, movie_id);
			
			ResultSet rows_selected = get_reviews_by_movie_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Review review = new Review();
				review.setMovie_id(rows_selected.getInt(1));
				review.setUsername(rows_selected.getString(2));
				review.setRating(rows_selected.getInt(3));
				review.setComment(rows_selected.getString(4)); 
				reviews.add(review);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return reviews;
    
	}
	
}
