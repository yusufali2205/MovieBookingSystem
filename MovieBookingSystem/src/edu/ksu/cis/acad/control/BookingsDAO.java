package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Bookings;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingsDAO extends HttpServlet {

    public int BookTickets(Bookings booking) {
    	int rows_updated = 0;
        try {
        	// creating database connection, connection object in dbConn
        	DatabaseConnect db = new DatabaseConnect();
            Connection dbConn = db.openConnection();
            
            String query = "INSERT INTO BOOKINGS "
            				+	"VALUES (?, ?, ?, ?, ?, ?)";
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

}
