package edu.ksu.cis.acad.servlet;


import edu.ksu.cis.acad.dbutil.DatabaseConnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MovieList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String datestr = request.getParameter("date");
        String theatre = request.getParameter("theatre");
        
        HttpSession session = request.getSession();
        session.setAttribute("tid", theatre);
        
        try {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date;
		
			date = sdf1.parse(datestr);
		
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
        
        DatabaseConnect db;
       
            db = new DatabaseConnect();
        	String query = "SELECT PLAYED_IN.*, MOVIE.movie_name FROM PLAYED_IN, MOVIE "
					+ "WHERE PLAYED_IN.theatre_id = "+theatre+" AND PLAYED_IN.last_date = "+"\""+sqlStartDate+"\""
					+ " AND MOVIE.movie_id = PLAYED_IN.movie_id";
        	
            ResultSet rs = db.result(query);
            out.print("<option value='0'>--select--</option>");
            while (rs.next()) {
                out.print("<option value='" + rs.getInt(2) + rs.getInt(3) + "'>");
                out.print(rs.getString(5) + " - " + rs.getString(3));
                out.print("</option>");
            }

        } catch (SQLException ex) {
        	ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();

        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
