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


public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String firstname= request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();        
        String username= request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        
        Map map = new HashMap();
        map.put("firstname",firstname);
        map.put("lastname",lastname);
        map.put("username",username);
        map.put("password",password);
        map.put("email",email);
        
        try{
            Database db = new Database();
            db.insert("registration", map);
            db.closeDb();
            response.sendRedirect("login.jsp");
        }catch(SQLException se){
 
            session.setAttribute("message","email already registered"+se.getMessage());
            response.sendRedirect(".");
        }catch(ClassNotFoundException cnf){
            out.print(cnf.getMessage());
        }
    }


}
