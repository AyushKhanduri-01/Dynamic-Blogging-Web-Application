package Database;

import Utility.DbConfig;
import java.io.PrintWriter;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Database implements DatabaseInterface {

    @Override
    public int isValid(User uc) {
        try (Connection conn = DbConfig.getConnection()) {
            String email = uc.getEmail();
            String password = uc.getPassword();

            String Query = "select user_id from user where email = ? and password = ?";
            try (PreparedStatement pstm = conn.prepareStatement(Query)) {
                pstm.setString(1, email);
                pstm.setString(2, password);

                try (ResultSet result = pstm.executeQuery()) {
                    if (result.next()) {
                        int id = result.getInt("user_id");
                        return id;
                    }
                }
            }

        } catch (Exception e) {
            System.out.print(e.toString());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean addUser(User uc) {
        String username = uc.getUsername();
        String email = uc.getEmail();
        String password = uc.getPassword();
        String imagefile = uc.getImagefile();
        String query = "INSERT INTO user (username, email, password, profile_picture) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {

                pstm.setString(1, username);
                pstm.setString(2, email);
                pstm.setString(3, password);
                pstm.setString(4, imagefile);

                int row = pstm.executeUpdate();
                return row > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addPost(Post post) {

        String title = post.getTitle();
        String description = post.getDescription();
        int user_id = post.getUser_id();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String file = post.getFile();

        String query = "insert into post(user_id,title,description,time_stamp,file_name) values (?,?,?,?,?)";

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, user_id);
                pstm.setString(2, title);
                pstm.setString(3, description);
                pstm.setTimestamp(4, timestamp);
                pstm.setString(5, file);

                int row = pstm.executeUpdate();
                return row > 0;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Post> getAllPost() {
        String query = "select * from post order by time_stamp desc";
        List<Post> list = new ArrayList<>();
        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {

                ResultSet set = pstm.executeQuery();
                while (set.next()) {
                    int post_id = set.getInt("post_id");
                    int user_id = set.getInt("user_id");
                    String title = set.getString("title");
                    String description = set.getString("description");
                    Timestamp timestamp = set.getTimestamp("time_stamp");
                    String file_name = set.getString("file_name");

                    Post p = new Post();
                    p.setPost_id(post_id);
                    p.setUser_id(user_id);
                    p.setTitle(title);
                    p.setDescription(description);
                    p.setTimestamp(timestamp);
                    p.setFileName(file_name);

                    list.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Post> getIndividualPost(int userId) {
        String query = "select * from post where user_id = ? order by time_stamp desc";
        List<Post> list = new ArrayList<>();
        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, userId);

                ResultSet set = pstm.executeQuery();
                while (set.next()) {
                    int post_id = set.getInt("post_id");
                    int user_id = set.getInt("user_id");
                    String title = set.getString("title");
                    String description = set.getString("description");
                    Timestamp timestamp = set.getTimestamp("time_stamp");
                    String file_name = set.getString("file_name");

                    Post p = new Post();
                    p.setPost_id(post_id);
                    p.setUser_id(user_id);
                    p.setTitle(title);
                    p.setDescription(description);
                    p.setTimestamp(timestamp);
                    p.setFileName(file_name);

                    list.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getName(int userId) {
        String username = null;
        String query = "select username from user where user_id = ?";
        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, userId);
                try (ResultSet result = pstm.executeQuery()) {
                    if (result.next()) {
                        username = result.getString("username");

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    @Override
    public List<connectionDisplay> getAllUser() {
        List<connectionDisplay> list = new ArrayList<>();
        String query = "select user_id,username from user";

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {

                ResultSet set = pstm.executeQuery();
                while (set.next()) {

                    int user_id = set.getInt("user_id");
                    String username = set.getString("username");

                    connectionDisplay cd = new connectionDisplay();
                    cd.setUserId(user_id);
                    cd.setUsername(username);

                    list.add(cd);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<connectionDisplay> getReqUser(String name) {
        String query = "select user_id,username from user where username Like '" + name + "%'";
        List<connectionDisplay> list = new ArrayList<>();
        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {

                ResultSet set = pstm.executeQuery();
                while (set.next()) {
                    String username = set.getString("username");
                    int userid = set.getInt("user_id");

                    connectionDisplay cd = new connectionDisplay();
                    cd.setUserId(userid);
                    cd.setUsername(username);

                    list.add(cd);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getProfilePicture(int userId) {
        String fileName = null;
        String query = "select profile_picture from user where user_id = ?";
        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, userId);
                try (ResultSet result = pstm.executeQuery()) {
                    if (result.next()) {
                        fileName = result.getString("profile_picture");

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public void addLike(int user_id, int post_id) {

        String query = "insert into liketable values (?,?)";

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, post_id);
                pstm.setInt(2, user_id);
                pstm.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getNumLikes(int post_id) {
        int num = 0;
        String query = "select count(*) as likeCount from liketable where post_id = ?";

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, post_id);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    num = rs.getInt("likeCount");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public void addComment(Comments cmt) {
        String query = "insert into commenttable (user_id, post_id, comment)values (?,?,?)";
        int user_id = cmt.getUser_id();
        int post_id = cmt.getPost_id();
        String comment = cmt.getComment();

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, user_id);
                pstm.setInt(2, post_id);
                pstm.setString(3, comment);
                pstm.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Comments> getAllComments(int post_id) {
        List<Comments> list = new LinkedList<>();
        String query = "select * from commenttable where post_id = ?";

        try (Connection conn = DbConfig.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, post_id);

                ResultSet rs = pstm.executeQuery();

                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String comment = rs.getString("comment");
                    Comments cm = new Comments(user_id, post_id, comment);
                    list.add(cm);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
