package edu.ksu.cis.acad.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import edu.ksu.cis.acad.control.LoginDAO;
import edu.ksu.cis.acad.control.TheatreDAO;
import edu.ksu.cis.acad.control.UserDAO;
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
			
			UserDAO userDao = new UserDAO();
			User valUser = userDao.login(request.getParameter("username"),request.getParameter("password"));
			
			if(valUser.getType()==null){
				System.out.println("Invalid Credetails!!");
			}
			else if(valUser.getType().equals("admin")){
				response.sendRedirect("admin.jsp");
			}
			else{
				HttpSession session = request.getSession();
				session.setAttribute("id", valUser.getUsername());
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
