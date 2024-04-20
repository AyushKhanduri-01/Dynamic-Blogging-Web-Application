package Database;

import java.util.List;

public interface DatabaseInterface {

    public int isValid(User uc);

    public boolean addUser(User uc);

    public boolean addPost(Post uc);

    public List<Post> getAllPost();

    public List<Post> getIndividualPost(int userId);

    public String getName(int userId);

    public List<connectionDisplay> getAllUser();

    public List<connectionDisplay> getReqUser(String name);

    public String getProfilePicture(int userId);

    public void addLike(int user_id, int post_id);

    public int getNumLikes(int post_id);

    public void addComment(Comments cmt);

    public List<Comments> getAllComments(int post_id);
}
