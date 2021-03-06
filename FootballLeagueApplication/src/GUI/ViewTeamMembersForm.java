/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Coach;
import Classes.Manager;
import Classes.Player;
import Classes.Team;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author blao
 */
public class ViewTeamMembersForm extends javax.swing.JFrame {

    /**
     * Creates new form ViewTeamMembersForm
     */
    public ViewTeamMembersForm() {
        initComponents();
        cmbTeamPopulate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTeamMembers = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        cmbTeamNames = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Team Members");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("View Team Members");

        tableTeamMembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "ID", "First Name", "Last Name", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTeamMembers);

        cmbTeamNames.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbTeamNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTeamNamesActionPerformed(evt);
            }
        });

        jLabel2.setText("Team Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTeamNames, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTeamNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTeamNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTeamNamesActionPerformed
        // TODO add your handling code here:
        int selectedIndex = cmbTeamNames.getSelectedIndex();
        if (selectedIndex > 0) {
            selectedIndex = selectedIndex - 1;

            int selectedTeamID;
            Team t = new Team("jflDB");
            Object[][] teamRecords = t.loadAllTeams();
            t.closeConnection();

            selectedTeamID = Integer.parseInt(teamRecords[selectedIndex][0].toString());

            this.loadTeamMembers(selectedTeamID);
        }
    }//GEN-LAST:event_cmbTeamNamesActionPerformed

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
            java.util.logging.Logger.getLogger(ViewTeamMembersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTeamMembersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTeamMembersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTeamMembersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTeamMembersForm().setVisible(true);
            }
        });
    }
    
    
    /**
     * Method which populates team combo-boxes with the names of the teams from the teams table
     */
    private void cmbTeamPopulate(){
        Team t = new Team("jflDB");
        String[] teams = t.populateTeamComboBox();
        
       for( int i=0; i<teams.length ; i++){
           cmbTeamNames.addItem(teams[i]);
        }
       
        t.closeConnection();
        
    }
    
    /**
     * Method which loads all members of a team (anyone with the associated team id in their record),
     * into the table, adding each one as an individual row
     * 
     * @param teamid TeamID of the team we're loading members of
     */
    private void loadTeamMembers(int teamid){
        DefaultTableModel model =(DefaultTableModel) tableTeamMembers.getModel();
       
        model.setRowCount(0);
       
        String type;
        String id;
        String firstName;
        String lastName;
        String position;
      
        Manager m = new Manager("jflDB");
        Object[][] managers = m.loadTeamManagers(teamid);
        for (int i=0; i< managers.length;i++){
            type = managers[i][0].toString();
            id = managers[i][1].toString();
            firstName = managers[i][2].toString();
            lastName = managers[i][3].toString();
            position = managers[i][4].toString();
          model.addRow(new Object[]{type, id, firstName,lastName,position});
        }
        m.closeConnection();
        
        Coach c = new Coach("jflDB");
        Object[][] coaches = c.loadTeamCoaches(teamid);
        for (int i=0; i< coaches.length;i++){
            type = coaches[i][0].toString();
            id = coaches[i][1].toString();
            firstName = coaches[i][2].toString();
            lastName = coaches[i][3].toString();
            position = coaches[i][4].toString();
          model.addRow(new Object[]{type, id, firstName,lastName,position});
        }
        c.closeConnection();
        
        Player p = new Player("jflDB");
        Object[][] players = p.loadTeamPlayers(teamid);
        for (int i=0; i< players.length;i++){
            type = players[i][0].toString();
            id = players[i][1].toString();
            firstName = players[i][2].toString();
            lastName = players[i][3].toString();
            position = players[i][4].toString();
          model.addRow(new Object[]{type, id, firstName,lastName,position});
        }
        p.closeConnection();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbTeamNames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tableTeamMembers;
    // End of variables declaration//GEN-END:variables
}
