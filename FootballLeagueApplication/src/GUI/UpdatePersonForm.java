/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Coach;
import Classes.Manager;
import Classes.Player;
import Classes.Referee;
import Classes.Team;
import javax.swing.JOptionPane;

/**
 *
 * @author blao
 */
public class UpdatePersonForm extends javax.swing.JFrame {

    int personID;

    /**
     * Creates new form UpdatePersonForm
     *
     * @param type type of person being updated (player, coach, manager,
     * referee)
     * @param id id number of person being updated
     */
    public UpdatePersonForm(String type, int id) {
        initComponents();
        displayElements();
        cmbTeamPopulate();
        personID = id;
        loadDetails(type, personID);

    }

    public UpdatePersonForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cmbPersonType = new javax.swing.JComboBox<>();
        buttonUpdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        cmbTeam = new javax.swing.JComboBox<>();
        lblTeam = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbPosition = new javax.swing.JComboBox<>();
        buttonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Person");

        jLabel2.setText("Type:");

        lblPosition.setText("Position:");

        jLabel3.setText("First Name:");

        cmbPersonType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coach", "Manager", "Player", "Referee" }));
        cmbPersonType.setEnabled(false);
        cmbPersonType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPersonTypeActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        jLabel4.setText("Surname:");

        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        txtSurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSurnameActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cmbTeam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblTeam.setText("Team:");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Update Person");

        cmbPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Attacking Midfielder", "Center-back", "Central Midfielder", "Defending Midfielder", "Full-back", "Goalkeeper", "Second Striker", "Striker", "Sweeper", "Wing-back", "Wing Midfielder", " " }));

        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPersonType, javax.swing.GroupLayout.Alignment.LEADING, 0, 145, Short.MAX_VALUE)
                            .addComponent(txtSurname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPosition)
                            .addComponent(lblTeam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPosition, 0, 153, Short.MAX_VALUE)
                            .addComponent(cmbTeam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(buttonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbPersonType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTeam)
                            .addComponent(cmbTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPosition)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPersonTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPersonTypeActionPerformed
        // TODO add your handling code here:
        displayElements();
    }//GEN-LAST:event_cmbPersonTypeActionPerformed

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurnameActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        // TODO add your handling code here:
        int selectedType;
        String firstName;
        String lastName;
        int selectedTeamIndex;
        int teamID;
        String position;

        selectedType = cmbPersonType.getSelectedIndex();
        firstName = txtFirstName.getText();
        lastName = txtSurname.getText();
        
        Team t = new Team("jflDB");
        Object[][] teamRecords = t.loadAllTeams();
        t.closeConnection();
        
        selectedTeamIndex = cmbTeam.getSelectedIndex();
        if(selectedTeamIndex > 0){
        selectedTeamIndex = selectedTeamIndex - 1;
        teamID = Integer.parseInt(teamRecords[selectedTeamIndex][0].toString());
        }else{
        teamID = 0;
        }
        
        position = String.valueOf(cmbPosition.getSelectedItem());
        
      

        switch (selectedType) {
            case 0:
                Coach c = new Coach("jflDB");
                c.updateRecord(personID, firstName, lastName, teamID);
                c.closeConnection();

                break;
            case 1:
                Manager m = new Manager("jflDB");
                m.updateRecord(personID, firstName, lastName, teamID);
                m.closeConnection();

                break;
            case 2:
                Player p = new Player("jflDB");
                p.updateRecord(personID, firstName, lastName, teamID, position);
                p.closeConnection();

                break;
            case 3:
                Referee r = new Referee("jflDB");
                r.updateRecord(personID, firstName, lastName);
                r.closeConnection();

                break;
        }
        
        JOptionPane.showMessageDialog(this, "Record Updated!");
        this.dispose();


    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
        int selectedType;
        selectedType = cmbPersonType.getSelectedIndex();

        int confirmDelete = JOptionPane.showConfirmDialog(this, "Do you want to delete this record?", "Delete Record", JOptionPane.YES_NO_OPTION);
        if (confirmDelete == 0) {
            System.out.println("Confirmed");
            switch (selectedType) {
                case 0:
                    Coach c = new Coach("jflDB");
                    c.deleteRecord(personID);
                    c.closeConnection();

                    break;
                case 1:
                    Manager m = new Manager("jflDB");
                    m.deleteRecord(personID);
                    m.closeConnection();

                    break;
                case 2:
                    Player p = new Player("jflDB");
                    p.deleteRecord(personID);
                    p.closeConnection();

                    break;
                case 3:
                    Referee r = new Referee("jflDB");
                    r.deleteRecord(personID);
                    r.closeConnection();

                    break;
            }
            JOptionPane.showMessageDialog(this, "Record Deleted!");
            this.dispose();
        } else {
            System.out.println("Not Confirmed");
        }


    }//GEN-LAST:event_buttonDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(UpdatePersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePersonForm().setVisible(true);
            }
        });
    }

    private void loadDetails(String type, int id) {

        switch (type) {
            case "Coach":
                Coach c = new Coach("jflDB");
                Object[] coachRecord = c.loadDetails(id);
                c.closeConnection();

                cmbPersonType.setSelectedIndex(0);
                txtFirstName.setText(coachRecord[1].toString());
                txtSurname.setText(coachRecord[2].toString());
                cmbTeam.setSelectedIndex(Integer.parseInt(coachRecord[3].toString()));
                break;
            case "Manager":
                Manager m = new Manager("jflDB");
                Object[] managerRecord = m.loadDetails(id);
                m.closeConnection();

                cmbPersonType.setSelectedIndex(1);
                txtFirstName.setText(managerRecord[1].toString());
                txtSurname.setText(managerRecord[2].toString());
                cmbTeam.setSelectedIndex(Integer.parseInt(managerRecord[3].toString()));
                break;
            case "Player":
                Player p = new Player("jflDB");
                Object[] playerRecord = p.loadDetails(id);
                p.closeConnection();

                cmbPersonType.setSelectedIndex(2);
                txtFirstName.setText(playerRecord[1].toString());
                txtSurname.setText(playerRecord[2].toString());
                cmbTeam.setSelectedIndex(Integer.parseInt(playerRecord[3].toString()));
                cmbPosition.setSelectedItem(playerRecord[4]);
                break;
            case "Referee":
                Referee r = new Referee("jflDB");
                Object[] refereeRecord = r.loadDetails(id);
                r.closeConnection();

                cmbPersonType.setSelectedIndex(3);
                txtFirstName.setText(refereeRecord[1].toString());
                txtSurname.setText(refereeRecord[2].toString());

                break;
        }

    }

    private void displayElements() {
        int selectedIndex;
        selectedIndex = cmbPersonType.getSelectedIndex();
        if ((selectedIndex == -1) || (selectedIndex == 3)) {
            cmbTeam.setVisible(false);
            lblTeam.setVisible(false);
            cmbPosition.setVisible(false);
            lblPosition.setVisible(false);
        } else if ((selectedIndex == 0) || (selectedIndex == 1) || (selectedIndex == 2)) {
            cmbTeam.setVisible(true);
            lblTeam.setVisible(true);
            cmbPosition.setVisible(false);
            lblPosition.setVisible(false);
            if (selectedIndex == 2) {
                cmbPosition.setVisible(true);
                lblPosition.setVisible(true);
            }
        }
    }

    private void cmbTeamPopulate() {
        Team t = new Team("jflDB");
        String[] teams = t.populateTeamComboBox();

        for (int i = 0; i < teams.length; i++) {
            cmbTeam.addItem(teams[i]);
        }

        t.closeConnection();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox<String> cmbPersonType;
    private javax.swing.JComboBox<String> cmbPosition;
    private javax.swing.JComboBox<String> cmbTeam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblTeam;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
}
