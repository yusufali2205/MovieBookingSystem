package edu.ksu.cis.acad.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.ksu.cis.acad.control.BookingsDAO;
import edu.ksu.cis.acad.control.TheatreDAO;
import edu.ksu.cis.acad.model.PlayedIn;
import edu.ksu.cis.acad.model.Theatre;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
//		String selectedValue = request.getParameter("value");
//		System.out.println(selectedValue);
//	    Map<String, String> options = new HashMap<String, String>();
//	    options.put("PVR", "ABC");
//	    options.put("PVR", "BDD");
//	    options.put("PVR", "GGG");
//	    options.put("PVR", "NNN");
//	    String json = new Gson().toJson(options);
//	    response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    System.out.println(json);
//	    response.getWriter().write(json);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(request.getParameter("value"));
//		try {
//		String datestr = request.getParameter("date");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
//		java.util.Date date;
//		
//			date = sdf1.parse(datestr);
//		
//		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
//		
//		TheatreDAO tDao = new TheatreDAO();
//		Theatre theatre = tDao.getAllTheatresByName(request.getParameter("theatre"));
//		
//		BookingsDAO Bdao = new BookingsDAO();
//		ArrayList<PlayedIn> playedin = Bdao.getMovieShowsByTheatreAndDate(theatre.getTheatre_id(),sqlStartDate);
//		
//		//request.setAttribute("theatre", request.getParameter("theatre"));
//		request.setAttribute("datepicker", datestr);
//		request.setAttribute("movies", playedin);
//        request.getRequestDispatcher("home.jsp").forward(request, response);
//		
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
