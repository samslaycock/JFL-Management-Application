/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.*;
import javax.swing.JOptionPane;

/**
 *
 * @author blao
 */
public class AddPersonForm extends javax.swing.JFrame {

    /**
     * Creates new form AddPersonForm
     */
    public AddPersonForm() {
        initComponents();
        displayElements();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbPersonType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        cmbTeam = new javax.swing.JComboBox<>();
        lblTeam = new javax.swing.JLabel();
        cmbPosition = new javax.swing.JComboBox<>();
        lblPosition = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        buttonAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Person");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Add Person");

        jLabel2.setText("Type:");

        jLabel3.setText("First Name:");

        cmbPersonType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coach", "Manager", "Player", "Referee" }));
        cmbPersonType.setSelectedIndex(-1);
        cmbPersonType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPersonTypeActionPerformed(evt);
            }
        });

        jLabel4.setText("Last Name:");

        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cmbTeam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblTeam.setText("Team:");

        cmbPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Attacking Midfielder", "Center-back", "Central Midfielder", "Defending Midfielder", "Full-back", "Goalkeeper", "Second Striker", "Striker", "Sweeper", "Wing-back", "Wing Midfielder", " " }));
        cmbPosition.setSelectedIndex(-1);

        lblPosition.setText("Position:");

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
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
                            .addComponent(txtLastName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPosition)
                            .addComponent(lblTeam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPosition, 0, 153, Short.MAX_VALUE)
                            .addComponent(cmbTeam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAdd)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE)
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
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(buttonAdd)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void cmbPersonTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPersonTypeActionPerformed
        // TODO add your handling code here:
        this.displayElements();
      
    }//GEN-LAST:event_cmbPersonTypeActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        // TODO add your handling code here:
        int selectedType;
        String firstName;
        String lastName;
        int teamID;
        String position;
        
        selectedType = cmbPersonType.getSelectedIndex();
        firstName = txtFirstName.getText();
        lastName = txtLastName.getText();
        teamID = cmbTeam.getSelectedIndex();
        position = String.valueOf(cmbPosition.getSelectedItem());
        
        switch (selectedType){
            case 0: Coach c = new Coach("jflDB");
                    c.insertRecord(firstName, lastName, teamID);
                    c.closeConnection();
                    this.recordAdded();
                break;
            case 1: Manager m = new Manager("jflDB");
                    m.insertRecord(firstName, lastName, teamID);
                    m.closeConnection();
                     this.recordAdded();
                    break;
            case 2: Player p = new Player("jflDB");
                    p.insertRecord(firstName, lastName, teamID, position);
                    p.closeConnection();
                     this.recordAdded();
                    break; 
            case 3: Referee r = new Referee("jflDB");
                    r.insertRecord(firstName, lastName);
                    r.closeConnection();
                     this.recordAdded(); 
                    break; 
        }  
        
    }//GEN-LAST:event_buttonAddActionPerformed

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
            java.util.logging.Logger.getLogger(AddPersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPersonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPersonForm().setVisible(true);
                
               
            }
        });
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
    
    private void cmbTeamPopulate(){
        Team t = new Team("jflDB");
        String[] teams = t.populateTeamComboBox();
        
       for( int i=0; i<teams.length ; i++){
           cmbTeam.addItem(teams[i]);
        }
       
        t.closeConnection();
        
    }
    
    private void recordAdded(){
        JOptionPane.showMessageDialog(this, "Record successfully added.");
        cmbPersonType.setSelectedIndex(-1);
        cmbPosition.setSelectedIndex(-1);
        cmbTeam.setSelectedIndex(-1);
        txtFirstName.setText("");
        txtLastName.setText("");
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
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
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
