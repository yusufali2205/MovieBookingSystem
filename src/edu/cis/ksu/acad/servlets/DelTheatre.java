package edu.cis.ksu.acad.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cis.ksu.acad.logic.TheatreDAO;

public class DelTheatre extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String theatre = request.getParameter("theatre");
        
        TheatreDAO tDao = new TheatreDAO();
        tDao.deleteTheatre(theatre);
        
        
        out.print("successfully deleted");
       
    }
}

