
package Servlet;

import Database.Comments;
import Database.Database;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFunction extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int post_id=Integer.parseInt(req.getParameter("post_id"));
        String comment = req.getParameter("comment");
        
        Database db = new Database();
        Comments cmt = new Comments(user_id,post_id,comment);
        db.addComment(cmt);
        
    }
    
}
