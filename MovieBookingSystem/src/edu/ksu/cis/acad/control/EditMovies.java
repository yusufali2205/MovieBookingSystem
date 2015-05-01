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

public class EditMovies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Database db = new Database();
            ResultSet rs = db.result("select * from movies where id=" + id);
            rs.next();
            String mname = rs.getString("mname");
            String date = rs.getDate("release_date").toString();
            int id1= rs.getInt("id");
            out.print("<h1>EDIT MOVIES</h1><form class=\"booking\" id=\"updatemovie\" action=\"editmovies\" method=\"post\" enctype=\"multipart/form-data\">");
            out.print("<table>\n" +
"                        <tr>\n" +
"                            <td>Movie Name</td>\n" +
"                            <td><input type=\"text\" name=\"mname\" id=\"mname\" value='"+mname+"'/><span class=\"error1\" id=\"merror\"></span></td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Release Date</td>\n" +
"                            <td><input type=\"text\" name=\"date\" id=\"datepicker\" value='"+date+"'/><span class=\"error1\" id=\"dateerror\"></td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Choose poster</td>\n" +
"                            <td><input type=\"file\" name=\"file\" id=\"poster\"/><span class=\"error1\" id=\"perror\"><input type=\"hidden\" name=\"id\" value='"+id1+"'/><span class=\"error1\" id=\"perror\"></td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td colspan=\"2\"><input type=\"submit\" value=\"UPDATE MOVIE\" class=\"button_example\"/></td>\n" +
"                        </tr>\n" +
"                    </table>");
            out.print("</form><script>$('#datepicker').datepicker({minDate: 0, dateFormat: 'yy-mm-dd'});</script>");
        } catch (SQLException ex) {
            out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateBooking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                Database db = new Database();
                Map map = new HashMap();
                String[] fields = {"mname","release_date","poster","id"};
                int i = 0;
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        if(!fileName.equals("")){
                            String root = getServletContext().getRealPath("/").replace("\\build","");
                            File uploadedFile = new File(root+"/img/movies/"+fileName);
                            item.write(uploadedFile);
                            map.put(fields[i], fileName);
                        }
                                                
                    } else {
                        String textFields = item.getString();
                        map.put(fields[i], textFields); 
                    }
                    i++;
                }
                String id =(String)map.get("id");
                map.remove("id");
                db.update("movies",map,Integer.parseInt(id));
                out.print("successfully Updated");
            } catch (FileUploadException e) {
                out.print(e.getMessage());
            } catch (Exception ex) {
                out.print(ex.getMessage());
            }
        }
    }

}
