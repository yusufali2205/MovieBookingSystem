package edu.ksu.cis.acad.servlet;

import edu.ksu.cis.acad.control.BookingsDAO;
import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Bookings;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyBookings extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("id");
        
        System.out.println("*********"+uid);
       
        try {
          
          
            
            BookingsDAO dao = new BookingsDAO();
            ArrayList<Bookings> ars = dao.showUserBookings(uid);
            
            
            
            out.print("<h1>MY BOOKINGS</h1><table id='mybookings'><tr>");
            out.print("<th>Movie Name</th><th>Theatre Name</th><th>Booking Date</th><th>Show</th><th>Seats Booked</th><th>Cancel</th><th>Review</th>");
            for(Bookings db1 : ars){
                out.print("<tr>");
                out.print("<td>"+db1.getMovie_id()+"</td>");
                out.print("<td>"+db1.getTheatre_id()+"</td>");
                out.print("<td>"+db1.getDate()+"</td>");
                out.print("<td>"+db1.getShow_time()+"</td>");
                out.print("<td>"+db1.getSeat_numbers()+"</td>");
                out.print("<td><a class='edit'href='editbooking.jsp?id="+db1.getUsername()+"'>Cancel booking</a></td>");
                out.print("<td><a class='cancel' href='cancelbooking?id="+db1.getUsername()+"'>Review</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");
        } catch (Exception ex) {
            out.print(ex.getMessage());
        } 
    }


}
