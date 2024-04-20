
document.addEventListener('DOMContentLoaded', function () {

    var searchBar = document.getElementById('searchBar');
    var searchItem = document.getElementById("searchItem");
    var user_id = document.getElementsByClassName('search-bar')[0].getAttribute('data-user-id');

    searchBar.addEventListener('input', function () {
        $(document).ready(function (e) {
            var name = document.getElementById("searchBar").value;
            if (name !== "") {
                var postData = {key: name,
                    currUser: user_id};
                $.ajax({
                    url: "searchResult.jsp",
                    method: "POST",
                    data: postData,
                    success: function (data, textStatus, jqXHR) {

                        $('#searchItem').html(data);
                    }
                });
            } else {
                searchItem.innerHTML = '';
            }
        });
    });
});

function changeVisibility() {
    var ele = document.getElementById("btn");
    if (ele.style.display === "none") {
        ele.style.display = 'block';
        ele.style.display = 'flex';
    } else {
        ele.style.display = "none";
    }

}

function showPost(userId, currUser) {
    $(document).ready(function (e) {
        var postData = {key: userId,
            currUser: currUser};

        $.ajax({
            url: "PostDisplay.jsp",
            method: "POST",
            data: postData,
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                $('#postContainer').html(data);
            }
        });
    });
}

function search() {
    $(document).ready(function (e) {
        var name = document.getElementById("searchBar").value;
        if (name !== "") {
            var postData = {key: name};
            $.ajax({
                url: "searchResult.jsp",
                method: "POST",
                data: postData,
                success: function (data, textStatus, jqXHR) {

                    $('#searchItem').html(data);
                }

            });
        }

    });

}

function addComment(post_id, user_id) {

    var comment = $('#comment-input-bar-' + post_id).val();

    $('#comment-input-bar-' + post_id).val("");

    if (comment !== null) {
        var postData = {post_id: post_id,
            user_id: user_id,
            comment: comment};

        $.ajax({
            url: "CommentFunction",
            method: "POST",
            data: postData,
            success: function (data, textStatus, jqXHR) {
                getComment(post_id);
            }
        });
    }

}

function getComment(post_id) {
    var ele = document.getElementById("comment_" + post_id);

    if (ele.style.display === 'block') {
        ele.style.display = 'none';
    } else {
        ele.style.display = 'block';
    }

    var postData = {post_id: post_id};
    $.ajax({
        url: "Comments.jsp",
        method: "POST",
        data: postData,
        success: function (data, textStatus, jqXHR) {
            $('#comment-box-' + post_id).html(data);
        }
    });
}


function doLike(user_id, post_id) {
    $('#likebtn_' + post_id).css('background-color', 'red');
    $(document).ready(function (e) {
        var dataPost = {user_id: user_id,
            post_id: post_id
        };

        $.ajax({
            url: "LikeFunction",
            method: "POST",
            data: dataPost,
            success: function (data, textStatus, jqXHR) {

            }
        });
    });
}

