package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SeatStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        //int uid = (Integer) session.getAttribute("id");
        int tid = Integer.parseInt(request.getParameter("tid"));
        int mid = Integer.parseInt(request.getParameter("mid"));
        String date = request.getParameter("date");
        int showtime = Integer.parseInt(request.getParameter("showtime"));

        int cols = 20;
        int rows = 5;
        String[] alph = {"A", "B", "C", "D", "E"};

        try {
            Database db = new Database();
            String booked = db.getAvailability(tid, mid, date, showtime);
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
        } catch (SQLException se) {
            out.print("something went wrig");
        } catch (ClassNotFoundException cnf) {
        }
        

        out.print("</table>");
    }
}
