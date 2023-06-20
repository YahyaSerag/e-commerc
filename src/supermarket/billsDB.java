
package supermarket;

import java.sql.*;
import javax.swing.JOptionPane;
import supermarket.ProductDB;


public class billsDB extends Bills{
    
     static Connection con;
    static Statement stmt;
    static ResultSet rs;
    ProductDB p = new ProductDB();
    
    public billsDB(){
        
    }
    
     public void connectebills(){
                      try{
        String host = "jdbc:derby://localhost:1527/SuperMarket ";
        String username = "ahmed"; 
         String userpassword = "123456789";
        con = DriverManager.getConnection(host,username,userpassword);
        
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String SQL = "SELECT * FROM bills";
        rs = stmt.executeQuery(SQL);

        
}
catch( SQLException E){
            System.out.println(E.getMessage());
}
    }
    
    
    
    public void closeConnectebills(){
        try{con.close();}
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public void truncate(){
                       try{
        String host = "jdbc:derby://localhost:1527/SuperMarket ";
        String username = "ahmed"; 
         String userpassword = "123456789";
        con = DriverManager.getConnection(host,username,userpassword);
        
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate("TRUNCATE TABLE BILLS");
        

        
}
catch( SQLException E){
            System.out.println(E.getMessage());
}finally{
                      closeConnectebills();
                           
                       }
    }
      
    
    
        public void Cash (int id,int quant) 
    {
        int price = 0;
        String name="";
          p.connect();
          
        
        try{
       
             
            int search_id = id;
            int found = 0;
           p.rs.absolute(0);
            while(p.rs.next()){
                if(search_id == p.rs.getInt(1)){
                    found = 1;
                    price = p.rs.getInt(3);
                    name = p.rs.getString(2);
                    
                    break;
                }
                
            }
        }catch( SQLException e){
            System.out.println(e.getMessage());
        }finally{
            
                p.closeConnect();
            
        }
 
            
            this.TotalPrice= this.TotalPrice +(price*quant);
            if(this.TotalPrice>=10000) this.Offers();
    
      
               connectebills();
        
        
        PreparedStatement ps = null;
        try{
            
            String sql = "INSERT INTO BILLS(id, name, price, quantity,proid) VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setInt(4, quant);
            ps.setInt(5, id);
            ps.execute();
           JOptionPane.showMessageDialog(null, "product Has Been Insert");
            
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            try{
                ps.close();
                closeConnectebills();
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
                       p.connect();
        
                 try{
             
             int search_id = id;
            int found = 0;
           p.rs.absolute(0);
            while(p.rs.next()){
                if(search_id == p.rs.getInt(1)){
        int quantity =p.rs.getInt(4);
        String exp = p.rs.getString(5);
        String period = p.rs.getString(6);
        p.rs.updateInt(1, id);
        p.rs.updateString(2, name);
        p.rs.updateInt(3, price);
        p.rs.updateInt(4, (quantity-quant));
        p.rs.updateString(5, exp);
        p.rs.updateString(6, period);
        p.rs.updateRow();
                    break;
                }
                
            }
            
          
         
         }
        catch(SQLException e){System.out.println(e.getMessage());}
         finally{
            p.closeConnect();
        }

    }
        
            public void Offers()
    {
            JOptionPane.showMessageDialog(null,"congrats ...you got  10% descount ");
            this.TotalPrice-=(this.TotalPrice/10);
    }
            
         public void DeleteItem(int id){
         
         int price = 0;
         int quantity = 0;
         
        connectebills();
        
        try{
             
             int search_id = id;
            int found = 0;
           rs.absolute(0);
            while(rs.next()){
                if(search_id == rs.getInt(1)){
                    price = rs.getInt(3);
                    quantity = rs.getInt(4);
                    found = 1;
                   rs.deleteRow();
                    break;
                }
                
            }
            this.TotalPrice-=price*quantity;
          
         
         }
        catch(SQLException e){System.out.println(e.getMessage());}
         finally{
            closeConnectebills();
        }
        
                  p.connect();
        
                 try{
             
             int search_id = id;
            int found = 0;
           p.rs.absolute(0);
            while(p.rs.next()){
                if(search_id == p.rs.getInt(1)){
        
        String exp = p.rs.getString(5);
        String period = p.rs.getString(6);
        p.rs.updateInt(1, id);
        p.rs.updateString(2, p.rs.getString(2));
        p.rs.updateInt(3, price);
        p.rs.updateInt(4, (p.rs.getInt(4)+quantity));
        p.rs.updateString(5, exp);
        p.rs.updateString(6, period);
        p.rs.updateRow();
                    break;
                }
                
            }
            
          
         
         }
        catch(SQLException e){System.out.println(e.getMessage());}
         finally{
            p.closeConnect();
        }
        
   }
         
         
         public void finsh(){
             
             truncate();
         }
    
}
