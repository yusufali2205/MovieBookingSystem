package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTheatre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Database db = new Database();
            int id = Integer.parseInt(request.getParameter("id"));
            db.delete("theatre",id);
            out.print("successfully deleted");
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {

        }
    }

}
