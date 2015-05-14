package edu.cis.ksu.acad.model;

public class Theatre {
	private int theatre_id;
	private String theatre_name;
	private String show_time;
	
	public int getTheatre_id() {
		return theatre_id;
	}
	// set theatre id should not be required since it is auto incremented in the database
	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}
	
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	
	
}
