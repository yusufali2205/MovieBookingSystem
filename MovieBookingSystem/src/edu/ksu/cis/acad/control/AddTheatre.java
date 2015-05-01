package edu.ksu.cis.acad.control;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ksu.cis.acad.dbutil.Database;

public class AddTheatre extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String theatre = request.getParameter("theatre").trim();
        Map map = new HashMap();
        map.put("tname", theatre);
        try {
            Database db = new Database();
            db.insert("theatre", map);
            out.print("successfully added");
        } catch (SQLException ex) {
            out.printf(ex.getMessage());
        } catch (ClassNotFoundException cnf) {
            out.printf(cnf.getMessage());
        }

    }
}
