package edu.ksu.cis.acad.model;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Movie {
	private String movie_id;
	private String movie_name;
	private java.sql.Date release_date;
	private String genre;
	private boolean running; // true if movie is currently being played
	
	public String getMid() {
		return movie_id;
	}
	public void setMid(String movie_id) {
		this.movie_id = movie_id;
	}
	
	public String getMname() {
		return movie_name;
	}
	public void setMname(String movie_name) {
		this.movie_name = movie_name;
	}
	
	public java.sql.Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(java.sql.Date release_date) {
		this.release_date = release_date;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	

}
