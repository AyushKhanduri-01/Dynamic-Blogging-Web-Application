
<%@page import="java.util.List"%>
<%@page import="Database.connectionDisplay"%>
<%@page import="Database.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post</title>
        <link rel="stylesheet" href="style/post.css">
        <script src="JavaScript/post.js"></script>
        <script
            src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>

    </head>
    <body>

        <%
            HttpSession s2 = request.getSession(false);
            int user_id = -1;
            if (s2 != null) {
                String str = (String) s2.getAttribute("isLoggedIn");
                user_id = (int) s2.getAttribute("user_id");
                System.out.println(user_id);
            } else {
                response.sendRedirect("index.html");
            }

        %>
        <!--Header start-->
        <div class="headers">
            The Canvas of Ideas          
        </div>
        <!--Header end-->





        <!--Wrapper-div-Start-->
        <div class="wrapper-div">


            <!--All-Connection-div-->
            <div class="connection-div">
                <h2 id="connctions">CONNECTIONS</h2>

                <%        Database db = new Database();
                    List<connectionDisplay> list = db.getAllUser();

                    for (connectionDisplay cd : list) {
                        int userid = cd.getUserId();
                        String username = cd.getUsername();

                %>
                <div id="connection-wrapper" onclick="showPost('<%=userid%>', '<%=user_id%>')">
                    <h2>- <%= username%></h2>
                    <p hidden><%= userid%></p>

                </div>

                <%
                    }
                %>

            </div>
            <!--All-Connection-div-close-->        





            <!--Main-div-start-->
            <div class="main">
                <div class ="div1">
                    <div class="div11" >
                        <button class="btn" onclick="changeVisibility()" >New Post</button>
                        <button class="btn" onclick="showPost('<%= user_id%>', '<%=user_id%>')">My Posts</button>
                        <button class="btn" onclick="showPost('0', '<%=user_id%>')">All Posts</button>
                    </div>

                </div>
                <div class="form-container" id="btn" hidden>
                    <form action="NewPost" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="user_id" value="<%=  user_id%>">
                        <input type="text" name="title" placeholder="Title" required>
                        <textarea name="description" placeholder="Description" required></textarea>
                        <input type="file" name="file" accept="image/*">
                        <button type="submit">Submit</button>
                        <button type="button" onclick="changeVisibility()">Back</button>
                    </form>

                </div>



                <div class="post-container" id="postContainer">
                    <!--This div display all post with help of AJAX -->
                </div> 


                <div>              
                </div>
            </div> 
            <!--Main-div-close-->




            <!--Search-div-start-->
            <div class="search-div">

                <div class="search-bar" data-user-id="<%= user_id%>">
                    <input type="text" name="search" id="searchBar" placeholder=" Search by name" autocomplete="off">     

                </div>


                <div id="searchItem">
                    <!--this will display search items-->
                </div>

            </div>
            <!--Search-div-close-->





        </div>
        <!--Wrapper-div-close-->




        <script>
            $(document).ready(function (e) {
                var User = <%=user_id%>;
                var postData = {key: 0,
                    currUser: User};
                $.ajax({
                    url: "PostDisplay.jsp",
                    method: "POST",
                    data: postData,
                    success: function (data, textStatus, jqXHR) {
                        $('#postContainer').html(data);
                    }
                });
            });

        </script>


    </body>
</html>
