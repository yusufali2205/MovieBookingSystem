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

public class MovieList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String date = request.getParameter("date");
        Database db;
        try {
            db = new Database();
            ResultSet rs = db.result("select * from movies where release_date <= '" + date + "'");
            out.print("<option value='0'>--select--</option>");
            while (rs.next()) {
                out.print("<option value='" + rs.getString("id") + "'>");
                out.print(rs.getString("mname"));
                out.print("</option>");
            }

        } catch (SQLException ex) {

        } catch (ClassNotFoundException ex) {

        }

    }

}
