package edu.ksu.cis.acad.servlet;


import edu.ksu.cis.acad.control.TheatreDAO;
import edu.ksu.cis.acad.dbutil.DatabaseConnect;
import edu.ksu.cis.acad.model.Theatre;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTheatre extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String theatre = request.getParameter("theatre");
        String shtime =  request.getParameter("shtime");
        
        
        System.out.println(theatre+shtime);
        Theatre thertre = new Theatre();
        thertre.setTheatre_name(theatre);
        thertre.setShow_time(shtime);
        
        TheatreDAO tDao = new TheatreDAO();
        tDao.addTHEATRE(thertre);
        
        
        out.print("successfully added");
       
    }
}
