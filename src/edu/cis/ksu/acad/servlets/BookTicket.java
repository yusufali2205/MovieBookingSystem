package edu.cis.ksu.acad.servlets;

import edu.cis.ksu.acad.dbutil.Database;


import edu.cis.ksu.acad.logic.BookingsDAO;
import edu.cis.ksu.acad.model.Bookings;

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

        String uid = (String) session.getAttribute("id");
        int theatre = Integer.parseInt(request.getParameter("tid"));
        String date = request.getParameter("date");
        String splits = request.getParameter("mid");
      //  int showtime = Integer.parseInt(request.getParameter("showtime"));
        String seats = request.getParameter("seats");
        int mid = Integer.parseInt(splits.substring(0,splits.length()-4));
        String showtime = splits.substring(splits.length()-4);

        try {
        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
     		java.util.Date date1 = sdf1.parse(date);
     		java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime());
     		Bookings bkk = new Bookings();
    		bkk.setMovie_id(mid);
    		bkk.setSeat_numbers(seats);
    		bkk.setDate(sqlStartDate);
    		bkk.setShow_time(showtime);
    		bkk.setTheatre_id(theatre);
    		bkk.setUsername(uid);
    		
    		BookingsDAO bdoa = new BookingsDAO();
    		bdoa.bookTickets(bkk);
        		
            out.print("successfully booked");
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
