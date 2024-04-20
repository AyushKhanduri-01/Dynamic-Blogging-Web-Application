package Servlet;

import Database.Database;
import Database.connectionDisplay;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");

        Database db = new Database();
        List<connectionDisplay> list = db.getReqUser(name);

        request.setAttribute("searchResult", list);
        request.getRequestDispatcher("post.jsp").forward(request, response);

    }

}
