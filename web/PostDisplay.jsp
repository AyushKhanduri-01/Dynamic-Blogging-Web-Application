<%@page import="java.util.List"%>
<%@page import="Database.Post"%>
<%@page import="Database.Database"%>
<%@page import="java.sql.*"%>

<%
    int key = Integer.parseInt(request.getParameter("key"));
    int currUser = Integer.parseInt(request.getParameter("currUser"));

    Database db = new Database();
    List<Post> list;
    if (key == 0) {
        list = db.getAllPost();
        System.out.println(key);
    } else {
        list = db.getIndividualPost(key);

    }

    for (Post p : list) {
        int user_id = p.getUser_id();
        int post_id = p.getPost_id();
        String title = p.getTitle();
        String description = p.getDescription();
        Timestamp timestamp = p.getTimestamp();
        String file_name = p.getFile();

        String name = db.getName(user_id);

        String fileName = db.getProfilePicture(user_id);

        int num = db.getNumLikes(post_id);

%>
<div id="post-wrapper">

    <div class="post-header">
        <div class="thumbnail">
            <img src="Profile_picture\<%=fileName%>" id="thumbnailImage"  onerror="this.onerror=null; this.src='Profile_picture/default.png';"  alt="">
        </div>
        <div class="user-details">
            <div class="username" id="username"><%=name%></div>
            <div class="timestamp" id="timestamp"><%=timestamp%></div>

        </div>
    </div>

    <div class="post-content">
        <h2 id= "title"><%=title%></h2>
        <p id= "description"><%=description%></p>
        <div class="media-display">
            <img src="uploads\<%=file_name%>" alt="">
            <audio></audio>
            <video></video>
            <div>
                <button class="L&C-btnn" onclick="doLike(<%=currUser%>,<%=post_id%>)" id="likebtn_<%=post_id%>">Like : <%=num%> </button>
                <button onclick="getComment(<%=post_id%>)" class="L&C-btnn" >comment</button>

                <div class="comment" id="comment_<%=post_id%>">

                    <div id="comment-input">
                        <input type="text" id="comment-input-bar-<%=post_id%>">
                        <button id="comment-post-btn-<%=post_id%>" onclick="addComment('<%=post_id%>',<%=currUser%>)">Post</button>
                    </div>

                    <div id="comment-box-<%=post_id%>" class="comment_box">
                        <!--all comments will display here using ajax-->
                    </div>



                </div>

            </div>  
        </div>

    </div>
</div>


<%

    }

%>