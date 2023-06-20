
package supermarket;


import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JOptionPane;


public class Admin {
    protected int id;
     
    protected String User_Name;
    
    protected String User_Pass;
    
    protected String Gender;
    
    
    
    
     public int id(){
        return this.id;
    }
    
    public String GetUserName(){
        return this.User_Name;
    }
    
    public String GetPasswpord(){
        return this.User_Pass;
    }
    
     public String gender(){
        return this.Gender;
    }
}
