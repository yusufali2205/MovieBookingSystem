package edu.ksu.cis.acad.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ksu.cis.acad.control.BookingsDAO;
import edu.ksu.cis.acad.dbutil.DatabaseConnect;

public class SeatStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
        //int uid = (Integer) session.getAttribute("id");
        int tid = Integer.parseInt(request.getParameter("tid"));
        String splits = request.getParameter("mid");
        String datestr = request.getParameter("date");
        
        
  
        int mid = Integer.parseInt(splits.substring(0,splits.length()-4));
        String showtime = splits.substring(splits.length()-4);
        session.setAttribute("mid", mid);
        session.setAttribute("showtime", showtime);
        
        System.out.println(mid +"      "+ showtime);

        int cols = 20;
        int rows = 5;
        String[] alph = {"A", "B", "C", "D", "E"};

      
        	BookingsDAO bd = new BookingsDAO();
        	SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
    		java.util.Date date = sdf1.parse(datestr);
    		
    		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
    		
            String booked = bd.getBookedSeats(tid, mid, sqlStartDate,showtime);
          
            String[] arr = booked.split(",");
           
            int len = arr.length;
            out.print("<table>");
            for (int i = 1; i <= rows; i++) {
                out.print("<tr><td>"+alph[i-1]+"</td><td>");                
                for (int j = 1; j <= cols; j++) {
                    String seat = alph[i - 1] + j;
                    boolean flag = true;
                    for (int k = 0; k < len; k++) {
                        if (seat.equals(arr[k])) {
                            out.print("<div id="+seat+" class='booked'></div>");
                            flag = false;
                        }
                    }
                    if(flag)
                        out.print("<div id="+seat+" class='available'></div>");
                }
                out.println("</td></tr>");
            }
        } catch (Exception se) {
            out.print("something went wrong");
        }
        

        out.print("</table>");
    }
}
