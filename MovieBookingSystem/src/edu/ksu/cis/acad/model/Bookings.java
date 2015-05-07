package edu.ksu.cis.acad.model;

import java.sql.Date;

public class Bookings {
	private int booking_id;
	private String username;
	private int movie_id;
	private int theatre_id;
	private String show_time;
	private String seat_numbers;
	private Date date;
	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	public int getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(int theatre_id) {
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
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
