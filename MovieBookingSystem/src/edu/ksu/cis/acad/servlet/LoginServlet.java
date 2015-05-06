package edu.ksu.cis.acad.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ksu.cis.acad.control.LoginDAO;
import edu.ksu.cis.acad.control.TheatreDAO;
import edu.ksu.cis.acad.model.Theatre;
import edu.ksu.cis.acad.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		
		try{
			
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			
			LoginDAO loDao = new LoginDAO();
			User validateduser = loDao.getUser(user);
			
			if(validateduser==null){
				System.out.println("Invalid Credetails!!");
			}
			else if(validateduser.getType().equals("admin")){
				response.sendRedirect("admin.jsp");
			}
			else{
				TheatreDAO tDao = new TheatreDAO();
				ArrayList<Theatre> theaterlist = tDao.getAllTheatres();
				request.setAttribute("theatres", theaterlist);
		        request.getRequestDispatcher("home.jsp").forward(request, response);
			 }
		}
		catch (Throwable theException) 	    
		{
		     theException.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
