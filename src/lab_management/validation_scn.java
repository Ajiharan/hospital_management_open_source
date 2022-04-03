/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab_management;

import javax.swing.JOptionPane;

/**
 *
 * @author Matha
 */
public class validation_scn {
    String TOScn="";
    String pay="";
    String sDate="";
    String rPN="";
    String PId="";
    
    
    public validation_scn(String a,String b, String c, String d,String e){
     TOScn=a;
     pay=b;
     sDate=c;
     rPN=d;
     PId=e;
    }
    
    public boolean ScanValidation(){
    boolean y;
   
    
    if(PId.equals("")){
        JOptionPane.showMessageDialog(null,"Please enter Patient Name");
         y=false;
    }
    else if(TOScn.equals("Select")){
        JOptionPane.showMessageDialog(null,"Please select the scan type");
        y=false;
    }
    
    else if(sDate.isEmpty()){
        JOptionPane.showMessageDialog(null,"Please select the Scanned date.");
        y=false;
    }
    else if(rPN.isEmpty()){
        JOptionPane.showMessageDialog(null, "Enter Physician name");
        y=false;
    }
  
    else
        y=true;
    
    return y;
}
    
    
    
}
