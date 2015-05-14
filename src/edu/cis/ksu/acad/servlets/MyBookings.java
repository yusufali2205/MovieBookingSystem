package edu.cis.ksu.acad.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cis.ksu.acad.logic.BookingsDAO;
import edu.cis.ksu.acad.logic.MovieDAO;
import edu.cis.ksu.acad.logic.TheatreDAO;
import edu.cis.ksu.acad.model.Bookings;

public class MyBookings extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String uid = (String)session.getAttribute("id");
        
        BookingsDAO dao = new BookingsDAO();
		 ArrayList<Bookings> ars = dao.showUserBookings(uid);
		 
		 out.print("<h1>MY BOOKINGS</h1><table id='mybookings'><tr>");
		 out.print("<th>Movie Name</th><th>Theatre Name</th><th>Booking Date</th><th>Show</th><th>Seats Booked</th><th>Cancel</th><th>Review</th>");
		 for(Bookings db1 : ars){
			 MovieDAO movie = new MovieDAO();
			 TheatreDAO theatre = new TheatreDAO();
			 String theatre_name = theatre.getTheatreNameById(db1.getTheatre_id());
			 String movie_name = movie.getMovieNameById(db1.getMovie_id());
		     out.print("<tr>");
		     out.print("<td>"+movie_name+"</td>");
		     out.print("<td>"+theatre_name+"</td>");

		     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		     String text = df.format(db1.getDate());
		     out.print("<td>"+text+"</td>");
		     out.print("<td>"+db1.getShow_time()+"</td>");
		     out.print("<td>"+db1.getSeat_numbers()+"</td>");
		     out.print("<td><a class='cancel' href='cancelbooking?id="+db1.getBooking_id()+"'>Cancel booking</a></td>");
		     out.print("<td><a class='cancel' href='review?id="+db1.getUsername()+"'>Review</a></td>");
		     out.print("</tr>");
		 }
		 out.print("</table>");
    }
}
