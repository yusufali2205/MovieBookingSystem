package edu.cis.ksu.acad.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cis.ksu.acad.logic.UserDAO;
import edu.cis.ksu.acad.model.User;

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
           
            UserDAO userDao = new UserDAO();
			User valUser = userDao.login(username,password);
		
			if(valUser.getType()!=null){
                        session.setAttribute("message", null);
                        session.setAttribute("username", valUser.getUsername());
                        session.setAttribute("id", valUser.getUsername());
                        
                        if(valUser.getType().equals("admin")){
                  
                        	session.setAttribute("admin",valUser.getUsername());
                        	response.sendRedirect("admin.jsp");
                        }
                        else{
                        	response.sendRedirect("home.jsp");}
                    } else {
                        session.setAttribute("message", "username or password is incorrect");
                        response.sendRedirect("login.jsp");
                    }
           
        } 
finally {
            out.close();
        }
    }
}
