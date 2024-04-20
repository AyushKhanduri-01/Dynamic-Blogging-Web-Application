package Servlet;
import Database.Database;
import Database.User;
import com.mysql.cj.jdbc.Blob;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class Signup extends HttpServlet {
    public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException, ServletException{
        
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Part file = req.getPart("profile-picture");
        
        
        
        
        User uc = new User();
        uc.setEmail(email);
        uc.setUsername(username);
        uc.setPassword(password);
        
        
        // file handling
        if(file != null ){
         String fileName = file.getSubmittedFileName();
        InputStream fileContent = file.getInputStream();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "Profile_picture"; // Define your upload directory
        Files.createDirectories(Paths.get(uploadPath));
        Files.copy(fileContent, Paths.get(uploadPath, fileName)); 
        uc.setImagefile(fileName);
        }
       
        
        //file handling close

        
        
            
            
            
            
            
            
                
        
        
        
        Database db = new Database();
        try{
            if(db.addUser(uc)){
               res.sendRedirect("index.html");
            }
        
        else{
            
            res.sendRedirect("signup.html");
        }
        
        }    
    catch(Exception e){       
    e.printStackTrace();
}
            
        
   

    }}