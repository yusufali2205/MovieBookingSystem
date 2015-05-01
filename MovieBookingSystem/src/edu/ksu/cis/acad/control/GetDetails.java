package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Integer id = request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):1;

        try {
            Database db = new Database();
            Database db1 = new Database();
            ResultSet rs = db.result("select * from bookticket where id=" + id);
            rs.next();
            out.print("<div id='moviedetails'><div class='poster'>");
            out.print("<img src='img/"+db1.getPoster(rs.getInt("mid"))+"' width='250px'/></div><div class='details'><table>");
            out.print("<tr><td class='heading'>Movie Name</td>"+"<td>"+db1.getMovie(rs.getInt("mid"))+"</td></tr>");
            out.print("<tr><td class='heading'>Theatre Name</td>"+"<td>"+db1.getTheatre(rs.getInt("tid"))+"</td></tr>");
            out.print("<tr><td class='heading'>Release Date</td>"+"<td>"+db1.getReleaseDate(rs.getInt("mid"))+"</td></tr>");      
            out.print("<tr><td class='heading'>Booking Date</td>"+"<td>"+rs.getDate("booking_date")+"</td></tr>"); 
            out.print("<tr><td class='heading'>show time</td>"+"<td>"+rs.getInt("showtime")+"</td></tr>"); 
            out.print("<tr><td class='heading'>Seats</td>"+"<td>"+rs.getString("seats").substring(0, rs.getString("seats").lastIndexOf(","))+"</td></tr>"); 
            out.print("</table></div>");

        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            
        }
    }

}
