
package Utility;

import java.sql.*;

public class DbConfig {
    public static Connection getConnection() throws ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/postproject";
        String user = "PostProject";
        String password = "Post@Project";
      
       //Load driver
      Class.forName("com.mysql.jdbc.Driver"); 
       // create connection 
       try{
           Connection conn = DriverManager.getConnection(url,user,password);
           return conn;
       }
       catch(SQLException e){
           e.printStackTrace();
       }
      return null;
    }
}
