<%@page import="java.util.LinkedList"%>
<%@page import="Database.Comments"%>
<%@page import="java.util.List"%>
<%@page import="Database.Database"%>
<%
    int post_id = Integer.parseInt(request.getParameter("post_id"));
    Database db = new Database();
    List<Comments> list = new LinkedList<>();
    list = db.getAllComments(post_id);

    if (list.isEmpty()) {

%>
<h2 style="text-align: center; margin-top: 10px;">No Comment Found </h2>

<%    }

    for (Comments cm : list) {
        String comment = cm.getComment();

%>

<div  id="comment_box">
    <p id="comment"><%=comment%></p>
    <hr>
</div>

<%

    }

%>

