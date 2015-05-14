package edu.cis.ksu.acad.servlets;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.logic.BookingsDAO;
import edu.cis.ksu.acad.logic.MovieDAO;
import edu.cis.ksu.acad.logic.RecommendationDAO;
import edu.cis.ksu.acad.logic.TheatreDAO;
import edu.cis.ksu.acad.model.Bookings;
import edu.cis.ksu.acad.model.Recommendation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Recommend extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String uid = (String)session.getAttribute("id");
        
        System.out.println("INSIDE RECOMMEND");
        
        RecommendationDAO dao = new RecommendationDAO();
		 ArrayList<Recommendation> ars = dao.generateRecommendationForUser(uid);
		 
		 out.print("<h1>RECOMMENDATION</h1><table id='mybookings'><tr>");
		 out.print("<th>Movie ID</th><th>Movie Name</th><th>AVERAGE RATING</th><th>GENRE</th>");
		 for(Recommendation db1 : ars){
		     out.print("<tr>");
		     out.print("<td>"+db1.getMovie_id()+"</td>");
		     out.print("<td>"+db1.getMovie_name()+"</td>");
		     out.print("<td>"+db1.getAverage_rating()+"</td>");
		     out.print("<td>"+db1.getGenre()+"</td>");
		     out.print("</tr>");
		 }
		 out.print("</table>");
    }


}
