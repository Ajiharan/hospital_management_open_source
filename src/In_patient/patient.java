
package In_patient;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;



public class patient {
    String pid="";
    String date="";
    String time="";
    String pn_title="";
    String p_name="";
    String p_initials="";
    String gender="";
    int age;
    String dob= "";
    String nic_pp="";
    
    String p_address="";
    int p_landline;
    int p_mobile;
    String p_nationality="";
    
    
    String g_title="";
    String g_name="";
    String g_nic_pp="";
    String g_relationship="";
    String g_address="";
    int g_landline;
    int g_mobile;
    //boolean p_available=true; 
    
    
    
    
    
    
    public patient(String a,String pdate,String ptime,String b, String c, String d, String e, int f,String g ,String h, String i, int j, int k, String l ){
        pid=a;
        date=pdate;
        time=ptime;
        pn_title=b;
        p_name=c;
        p_initials=d;
        gender=e;
        age=f;
        dob=g;
        nic_pp=h;
        p_address=i;
        p_landline=j;
        p_mobile=k;
        p_nationality=l;
        
    }
    
    //overload
    public patient(String a,String pdate,String ptime, String b, String c, String d, String e, int f,  String h,String g, String i, int j, int k, String l, String m, String n, String o, String p , String q, int r, int s){
    
        this(a,pdate,ptime,b,c,d,e,f,h,g,i,j,k,l);
        g_title=m;
        g_name=n;
        g_nic_pp=o;
        g_relationship=p;
        g_address=q;
        g_landline=r;
        g_mobile=s;
       // p_available=t;
        
    }
    
    
    
    public void insert_new_patient() throws ClassNotFoundException{
        Statement st=null;
        ResultSet rs=null;
        
        try{
            
           Connection conn = MySqlConnect.connectDB();
            st = conn.createStatement();
            
            String q ="insert into patient (`pId`,`pAddDate`,`pAddTime`,`pNameTitle`,`pName`,`pNameWithInitials`,`gender`,`age`,`dob`,`pNicOrPp`,`address`,`landLine`,`mobile`,`nationality`,`gNameTitle`,`gName`,`gNicOrPp`,`gRelationship`,`gAddress`,`gLandLine`,`gMobile`)"
                      +"values('"+pid+"','"+date+"','"+time+"','"+pn_title+"','"+p_name+"','"+p_initials+"','"+gender+"','"+age+"','"+dob+"','"+nic_pp+"','"+p_address+"','"+p_landline+"','"+p_mobile+"','"+p_nationality+"','"+g_title+"','"+g_name+"','"+g_nic_pp+"','"+g_relationship+"','"+g_address+"','"+g_landline+"','"+g_mobile+"')";
        
            st.executeUpdate(q);
            
            JOptionPane.showMessageDialog(null,"New Patient Added!!");
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
       /* catch(ClassNotFoundException ex){
            Logger.getLogger(inPatient_management.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    
    
    }
    
    public void Edit_patient(){
        PreparedStatement pst=null;
        Statement st=null; 
        try{
            Connection conn = MySqlConnect.connectDB();
            st=conn.createStatement();
            
            String q="update patient set  pAddDate='"+date+"', pAddTime='"+time+"', pNameTitle='"+pn_title+"', pName='"+p_name+"', pNameWithInitials='"+p_initials+"', gender='"+gender+"', age='"+age+"', dob='"+dob+"', pNicOrPp='"+nic_pp+"', address='"+p_address+"', landLine='"+p_landline+"', mobile='"+p_mobile+"',nationality='"+p_nationality+"', gNameTitle='"+g_title+"', gName='"+g_name+"', gNicOrPp='"+g_nic_pp+"', gRelationship='"+g_relationship+"', gAddress='"+g_address+"', gLandLine='"+g_landline+"', gMobile='"+g_mobile+"' where pId='"+pid+"' ";
            pst=conn.prepareStatement(q);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data is Updated");
        
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        
        }
    
    
    }
  
    
    
    
}
