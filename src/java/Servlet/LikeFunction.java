
package Servlet;

import Database.Database;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikeFunction extends HttpServlet{
    @Override
    public  void doPost(HttpServletRequest req, HttpServletResponse res){
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int post_id=Integer.parseInt(req.getParameter("post_id"));
        
        
        Database db = new Database();
        db.addLike(user_id, post_id);
        
    }
}
