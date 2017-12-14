/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Game;
import Classes.Team;
import java.util.ArrayList;
import java.util.List;


import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author blao
 */
public class LeagueTableForm extends javax.swing.JFrame {

    /**
     * Creates new form LeagueTableForm
     */
    public LeagueTableForm() {
        initComponents();
        loadLeagueTable();
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
        tableLeagueTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("League Table");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("League Table");

        tableLeagueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team Name", "Played", "Win", "Draw", "Loss", "Goals For", "Goals Against", "Goal Difference", "Points"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableLeagueTable);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(LeagueTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeagueTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeagueTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeagueTableForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeagueTableForm().setVisible(true);
            }
        });
    }
    
    private void loadLeagueTable(){
        DefaultTableModel model =(DefaultTableModel) tableLeagueTable.getModel();
  
        //tableLeagueTable.setAutoCreateRowSorter(true);
      
        model.setRowCount(0);
        
        Team t = new Team("jflDB");
        Object[][] teamRecords = t.loadAllTeams();
        t.closeConnection();
        
        
        
        int teamID;
        String teamName;
        int gamesPlayed = 0;
        int homeGoals = 0;
        int awayGoals = 0;
        int winCount = 0;
        int drawCount = 0;
        int lossCount = 0;
        int goalsFor = 0;
        int goalsAgainst = 0;
        int goalDifference = 0;
        int points = 0;
        for(int i=0;i<teamRecords.length;i++){
            teamID = Integer.parseInt(teamRecords[i][0].toString());
            
            Game g = new Game("jflDB");
            Object[] leagueRecord = g.loadLeagueRecord(teamID);
            g.closeConnection();
            
            teamName = leagueRecord[0].toString();
            gamesPlayed = Integer.parseInt(leagueRecord[1].toString());
            winCount = Integer.parseInt(leagueRecord[2].toString());
            drawCount = Integer.parseInt(leagueRecord[3].toString());
            lossCount = Integer.parseInt(leagueRecord[4].toString());
            goalsFor = Integer.parseInt(leagueRecord[5].toString());
            goalsAgainst = Integer.parseInt(leagueRecord[6].toString());
            goalDifference = Integer.parseInt(leagueRecord[7].toString());
            points = Integer.parseInt(leagueRecord[8].toString());
            
            model.addRow(new Object[]{teamName, gamesPlayed, winCount, drawCount, lossCount, goalsFor, goalsAgainst, goalDifference, points}); 
        }
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableLeagueTable.getModel());
         tableLeagueTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
 
        int pointIndex = 8;
        sortKeys.add(new RowSorter.SortKey(pointIndex, SortOrder.DESCENDING));
        
         int gdIndex = 7;
        sortKeys.add(new RowSorter.SortKey(gdIndex, SortOrder.DESCENDING));
 
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableLeagueTable;
    // End of variables declaration//GEN-END:variables
}
