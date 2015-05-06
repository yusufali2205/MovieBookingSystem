package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Bookings;
import edu.ksu.cis.acad.model.Movie;
import edu.ksu.cis.acad.model.PlayedIn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingsDAO {

    // might need to put a validation to check if the seats selected are available or not
	// or we will disable the already booked seats on seat selection page 
	public int bookTickets(Bookings booking) {
    	int rows_updated = 0;
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "INSERT INTO BOOKINGS "
            				+ "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement book_ticket_ps = dbConn.prepareStatement(query);
            book_ticket_ps.setString(1, "");
            book_ticket_ps.setString(2, booking.getUsername());
            book_ticket_ps.setString(3, booking.getMovie_id());
            book_ticket_ps.setString(4, booking.getTheatre_id());
            book_ticket_ps.setString(5, booking.getSeat_numbers());
            
            rows_updated = book_ticket_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return rows_updated;

    }
	
	public int cancelBooking(int booking_id) {
		int rows_deleted = 0;
		try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "DELETE FROM BOOKINGS "
            				+ "WHERE booking_id = ?";
            PreparedStatement cancel_ticket_ps = dbConn.prepareStatement(query);
            cancel_ticket_ps.setInt(1, booking_id);
            
            rows_deleted = cancel_ticket_ps.executeUpdate();
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
           } 

    	return rows_deleted;
		
	}
	
	// will return an arraylist of bookings for a given username
	public ArrayList<Bookings> showUserBookings(String username) {
		ArrayList<Bookings> bookings = new ArrayList<Bookings>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM BOOKINGS "
							+ "WHERE username = ?";
    
			PreparedStatement get_all_user_movie_ps = dbConn.prepareStatement(query);
			get_all_user_movie_ps.setString(1, username);
			
			ResultSet rows_selected = get_all_user_movie_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				Bookings booking = new Bookings();
				booking.setBooking_id(rows_selected.getInt(1));
				booking.setUsername(rows_selected.getString(2));
				booking.setMovie_id(rows_selected.getString(3));
				booking.setTheatre_id(rows_selected.getString(4)); 
				booking.setShow_time(rows_selected.getString(5));
				booking.setSeat_numbers(rows_selected.getString(6));
				bookings.add(booking);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return bookings;
    
	}
	
	// this will return all the booked seats of a particular show
	// Ashwin can use this method to disable already booked seats on the booking page
	public String getBookedSeats(int movie_id, int theatre_id, Date date, String show_time) {
		String booked_seats = "";
		DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT seat_numbers FROM BOOKINGS "
							+ "WHERE movie_id=? AND theatre_id=? AND date=? AND show_time=?";
    
			PreparedStatement get_booked_seats_ps = dbConn.prepareStatement(query);
			get_booked_seats_ps.setInt(1, movie_id);
			get_booked_seats_ps.setInt(2, theatre_id);
			get_booked_seats_ps.setDate(3, date);
			get_booked_seats_ps.setString(4, show_time);
						
			ResultSet rows_selected = get_booked_seats_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				booked_seats = booked_seats+rows_selected.getString(1);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return booked_seats;
    
	}
	
	public ArrayList<PlayedIn> getMovieShowsByTheatreAndDate(int theatre_id, Date date) {
		ArrayList<PlayedIn> shows = new ArrayList<PlayedIn>();
    	DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT PLAYED_IN.*, MOVIE.movie_name FROM PLAYED_IN, MOVIE "
							+ "WHERE PLAYED_IN.theatre_id = ? AND PLAYED_IN.last_date > ? "
							+ "AND MOVIE.movie_id = PLAYED_IN.movie_id";
    
			PreparedStatement get_shows_by_date = dbConn.prepareStatement(query);
			get_shows_by_date.setInt(1, theatre_id);
			get_shows_by_date.setDate(2, date);
			
			ResultSet rows_selected = get_shows_by_date.executeQuery();
            
			while ( rows_selected.next() ) {
				PlayedIn show = new PlayedIn();
				show.setTheatre_id(rows_selected.getInt(1));
				show.setMovie_id(rows_selected.getInt(2));
				show.setShow_time(rows_selected.getString(3));
				show.setDate(rows_selected.getDate(4));
				show.setMovie_name(rows_selected.getString(5));
				shows.add(show);
			} 
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return shows;
	}
	
	
	
}
