/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asir
 */
public class Stats extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public Stats() {
        initComponents();
        txt();
        showGlrnk();
        showYCR();
        showLCR();
    }

    public void txt() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlserver://localhost:1433;databaseName=FTMSProject;selectMethod=cursor", "sa", "123456");
            String query="select top 1 Player.PName as 'PlayerName',count(Goal_ID) as 'Total Goals' from Goal join Player on Goal.Player_ID=Player.Player_ID group by Goal.Player_ID,Player.PName order by count(Goal_ID) desc;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i=0;
            if(rs.next()){
                TXT1.setText(rs.getString("PlayerName"));
            }
            
        } catch (Exception e) {
        };
    }

    public ArrayList<GoalRanking> fxtr() {
        ArrayList<GoalRanking> fx = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlserver://localhost:1433;databaseName=FTMSProject;selectMethod=cursor", "sa", "123456");
            String query1 = "select Goal.Player_ID as 'Player_ID',Player.PName as 'Player_Name',(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',count(Goal_ID) as 'Total_Goals' from Goal inner join Player on Goal.Player_ID=Player.Player_ID group by Goal.Player_ID,Player.TeamId,Player.PName order by count(Goal_ID) desc;";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query1);

            GoalRanking g;
            while (rs.next()) {
                g = new GoalRanking(rs.getInt("Player_ID"), rs.getString("Player_Name"), rs.getString("Team"), rs.getInt("Total_Goals"));
                fx.add(g);
            }

        } catch (Exception ex) {
            Logger.getLogger(InputDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fx;
    }

    public void showGlrnk() {
        ArrayList<GoalRanking> list = fxtr();
        DefaultTableModel model = (DefaultTableModel) GOALRnk.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getPlayerID();
            row[1] = list.get(i).getPlayerName();
            row[2] = list.get(i).getTeamName();
            row[3] = list.get(i).getTotalGoals();
            model.addRow(row);
        }
    }

    public ArrayList<CardRanking> YC() {
        ArrayList<CardRanking> fx = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlserver://localhost:1433;databaseName=FTMSProject;selectMethod=cursor", "sa", "123456");
            String query2 = "select Cards.Player_ID,Player.PName,(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',count(Card_ID) as 'Total_Cards' from Cards inner join Player on Cards.Player_ID=Player.Player_ID where Card_Type='Yellow' group by Cards.Player_ID,Player.TeamId,Player.PName order by count(Card_ID) desc;";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query2);

            CardRanking y;
            while (rs.next()) {
                y = new CardRanking(rs.getInt("Player_ID"), rs.getString("PName"), rs.getString("Team"), rs.getInt("Total_Cards"));
                fx.add(y);
            }

        } catch (Exception ex) {
            Logger.getLogger(InputDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fx;
    }

    public void showYCR() {
        ArrayList<CardRanking> list = YC();
        DefaultTableModel model = (DefaultTableModel) YCRnk.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getPlayerID();
            row[1] = list.get(i).getPlayerName();
            row[2] = list.get(i).getTeamName();
            row[3] = list.get(i).getTotalCards();
            model.addRow(row);
        }
    }

    public ArrayList<CardRanking> RC() {
        ArrayList<CardRanking> fx = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlserver://localhost:1433;databaseName=FTMSProject;selectMethod=cursor", "sa", "123456");
            String query3 = "select Cards.Player_ID,Player.PName,(Select TeamName from Team where Team.TeamId=Player.TeamId) as 'Team',count(Card_ID) as 'Total_Cards' from Cards inner join Player on Cards.Player_ID=Player.Player_ID where Card_Type='Red' group by Cards.Player_ID,Player.TeamId,Player.PName order by count(Card_ID) desc;";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query3);

            CardRanking r;
            while (rs.next()) {
                r = new CardRanking(rs.getInt("Player_ID"), rs.getString("PName"), rs.getString("Team"), rs.getInt("Total_Cards"));
                fx.add(r);
            }

        } catch (Exception ex) {
            Logger.getLogger(InputDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fx;
    }

    public void showLCR() {
        ArrayList<CardRanking> list = RC();
        DefaultTableModel model = (DefaultTableModel) RRnk.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getPlayerID();
            row[1] = list.get(i).getPlayerName();
            row[2] = list.get(i).getTeamName();
            row[3] = list.get(i).getTotalCards();
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        YCRnk = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        GOALRnk = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        RRnk = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TXT1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Main Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel7.setText("Highest Goals");

        YCRnk.setBackground(new java.awt.Color(153, 255, 51));
        YCRnk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player ID", "Player Name", "Team", "Total Cards"
            }
        ));
        jScrollPane1.setViewportView(YCRnk);

        GOALRnk.setBackground(new java.awt.Color(153, 255, 51));
        GOALRnk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player ID", "Player Name", "Team", "Total Goals"
            }
        ));
        jScrollPane2.setViewportView(GOALRnk);

        RRnk.setBackground(new java.awt.Color(153, 255, 51));
        RRnk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player ID", "Player Name", "Team", "Total Cards"
            }
        ));
        jScrollPane3.setViewportView(RRnk);

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setText("YELLOW CARDS");

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel9.setText("RED CARDS");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel10.setText("GOALS");

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel11.setText("TOURNAMENT STATS");

        TXT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TXT1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new MainMenuFTMS().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TXT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT1ActionPerformed

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
            java.util.logging.Logger.getLogger(Stats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stats().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GOALRnk;
    private javax.swing.JTable RRnk;
    private javax.swing.JTextField TXT1;
    private javax.swing.JTable YCRnk;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
