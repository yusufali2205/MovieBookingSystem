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

public class LoginValidation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        PrintWriter out = response.getWriter();

        try {
            Database db = new Database();
            if (username.equals("admin") && password.equals("admin")) {
                session.setAttribute("message", null);
                session.setAttribute("admin", "admin");
                response.sendRedirect("admin.jsp");
            } else {
                String query = "select count(*) from registration where username='" + username + "' and password='" + password + "'";

                ResultSet rs = db.result(query);
                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                        session.setAttribute("message", null);
                        session.setAttribute("username", username);
                        session.setAttribute("id", db.getId(username));
                        response.sendRedirect("home.jsp");
                    } else {
                        session.setAttribute("message", "username or password is incorrect");
                        response.sendRedirect("login.jsp");
                    }
                }
            }
        } catch (SQLException se) {
            out.print(se.getMessage());
        } catch (ClassNotFoundException cnf) {
            out.print(cnf.getMessage());
        } finally {
            out.close();
        }
    }
}
