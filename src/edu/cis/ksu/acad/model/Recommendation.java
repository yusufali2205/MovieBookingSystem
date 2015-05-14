package edu.cis.ksu.acad.model;

public class Recommendation {
	private String username;
	private int movie_id;
	private String genre;
	private String movie_name;
	private long average_rating;
	
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

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public long getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(long average_rating) {
		this.average_rating = average_rating;
	}


	
}
