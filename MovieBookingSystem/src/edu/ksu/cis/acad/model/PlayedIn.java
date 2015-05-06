package edu.ksu.cis.acad.model;

import java.sql.Date;

public class PlayedIn {
	private int theatre_id;
	private String theatre_name;
	private int movie_id;
	private String movie_name;
	private String show_time;
	private Date last_date;
	
	public int getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}
	
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	
	public Date getDate() {
		return last_date;
	}
	public void setDate(Date last_date) {
		this.last_date = last_date;
	}
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	
	
}
