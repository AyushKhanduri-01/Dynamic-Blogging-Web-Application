
package Servlet;
import Database.Database;
import Database.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;

 
public class Login extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        HttpSession s1= req.getSession();
       
        String email = req.getParameter("email");
        String password=req.getParameter("password");
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        User uc = new User();
        uc.setEmail(email);
        uc.setPassword(password);
        
        Database db = new Database();
        int result = db.isValid(uc);
       if(result != -1){
          s1.setAttribute("isLoggedIn","yes");
          s1.setAttribute("user_id",result);
          System.out.println(result);
          res.sendRedirect("post.jsp");
        }
       else{
           out.println("User not exist");
       }
        
        
        
        
        
        
        
    }
        
    
}
