package edu.ksu.cis.acad.control;

import edu.ksu.cis.acad.model.User;

public class LoginDAO {

	//TODO: validate the credentials and return object user.
	
	public User getUser(User user){
		
		User user1 = new User();
		user1.setType("user");
		
		return user1;
	}
	
	
}
