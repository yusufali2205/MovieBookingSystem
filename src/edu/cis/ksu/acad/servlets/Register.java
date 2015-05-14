package edu.cis.ksu.acad.servlets;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.logic.UserDAO;
import edu.cis.ksu.acad.model.User;

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
        
        User user = new User();
        user.setEmail(email);
        user.setFirst_name(firstname);
        user.setLast_name(lastname);
        user.setPassword(password);
        user.setUsername(username);
        user.setType("customer");
        
        try{
        	UserDAO dao = new UserDAO();
        	dao.createUser(user);
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        }catch(Exception se){
 
            session.setAttribute("message","username already registered"+se.getMessage());
            response.sendRedirect(".");
        }
    }


}
