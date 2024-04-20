
package Database;

import com.mysql.cj.jdbc.Blob;
import java.sql.*;


public class Post {
    int post_id;
    int user_id;
    String title;
    String description;
    Timestamp timestamp;
    String file_name;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getFile() {
        return file_name;
    }

    public void setFileName(String file_name) {
        this.file_name = file_name;
    }
    
    
    
  
}