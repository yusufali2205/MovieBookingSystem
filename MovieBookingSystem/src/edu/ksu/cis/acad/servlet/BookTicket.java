package edu.ksu.cis.acad.servlet;

import edu.ksu.cis.acad.control.BookingsDAO;
import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Bookings;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookTicket extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    	response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
    		
			session.setAttribute("id", session.getAttribute("id"));

        String uid = (String) session.getAttribute("id");
   
        String seats = (String)session.getAttribute("seats");
        System.out.println("seats"+ (String)session.getAttribute("seats"));
        
        String seats_x = request.getParameter("sseats");
        System.out.println("seeeee: "+ seats_x);
        
        String splits = request.getParameter("mid");
        String datestr = request.getParameter("date");
        
        
        
  
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date;
		try {
			date = sdf1.parse(datestr);
		
		    int theatre =Integer.parseInt((String)session.getAttribute("tid"));
	        int mid = (Integer) session.getAttribute("mid");
	        String shtime = (String)session.getAttribute("showtime");
		
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		
		Bookings bkk = new Bookings();
		bkk.setMovie_id(mid);
		bkk.setSeat_numbers(seats);
		bkk.setDate(sqlStartDate);
		bkk.setShow_time(shtime);
		bkk.setTheatre_id(theatre);
		bkk.setUsername(uid);
		
		BookingsDAO bdoa = new BookingsDAO();
		//bdoa.bookTickets(bkk);
		
		request.getRequestDispatcher("mybookings.jsp").forward(request, response);
		out.print("successfully booked");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}
