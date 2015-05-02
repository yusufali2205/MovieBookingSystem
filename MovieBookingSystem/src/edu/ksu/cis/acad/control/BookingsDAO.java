package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Bookings;
import edu.ksu.cis.acad.model.Movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingsDAO extends HttpServlet {

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
	public String getBookedSeats(String movie_id, String theatre_id, Date date, String show_time) {
		String booked_seats = "";
		DatabaseConnect db = new DatabaseConnect();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT seat_numbers FROM BOOKINGS "
							+ "WHERE movie_id=? AND theatre_id=? AND date=? AND show_time=?";
    
			PreparedStatement get_booked_seats_ps = dbConn.prepareStatement(query);
			
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
}
