package edu.ksu.cis.acad.model;

public class PlayedIn {
	private String theatre_id;
	private String movie_id;
	private String show_time;
	private String last_date;
	
	public String getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(String theatre_id) {
		this.theatre_id = theatre_id;
	}
	
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	
	public String getDate() {
		return last_date;
	}
	public void setDate(String last_date) {
		this.last_date = last_date;
	}
	
	
}
