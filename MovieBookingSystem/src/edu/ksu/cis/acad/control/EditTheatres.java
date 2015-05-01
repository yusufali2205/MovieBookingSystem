package edu.ksu.cis.acad.control;


import edu.ksu.cis.acad.dbutil.Database;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class EditTheatres extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Database db = new Database();
            ResultSet rs = db.result("select * from theatre where id=" + id);
            rs.next();
            String tname = rs.getString("tname");
            int id1= rs.getInt("id");
            out.print("<h1>EDIT MOVIES</h1><form class=\"booking\" id=\"updatetheatre\" action=\"edittheatres\" method=\"post\">"+
                "<form class=\"booking\" id=\"addtheatres\" action=\"addtheatre\" method=\"post\">\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td>THEATRE NAME</td>\n" +
                "            <td><input id=\"theatres\" type=\"text\" name=\"theatre\" value='"+tname+"'/><span class=\"error1\" id=\"terror\"></span><input type=\"hidden\" id=\"tid\" name=\"id\" value='"+id1+"'/></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td colspan=\"2\"><input type=\"submit\" value=\"UPDATE THEATRES\" class=\"button_example\"/></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "</form>"
           );
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateBooking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            String tname =  request.getParameter("theatre");
            int id = Integer.parseInt(request.getParameter("id"));            
            Map map = new HashMap();            
            map.put("tname", tname);
        try {
            Database db = new Database();
            db.update("theatre", map, id);
            out.print("Updated theatre successfully");
        } catch (SQLException ex) {
            Logger.getLogger(EditTheatres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditTheatres.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }

}
