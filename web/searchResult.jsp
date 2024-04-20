<%@page import="Database.Database"%>
<%@page import="java.util.List"%>
<%@page import="Database.connectionDisplay"%>
<%
    String name = request.getParameter("key");
    int currUser = Integer.parseInt(request.getParameter("currUser"));
    Database db = new Database();
   List<connectionDisplay> list = db.getReqUser(name);
   if(list != null && !list.isEmpty()){
       for(connectionDisplay cd : list){
       String username = cd.getUsername();
       int userId= cd.getUserId();
 %>
      <div id="connection-wrapper" onclick="showPost('<%= userId%>',<%=currUser%>)">
 
       <h2><%= username%></h2>
        <p hidden><%= userId %></p>
      </div>
 
 <%
       
       
     }
    }
    else{
      %>
      <h2 style="text-align: center; margin-top: 10px;">No User Found </h2>
      <%
    }
%>
