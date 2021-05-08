
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asir
 */
public class Display extends javax.swing.JFrame {

    /**
     * Creates new form Display
     */
    public Display() {
        initComponents();
        /*
        ConnectMSSQL obj = new ConnectMSSQL();
        obj.connectDB();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager
                    .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
            java.sql.Statement statement = con.createStatement();
            String query1 = "SELECT * FROM Customer";
            ResultSet rs = statement
                    .executeQuery(query1);

            ResultSetMetaData rsmta = rs.getMetaData();
            int columns = rsmta.getColumnCount();
            DefaultTableModel dtm = new DefaultTableModel();
            Vector columnsName = new Vector();
            Vector dataRow = new Vector();
            
            for(int i=0; i<columns ;i++){
                columnsName.addElement(rsmta.getColumnName(i));
            }
            dtm.setColumnIdentifiers(columnsName);
            
            while(rs.next()){
                dataRow = new Vector();
                for(int j=0; j<columns ;j++){
                    dataRow.addElement(rs.getString(j));                   
                }
                dtm.addRow(dataRow);
            }
            display_Table.setModel(dtm);
        } catch (Exception e) {

        }
        */
    }
    

    public ArrayList<User> userList() {
        ArrayList<User> usersList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager
                    .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
            java.sql.Statement statement = con.createStatement();
            String query1 = "SELECT * FROM Customer";
            ResultSet rs = statement
                    .executeQuery(query1);

            User user;
            while (rs.next()) {
                user = new User(rs.getInt("sno"), rs.getString("Fname"), rs.getString("Lname"), rs.getString("address"), rs.getString("city"), rs.getString("country"));
                usersList.add(user);
            }
        } catch (Exception e) {

        }
        return usersList;
    }


    public void show_user(){
        ArrayList<User> list= userList();
        DefaultTableModel model = (DefaultTableModel) display_Table.getModel();
        Object[] row = new Object[6];
        for(int i=0; i<list.size() ;i++){
            row[0] = list.get(i).getsno();
            row[1] = list.get(i).getFname();
            row[2] = list.get(i).getLname();
            row[3] = list.get(i).getAddress();
            row[4] = list.get(i).getCity();
            row[5] = list.get(i).getCountry();
            model.addRow(row);
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

        OKBTN = new javax.swing.JButton();
        TF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        display_Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OKBTN.setText("OK");
        OKBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKBTNActionPerformed(evt);
            }
        });

        TF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFActionPerformed(evt);
            }
        });

        display_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "FirstName", "LastName", "CustomerAddress", "City", "Country"
            }
        ));
        display_Table.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(display_Table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(TF, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TF, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKBTNActionPerformed
        // TODO add your handling code here:
      
        ConnectMSSQL obj = new ConnectMSSQL();
        obj.connectDB();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
                    .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT FirstName FROM Customer");
            String str[] = new String[6];
            int i = 0;
            while (resultSet.next()) {
                System.out.println("Customer NAME:"
                        + resultSet.getString("FirstName"));
                str[i++] = resultSet.getString("FirstName");
            }
            TF.setText(str[1]);

        } catch (Exception e) {

        }

    }//GEN-LAST:event_OKBTNActionPerformed

    private void TFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFActionPerformed

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
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKBTN;
    private javax.swing.JTextField TF;
    private javax.swing.JTable display_Table;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
