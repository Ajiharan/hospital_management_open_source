/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab_management;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Matha
 */
public class NewTest {
    
    String ID="";
    String name="";
    Double fee;
    
    public NewTest(String a, String b, Double c){
        ID = a;
        name = b;
        fee = c;
    }
    
    public void insertNewTestDetails(){
        Statement st=null;
        ResultSet rs=null;
    }
    
}
