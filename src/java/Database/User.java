
package Database;

import com.mysql.cj.jdbc.Blob;

public class User {
   String email;
   String password;
   String username;
   String imagefile;

    public String getImagefile() {
        return imagefile;
    }

    public void setImagefile(String imagefile) {
        this.imagefile = imagefile;
    }

    

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

   

    

}
