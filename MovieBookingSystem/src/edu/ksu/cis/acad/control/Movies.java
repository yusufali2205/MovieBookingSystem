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

public class Movies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
       
        try {
            Database db= new Database();
            ResultSet rs = db.result("select * from movies order by id desc");
            out.print("<h1>MOVIE LIST</h1><table id='mybookings'><tr>");
            out.print("<th>Poster</th><th>Movie Name</th><th>Release date</th><th>edit</th><th>delete</th>");
            while(rs.next()){
                out.print("<tr>");
                out.print("<td><img width='50' src='img/movies/"+rs.getString("poster")+"'/></td>");
                out.print("<td>"+rs.getString("mname")+"</td>");
                out.print("<td>"+rs.getDate("release_date")+"</td>");                
                out.print("<td><a class='edit' href='editmovies?id="+rs.getInt("id")+"'>Edit Movie</a></td>");
                out.print("<td><a class='cancel' href='deletemovie?id="+rs.getInt("id")+"'>Delete Movie</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex1) {
            out.print(ex1.getMessage());
        }
    }


}
