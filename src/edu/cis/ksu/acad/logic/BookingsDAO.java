
package edu.cis.ksu.acad.logic;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.model.Bookings;
import edu.cis.ksu.acad.model.PlayedIn;



public class BookingsDAO {

	// or we will disable the already booked seats on seat selection page 
	public int bookTickets(Bookings booking) {
    	int seats_booked = 0;
        try {
        	// creating database connection, connection object in dbConn
        	Database db = new Database();
            Connection dbConn = db.openConnection();
            
            // check if any of the seat is already booked
            String check_seats_query = "SELECT s.seat_number FROM BOOKINGS b, SEATS_BOOKED s "
    				+ "WHERE b.booking_id=s.booking_id "
            		+ "AND movie_id=? AND theatre_id=? AND show_time=? AND date=?";
            PreparedStatement check_seats_ps = dbConn.prepareStatement(check_seats_query);
            
            check_seats_ps.setInt(1, booking.getMovie_id());
            check_seats_ps.setInt(2, booking.getTheatre_id());
            check_seats_ps.setString(3, booking.getShow_time());
            check_seats_ps.setDate(4,  booking.getDate());
            ResultSet seats = check_seats_ps.executeQuery();
            
            while (seats.next()) {
            	String seats_to_book = booking.getSeat_numbers();
            	String seat_already_booked = seats.getString(1);
            	if ( seats_to_book.contains(seat_already_booked) ) {
            		System.out.println("One of the seats you are trying to book is already booked, please select available seats only");
            		return 0;
            	}
            }
            
            // insert into bookings table
            String insert_booking_query = "INSERT INTO BOOKINGS (username, movie_id, theatre_id, show_time, date)"
            				+ "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement book_ticket_ps = dbConn.prepareStatement(insert_booking_query);
            book_ticket_ps.setString(1, booking.getUsername());
            book_ticket_ps.setInt(2, booking.getMovie_id());
            book_ticket_ps.setInt(3, booking.getTheatre_id());
            book_ticket_ps.setString(4, booking.getShow_time());
            book_ticket_ps.setDate(5, booking.getDate());
            
            int booking_done = book_ticket_ps.executeUpdate();
            
            // getting booking ID for the above insert
            String get_booking_id_query = "SELECT MAX(booking_id) FROM BOOKINGS";
            PreparedStatement get_booking_id_ps = dbConn.prepareStatement(get_booking_id_query);
            ResultSet created_booking_id = get_booking_id_ps.executeQuery();
            created_booking_id.next();
            int current_booking_id = created_booking_id.getInt(1);
            
            // insert into seats table
            String[] seats_to_book = booking.getSeat_numbers().split(",");
            
            for (int i=0; i<seats_to_book.length; i++){
            	String insert_seats_query = "INSERT INTO SEATS_BOOKED "
        				+ "VALUES (?, ?)";
                PreparedStatement insert_seats_ps = dbConn.prepareStatement(insert_seats_query);
                insert_seats_ps.setInt(1, current_booking_id);
                insert_seats_ps.setString(2, seats_to_book[i]);
                seats_booked = insert_seats_ps.executeUpdate() + 1;	
            }
            
            dbConn.close();
            	
        }  catch (Exception ex) {
            	ex.printStackTrace();
            	System.out.println("There is some error in booking");
           } 

    	return seats_booked;

    }
	
	public int cancelBooking(int booking_id) {
		int rows_deleted = 0;
		try {
        	// creating database connection, connection object in dbConn
			Database db = new Database();
            Connection dbConn = db.openConnection();
            
            String cancel_booking_query = "DELETE FROM BOOKINGS "
            				+ "WHERE booking_id = ?";
            PreparedStatement cancel_ticket_ps = dbConn.prepareStatement(cancel_booking_query);
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
		Database db = new Database();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT * FROM BOOKINGS "
							+ "WHERE username = ?";
    
			PreparedStatement get_all_user_movie_ps = dbConn.prepareStatement(query);
			get_all_user_movie_ps.setString(1, username);
			
			ResultSet rows_selected = get_all_user_movie_ps.executeQuery();
			
			while ( rows_selected.next() ) {
				Bookings booking = new Bookings();
				int booking_id = rows_selected.getInt(1);
				int movie_id = rows_selected.getInt(3);
				int theatre_id = rows_selected.getInt(4);
				String show_time = rows_selected.getString(5);
				Date show_date = rows_selected.getDate(6);
				System.out.println("in dao: "+show_date);
				String seats_booked = getBookedSeatsByUser(booking_id, username, movie_id, theatre_id, show_date, show_time);
				booking.setBooking_id(booking_id);
				booking.setUsername(username);
				booking.setMovie_id(movie_id);
				booking.setTheatre_id(theatre_id); 
				booking.setShow_time(show_time);
				booking.setSeat_numbers(seats_booked);
				booking.setDate(show_date);
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
		Database db = new Database();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT s.seat_number FROM BOOKINGS b, SEATS_BOOKED s "
							+ "WHERE movie_id=? AND theatre_id=? AND date=? AND show_time=? AND b.booking_id=s.booking_id";
    
			
			System.out.println(query);
			PreparedStatement get_booked_seats_ps = dbConn.prepareStatement(query);
			get_booked_seats_ps.setInt(1, movie_id);
			get_booked_seats_ps.setInt(2, theatre_id);
			get_booked_seats_ps.setDate(3, date);
			get_booked_seats_ps.setString(4, show_time);
						
			ResultSet rows_selected = get_booked_seats_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				booked_seats = booked_seats+","+rows_selected.getString(1);
			} 
			
			if(!booked_seats.isEmpty()){
				booked_seats = booked_seats.substring(1);
			}
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("asdasdasdasd"+booked_seats);
       
        
        return booked_seats;
    
	}
	
	// to get seats booked by a user
	private String getBookedSeatsByUser(int booking_id, String username, int movie_id, int theatre_id, Date date, String show_time) {
		String booked_seats_by_user = "";
		Database db = new Database();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT s.seat_number FROM BOOKINGS b, SEATS_BOOKED s "
							+ "WHERE b.movie_id=? AND b.theatre_id=? AND b.date=? AND b.show_time=? AND b.username=? AND b.booking_id=s.booking_id AND b.booking_id=?";
    
			PreparedStatement get_booked_seats_ps = dbConn.prepareStatement(query);
			get_booked_seats_ps.setInt(1, movie_id);
			get_booked_seats_ps.setInt(2, theatre_id);
			get_booked_seats_ps.setDate(3, date);
			get_booked_seats_ps.setString(4, show_time);
			get_booked_seats_ps.setString(5, username);
			get_booked_seats_ps.setInt(6, booking_id);
									
			ResultSet rows_selected = get_booked_seats_ps.executeQuery();
            
			while ( rows_selected.next() ) {
				booked_seats_by_user = booked_seats_by_user+","+rows_selected.getString(1);
			} 
			
			if (!booked_seats_by_user.isEmpty()) {
				booked_seats_by_user = booked_seats_by_user.substring(1);
			}
			
            dbConn.close();
            
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return booked_seats_by_user;
    
	}
	
	public ArrayList<PlayedIn> getMovieShowsByTheatreAndDate(int theatre_id, Date date) {
		ArrayList<PlayedIn> shows = new ArrayList<PlayedIn>();
		Database db = new Database();
        try {
			Connection dbConn = db.openConnection();
			
			String query = "SELECT PLAYED_IN.*, MOVIE.movie_name FROM PLAYED_IN, MOVIE "
							+ "WHERE PLAYED_IN.theatre_id = ? AND PLAYED_IN.last_date = ? "
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
	
	public static void main(String args[]) {
		BookingsDAO bd = new BookingsDAO();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date;
		try {
			date = sdf1.parse("05/15/2015");
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
		
		Bookings booking = new Bookings();
		booking.setDate(sqlStartDate);
		booking.setMovie_id(2);
		booking.setTheatre_id(1);
		booking.setUsername("a");
		booking.setShow_time("2100");
		booking.setSeat_numbers("C1,C2,C3");
		
		int booked = bd.bookTickets(booking);
		
		
		System.out.println(booked);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

