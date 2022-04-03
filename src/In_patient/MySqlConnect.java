
package In_patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class MySqlConnect {
    connection conn=null;
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/athlanta_hospital?autoReconnect=true&useSSL=false","root","");
            //JOptionPane.showMessageDialog(null, "Connected to database");
            return conn;
            
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    private static class connection {

        public connection() {
        }
    }
    
}
