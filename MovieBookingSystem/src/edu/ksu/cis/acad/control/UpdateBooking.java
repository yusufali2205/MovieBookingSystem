package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateBooking extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int id = Integer.parseInt(request.getParameter("id"));
        int tid = Integer.parseInt(request.getParameter("tid"));
        String booking_date = request.getParameter("date");
        int mid = Integer.parseInt(request.getParameter("mid"));
        int showtime = Integer.parseInt(request.getParameter("showtime"));
        String seats = request.getParameter("seats");
        try {
            Database db = new Database();
            Map map = new HashMap();
            
            map.put("tid", tid);
            map.put("mid", mid);
            map.put("booking_date", booking_date);
            map.put("showtime", showtime);
            map.put("seats", seats);
            db.update("bookticket", map, id);
            out.print("successfully updated");
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


}
