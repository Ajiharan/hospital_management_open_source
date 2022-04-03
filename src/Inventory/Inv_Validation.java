/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;


import javax.swing.JOptionPane;

/**
 *
 * @author Matha
 */
public class Inv_Validation {
    String itmNameAtInv=""; 
    String CategryAtInv;
    String qtyAtInv;
    String unitPurchsCostAtInv="";
    String pdate;
        


public Inv_Validation(String a,String b,String c,String d,String e){
    itmNameAtInv=a;
    CategryAtInv=b;
    qtyAtInv=c;
    unitPurchsCostAtInv=d;
    pdate=e;

}

public boolean inValidation(){
    boolean y;
   
    
    if(itmNameAtInv.equals("")){
        JOptionPane.showMessageDialog(null,"Please enter Item Name");
         y=false;
    }
    else if(CategryAtInv.equals("Select")){
        JOptionPane.showMessageDialog(null,"Please select the inventory type");
        y=false;
    }
    
    else if(qtyAtInv.isEmpty()){
        JOptionPane.showMessageDialog(null,"Enter quantity.");
        y=false;
    }
    else if(unitPurchsCostAtInv.isEmpty()){
        JOptionPane.showMessageDialog(null, "Enter Unit purchase cost");
        y=false;
    }
    else if(pdate.isEmpty()){
        JOptionPane.showMessageDialog(null,"Please select the purchased date.");
        y=false;
    }
  
    else
        y=true;
    
    return y;
}

}