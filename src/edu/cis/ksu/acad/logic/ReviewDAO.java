package edu.cis.ksu.acad.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.model.Review;


public class ReviewDAO {

	public int addReview(Review review) {
		int review_added = 0;
        try {
        	// creating database connection, connection object in dbConn
        	Database db = new Database();
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
        try {
        	Database db = new Database();
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
	

	public ArrayList<Review> getReviewsByMovie(int movie_id) {
		ArrayList<Review> reviews = new ArrayList<Review>();
        try {
        	Database db = new Database();
			Connection dbConn = db.openConnection();
			
			String query = "SELECT REVIEWS.*, MOVIE.movie_name FROM REVIEWS, MOVIE "
							+ "WHERE movie_id=? AND REVIEWS.movie_id=MOVIE.movie_id";
    
			PreparedStatement get_reviews_by_movie_ps = dbConn.prepareStatement(query);
			get_reviews_by_movie_ps.setInt(1, movie_id);
			
			ResultSet rows_selected = get_reviews_by_movie_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Review review = new Review();
				review.setMovie_id(rows_selected.getInt(1));
				review.setUsername(rows_selected.getString(2));
				review.setRating(rows_selected.getInt(3));
				review.setComment(rows_selected.getString(4)); 
				review.setMovie_name(rows_selected.getString(5));
				reviews.add(review);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return reviews;
    
	}
	
	public long averageRatingByMovie(int movie_id) {
		long average_rating = 0;
		Database db = new Database();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT AVG(rating) FROM REVIEWS "
							+ "WHERE movie_id=?";
    
			PreparedStatement get_booked_seats_ps = dbConn.prepareStatement(query);
			get_booked_seats_ps.setInt(1, movie_id);
			
			ResultSet rows_selected = get_booked_seats_ps.executeQuery();
            rows_selected.next();
            average_rating = rows_selected.getLong(1);
					
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("This movie has no reviews");
			return average_rating;
		}
        
        return average_rating;
	}
	
}
