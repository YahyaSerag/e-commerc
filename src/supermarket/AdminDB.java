
package supermarket;

import java.sql.*;
import javax.swing.JOptionPane;

public class AdminDB {
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    public AdminDB(){
        
    }
    public void connecte(){
                     
                      try{
        String host = "jdbc:derby://localhost:1527/SuperMarket ";
        String username = "ahmed"; 
         String userpassword = "123456789";
        con = DriverManager.getConnection(host,username,userpassword);
        
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String SQL = "SELECT * FROM ADMIN";
        rs = stmt.executeQuery(SQL);

        
}
catch( SQLException E){
            System.out.println(E.getMessage());
}
                    
                    
    }
    
     public void closeConnecte(){
        try{con.close();}
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}
