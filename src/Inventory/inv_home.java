
package Inventory;
import Home.Home_Main;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class inv_home extends javax.swing.JFrame {
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    DefaultTableModel model;

    public inv_home() {
        initComponents();
         model =(DefaultTableModel)i_details.getModel();
        conn = MySQLConn.ConnectDB();
        
        AutoScanid();
        inventory_details();  
        CategryAtInv.setSelectedItem("Select");
    }
    
    private void setinventoryDetails(){
        SIIDAtInv.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 0)));
        itmNameAtInv.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 1)));
        CategryAtInv.setSelectedItem(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 2)));
        itmDescriAtInv.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 3)));
        qtyAtInv.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 4)));
        unitPurchsCostAtInv.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 5)));
        ttlAmntAtInv.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 6)));
        pdate.setText(String.valueOf(model.getValueAt(i_details.getSelectedRow(), 7)));    
    }
    
    public void insert_inventory_details() throws ClassNotFoundException {
        Inv_Validation Iv=new Inv_Validation(itmNameAtInv.getText(),CategryAtInv.getSelectedItem().toString(),qtyAtInv.getText(),unitPurchsCostAtInv.getText(),pdate.getText());
        boolean valid=Iv.inValidation();
        
        if(valid){
            inventory inv;
            inv = new inventory(SIIDAtInv.getText(),itmNameAtInv.getText(),CategryAtInv.getSelectedItem().toString(),itmDescriAtInv.getText(),
                Integer.parseInt(qtyAtInv.getText()), Double.parseDouble(unitPurchsCostAtInv.getText()), Double.parseDouble(ttlAmntAtInv.getText()), ((JTextField)purchsdDateAtInv1.getDateEditor().getUiComponent()).getText());
        
            inv.insert_inventory_details();
            clear_inv_details();
        }
    }
    
    
    public void total(){
        double qty=Integer.parseInt(qtyAtInv.getText());
        int price = Integer.parseInt(unitPurchsCostAtInv.getText());
        
        double puno=qty*price;
        
        ttlAmntAtInv.setText(String.valueOf(puno));    
    }
    
    private void edit_inv_details(){
        inventory inve;
       inve = new inventory(SIIDAtInv.getText(),itmNameAtInv.getText(),CategryAtInv.getSelectedItem().toString(),itmDescriAtInv.getText(),
                Integer.parseInt(qtyAtInv.getText()), Double.parseDouble(unitPurchsCostAtInv.getText()), Double.parseDouble(ttlAmntAtInv.getText()), pdate.getText());
        
       inve.edit_inv_details();
    }
    
    
    private void inventory_details(){
        try{
        String sql="select * from inventory2";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        i_details.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void search_inv(){
        String s = search.getText();
        search.setText(null);
        try{
            Connection conn = MySQLConn.ConnectDB();
            String q = "select * from inventory2 where IId= ?";
            
            pst=conn.prepareStatement(q);
            pst.setString(1,s);
            rs=pst.executeQuery();
            
            if(rs.next()){
              String id = rs.getString("IID");
              SIIDAtInv.setText(id);
                
              String a = rs.getString("IName");
              itmNameAtInv.setText(a);
              
              String b = rs.getString("Cat");
              CategryAtInv.setSelectedItem(b);
              
              String c = rs.getString("IDescription");
              itmDescriAtInv.setText(c);
              
              String d = rs.getString("Quan");
              qtyAtInv.setText(d);  
              
              String e = rs.getString("UpurchaseCost");
              unitPurchsCostAtInv.setText(e);
              
              String f = rs.getString("TAmount");
              ttlAmntAtInv.setText(f);
              
              String g = rs.getString("PuDate");
              pdate.setText(g);
                   
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    
    }
    
            
    private void clear_inv_details(){
        itmNameAtInv.setText(null);
        CategryAtInv.setSelectedItem("Select");
        itmDescriAtInv.setText(null);
        qtyAtInv.setText(null);
        unitPurchsCostAtInv.setText(null);
        ttlAmntAtInv.setText(null);
        //SIIDAtInv.setText("");
        purchsdDateAtInv1.setDate(null);
        search.setText("");
        valq.setText(null);
        valIN.setText(null);
        valC.setText(null);
        valPD.setText(null);
        
    }
    
    private void delete_inv_details(){
        String value1= SIIDAtInv.getText();
        if(value1!=null){
                  String sql="delete from inventory2 where IID=?";
                    try{

                        pst=conn.prepareStatement(sql);
                        pst.setString(1, value1);
                        pst.executeUpdate();  
                        JOptionPane.showMessageDialog(null,"Data is deleteted Successfullly!");
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e);
                    }
       }
        else
        {
             JOptionPane.showMessageDialog(null,"Select the inventory ID");
        }
    }
    
    private void AutoScanid(){
        try{
        Connection conn = MySQLConn.ConnectDB();
        String p="SELECT IID FROM inventory2 ORDER BY IID DESC LIMIT 1";
        pst=conn.prepareStatement(p);
        rs=pst.executeQuery();
            if(rs.next()){
                  String p1=rs.getString("IID");
                  String[] parts = p1.split("(?<=-)");
                    String part1 = parts[0];
                    String part2 = parts[1];                 
                    int id = Integer.parseInt(part2);
                    int id2=id+1;
                    String id3=Integer.toString(id2);
                    String newid=part1+id3;
                    
                  SIIDAtInv.setText(newid);
                
            }        
        }
        catch(Exception e){
        
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        qtyAtInv = new javax.swing.JTextField();
        search = new javax.swing.JTextField();
        ttlAmntAtInv = new javax.swing.JTextField();
        itmNameAtInv = new javax.swing.JTextField();
        unitPurchsCostAtInv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itmDescriAtInv = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        CategryAtInv = new javax.swing.JComboBox<>();
        purchsdDateAtInv1 = new com.toedter.calendar.JDateChooser();
        SIIDAtInv = new javax.swing.JLabel();
        pdate = new javax.swing.JTextField();
        valq = new javax.swing.JLabel();
        valIN = new javax.swing.JLabel();
        valC = new javax.swing.JLabel();
        valPD = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        i_details = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yu Mincho", 1, 36)); // NOI18N
        jLabel1.setText("INVENTORY MANAGEMENT");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Item Name");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Category");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Quantity");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Unit purchase cost");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Item ID");

        qtyAtInv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qtyAtInvFocusLost(evt);
            }
        });
        qtyAtInv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                qtyAtInvMouseReleased(evt);
            }
        });
        qtyAtInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyAtInvActionPerformed(evt);
            }
        });

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        itmNameAtInv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                itmNameAtInvFocusLost(evt);
            }
        });

        unitPurchsCostAtInv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                unitPurchsCostAtInvKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Item description");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Total amount");

        itmDescriAtInv.setColumns(20);
        itmDescriAtInv.setRows(5);
        jScrollPane2.setViewportView(itmDescriAtInv);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Purchased Date");

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        CategryAtInv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Machinery", "Furniture" }));
        CategryAtInv.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CategryAtInvFocusLost(evt);
            }
        });

        purchsdDateAtInv1.setDateFormatString("yyyy-MM-dd");
        purchsdDateAtInv1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                purchsdDateAtInv1PropertyChange(evt);
            }
        });

        SIIDAtInv.setText("ItmID");

        pdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pdateFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addGap(92, 92, 92)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(qtyAtInv, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(ttlAmntAtInv)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(valq, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(unitPurchsCostAtInv)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(itmNameAtInv)
                                                    .addComponent(CategryAtInv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(99, 99, 99)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel10)))
                                            .addComponent(jScrollPane2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(valPD, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(pdate)
                                                    .addComponent(SIIDAtInv, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(purchsdDateAtInv1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(53, 53, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(jLabel3)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(valIN, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(valC, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {itmNameAtInv, jScrollPane2, qtyAtInv, search, ttlAmntAtInv, unitPurchsCostAtInv});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itmNameAtInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(SIIDAtInv)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valIN, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CategryAtInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(purchsdDateAtInv1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(pdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4)
                .addComponent(valC, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(valPD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qtyAtInv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitPurchsCostAtInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(ttlAmntAtInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        i_details.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item_ID", "Item_Name", "Description", "Quantity", "Total_amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        i_details.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                i_detailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(i_details);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 102, 102));
        jButton6.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("CLEAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 102, 102));
        jButton5.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("HOME");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addGap(48, 48, 48))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton6});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addGap(30, 30, 30)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        jTabbedPane1.addTab("Inventory", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        clear_inv_details();
        AutoScanid();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        delete_inv_details();
        inventory_details();
        clear_inv_details();
        AutoScanid();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        edit_inv_details();
        inventory_details();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            insert_inventory_details();
            inventory_details();
            clear_inv_details();
            AutoScanid();

        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(inv_home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void i_detailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_i_detailsMouseClicked
        setinventoryDetails();
    }//GEN-LAST:event_i_detailsMouseClicked

    private void pdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pdateFocusLost
        String jj=pdate.getText();
        if(jj.equals("")){
            valPD.setText("Please select the purchased date.");
            valPD.setForeground(Color.red);
        }
        else{
            valPD.setText("");
        }

    }//GEN-LAST:event_pdateFocusLost

    private void purchsdDateAtInv1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_purchsdDateAtInv1PropertyChange
        // TODO add your handling code here:
        pdate.setText(((JTextField)purchsdDateAtInv1.getDateEditor().getUiComponent()).getText());
    }//GEN-LAST:event_purchsdDateAtInv1PropertyChange

    private void CategryAtInvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CategryAtInvFocusLost
        String ss = CategryAtInv.getSelectedItem().toString();
        if(ss.equals("Select")){
            valC.setText("Please Select the category!!");
            valC.setForeground(Color.red);
        }
        else
        valC.setText("");
    }//GEN-LAST:event_CategryAtInvFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        search_inv();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void unitPurchsCostAtInvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitPurchsCostAtInvKeyReleased

        total();
    }//GEN-LAST:event_unitPurchsCostAtInvKeyReleased

    private void itmNameAtInvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itmNameAtInvFocusLost
        String ss= itmNameAtInv.getText();
        if(ss.equals("")){
            valIN.setText("Please enter Item name!!");
            valIN.setForeground(Color.red);
        }
        else{
            for(int i=0; i < ss.length(); i++){
                char a = ss.charAt(i);

                if(Character.isDigit(a)){
                    valIN.setText("Dont enter numbers in Item Name.");
                    valIN.setForeground(Color.red);
                }
                else
                valIN.setText("");
            }
        }

    }//GEN-LAST:event_itmNameAtInvFocusLost

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void qtyAtInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyAtInvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyAtInvActionPerformed

    private void qtyAtInvMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyAtInvMouseReleased

    }//GEN-LAST:event_qtyAtInvMouseReleased

    private void qtyAtInvFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qtyAtInvFocusLost
        // TODO add your handling code here:
        String s = qtyAtInv.getText();
        if(s.equals("")){
            valq.setText("Please enter Quantity!");
            valq.setForeground(Color.red);
        }
        else{
            for(int i=0; i < s.length(); i++){
                char a = s.charAt(i);

                if(Character.isDigit(a)){

                    valq.setText("");
                    valq.setForeground(Color.red);

                }
                else{
                    valq.setText("You can enter number only.");

                }
            }
        }
    }//GEN-LAST:event_qtyAtInvFocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      Home_Main home=new Home_Main();
      this.setVisible(false);
      home.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inv_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inv_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inv_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inv_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inv_home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategryAtInv;
    private javax.swing.JLabel SIIDAtInv;
    private javax.swing.JTable i_details;
    private javax.swing.JTextArea itmDescriAtInv;
    private javax.swing.JTextField itmNameAtInv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField pdate;
    private com.toedter.calendar.JDateChooser purchsdDateAtInv1;
    private javax.swing.JTextField qtyAtInv;
    private javax.swing.JTextField search;
    private javax.swing.JTextField ttlAmntAtInv;
    private javax.swing.JTextField unitPurchsCostAtInv;
    private javax.swing.JLabel valC;
    private javax.swing.JLabel valIN;
    private javax.swing.JLabel valPD;
    private javax.swing.JLabel valq;
    // End of variables declaration//GEN-END:variables
}
