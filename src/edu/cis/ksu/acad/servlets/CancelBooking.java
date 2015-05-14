package edu.cis.ksu.acad.servlets;

import edu.cis.ksu.acad.dbutil.Database;
import edu.cis.ksu.acad.logic.BookingsDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelBooking extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		BookingsDAO bdao = new BookingsDAO();
		bdao.cancelBooking(id);
		request.getRequestDispatcher("mybookings.jsp").forward(request, response);
    }

}
