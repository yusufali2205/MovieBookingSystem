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

public class Theatres extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
       
        try {
            Database db= new Database();
            ResultSet rs = db.result("select * from theatre order by id desc");
            out.print("<h1>THEATRE LIST</h1><table id='mybookings'><tr>");
            out.print("<th>Theatre Name</th><th>edit</th><th>delete</th>");
            while(rs.next()){
                out.print("<tr>");
                out.print("<td>"+rs.getString("tname")+"</td>");        
                out.print("<td><a class='edit' href='edittheatres?id="+rs.getInt("id")+"'>Edit Theatre</a></td>");
                out.print("<td><a class='cancel' href='deletetheatre?id="+rs.getInt("id")+"'>Delete Theatre</a></td>");
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
