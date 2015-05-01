package edu.ksu.cis.acad.control;

import edu.ksu.cis.acad.dbutil.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        int uid = (Integer)session.getAttribute("id");
        
        try {
            Database db= new Database();
            Database db1= new Database();
            ResultSet rs = db.result("select * from bookticket where uid="+uid);
            out.print("<h1>MY BOOKINGS</h1><table id='mybookings'><tr>");
            out.print("<th>Movie Name</th><th>Theatre Name</th><th>booking Date</th><th>show</th><th>seats</th><th>details</th><th>edit</th><th>cancel</th>");
            while(rs.next()){
                out.print("<tr>");
                out.print("<td>"+db1.getMovie(rs.getInt("mid"))+"</td>");
                out.print("<td>"+db1.getTheatre(rs.getInt("tid"))+"</td>");
                out.print("<td>"+rs.getDate("booking_date")+"</td>");
                out.print("<td>"+rs.getInt("showtime")+"</td>");
                out.print("<td>"+rs.getString("seats").substring(0,rs.getString("seats").lastIndexOf(","))+"</td>");
                out.print("<td><a class='details' href='getdetails?id="+rs.getInt("id")+"'>Get Details</a></td>");
                out.print("<td><a class='edit'href='editbooking.jsp?id="+rs.getInt("id")+"'>Edit booking</a></td>");
                out.print("<td><a class='cancel' href='cancelbooking?id="+rs.getInt("id")+"'>cancel booking</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex1) {
            out.print(ex1.getMessage());
        }
    }


}
