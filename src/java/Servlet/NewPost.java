
package Servlet;

import Database.Database;
import Database.Post;
import Helper.FileHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class NewPost extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
       int user_id = Integer.parseInt( req.getParameter("user_id"));
       String title = req.getParameter("title");
       String description = req.getParameter("description");
       Part file = req.getPart("file");
       
       Post post = new Post();
      
       
      
       
       if(file != null && file.getSize() > 0){
       // file handling---------
       String fileName = file.getSubmittedFileName();
       InputStream fileContent = file.getInputStream();
       String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // Define your upload directory
        Files.createDirectories(Paths.get(uploadPath));
        Files.copy(fileContent, Paths.get(uploadPath, fileName));       
         post.setFileName(fileName);
       }
       // file handling ending........

       
       post.setUser_id(user_id);
       post.setTitle(title);
       post.setDescription(description);
      
 
       Database db = new Database();
       if(db.addPost(post)){
           res.sendRedirect("post.jsp");
          
       }
       else{
           
       }
       
       
       
       
    }
}
