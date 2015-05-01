package edu.ksu.cis.acad.model;

public class Bookings {
	private String booking_id;
	private String username;
	private String movie_id;
	private String theatre_id;
	private String show_time;
	private String seat_numbers;
	
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	
	public String getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(String theatre_id) {
		this.theatre_id = theatre_id;
	}
	
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	
	public String getSeat_numbers() {
		return seat_numbers;
	}
	public void setSeat_numbers(String seat_numbers) {
		this.seat_numbers = seat_numbers;
	}
	
}
