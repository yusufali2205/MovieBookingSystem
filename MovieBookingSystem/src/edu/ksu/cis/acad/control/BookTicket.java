package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

        int uid = (Integer) session.getAttribute("id");
        int theatre = Integer.parseInt(request.getParameter("tid"));
        String date = request.getParameter("date");
        int movie = Integer.parseInt(request.getParameter("mid"));
        int showtime = Integer.parseInt(request.getParameter("showtime"));
        String seats = request.getParameter("seats");

        try {
            Database db = new Database();
            Map map = new HashMap();

            map.put("uid", uid);
            map.put("tid", theatre);
            map.put("mid", movie);
            map.put("booking_date", date);
            map.put("showtime", showtime);
            map.put("seats", seats);
            db.insert("bookticket", map);
            out.print("successfully booked");
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException cnf) {
            out.print(cnf.getMessage());
        }
    }

}
