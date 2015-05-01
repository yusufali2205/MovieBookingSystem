package edu.ksu.cis.acad.control;

import edu.ksu.cis.acad.dbutil.Database;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddMovies extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        PrintWriter out= response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                Database db = new Database();
                Map map = new HashMap();
                String[] fields = {"mname","release_date","poster"};
                int i = 0;
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        map.put(fields[i], fileName);
                        String root = getServletContext().getRealPath("/").replace("\\build","");
                        File uploadedFile = new File(root+"/img/movies/"+fileName);
                        item.write(uploadedFile);

                    } else {
                        String textFields = item.getString();
                        map.put(fields[i], textFields); 
                    }
                    i++;
                }
                db.insert("movies",map);
                out.print("successfully added");
            } catch (FileUploadException e) {
                out.print(e.getMessage());
            } catch (Exception ex) {
                out.print(ex.getMessage());
            }
        }
    }

}
