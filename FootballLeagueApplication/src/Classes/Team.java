/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Connection.DBConnection;
import java.sql.*;

/**
 *
 * @author blao
 */
public class Team extends DBConnection {

    /**
     * Creates a new instance of the team class, and creates a connection to the
     * named database
     *
     * @param dbName Service name of database
     */
    public Team(final String dbName) {
        this.connectDatabase(dbName);
    }

    /**
     * Insert a new Player record into the database
     *
     * teamID id of created team
     *
     * @param teamName name of created team CaptainID name of the teams captain
     * (initialised to nothing here)
     */
    public void insertRecord(final String teamName) {
        final String insertStmt = "INSERT INTO jfl.teams (TeamID, TeamName, CaptainID) VALUES (?,?,?)";
        try {
            int maxCount;
            try {
                final String countQuery = "SELECT Max(TeamID) AS teamCount FROM jfl.teams";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                maxCount = output.getInt("maxCount");
            } catch (SQLException sqle) {
                maxCount = 0;
            }
            maxCount++;

            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            pstmt.setInt(1, maxCount);
            pstmt.setString(2, teamName);
            pstmt.setInt(3, -1);

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting team record:" + sqle.toString());
        }
    }
    
    public void updateRecord (int id, final String firstname, final int captainID){
        final String updateStmt = "UPDATE jfl.teams SET TeamName='"+firstname+"',  CaptainID="+captainID+" WHERE TeamID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating team record:" + sqle.toString());
        }
    }
    
    public Object[] loadDetails(final int teamID){
        Object[] teamRecord = new Object[3];
        try {
            final String allQuery = "SELECT * FROM jfl.teams WHERE TeamID="+teamID;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            teamRecord[0] = output.getString("TeamID");
            teamRecord[1] = output.getString("TeamName");
            teamRecord[2] = output.getString("CaptainID");
            
         } catch (SQLException sqle) {
            System.out.println("Exception when loading team details:" + sqle.toString());
        }   
         return teamRecord;
    }
    
    public Object[][] loadAllTeams(){
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(TeamID) AS teamCount FROM jfl.teams";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("teamCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting team count:" + sqle.toString());
        }
        
        Object[][] teamRecords = new Object[recordCount][4];
        int arrayCount = 0;
        int captainID = 0;
        String captainName = "No Captain";
        try {
            final String allQuery = "SELECT * FROM jfl.teams";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                captainID = Integer.parseInt(output.getString("CaptainID"));
                if(captainID > 0){
                    Player p = new Player("jflDB");
                    Object[] playerRecord = p.loadDetails(captainID);
                    p.closeConnection();
                    captainName = playerRecord[1] + " " + playerRecord[2];
                }else{
                    captainName = "No Captain";
                }

                teamRecords[arrayCount][0] = output.getString("TeamID");
                teamRecords[arrayCount][1] = output.getString("TeamName");
                teamRecords[arrayCount][2] = captainID;
                teamRecords[arrayCount][3] = captainName;
                
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating team array:" + sqle.toString());
        }
        return teamRecords;
    }

    public String[] populateTeamComboBox() {
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(TeamID) AS teamCount FROM jfl.teams";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("teamCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Team count:" + sqle.toString());
        }

        String[] comboArray = new String[recordCount];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.teams";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                comboArray[arrayCount] = output.getString("TeamName");
                arrayCount++;
            }

        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating combo box:" + sqle.toString());

        }
        return comboArray;
    }
    
    public String getTeamName(int teamid){
         String teamName = "";
        try {
            final String allQuery = "SELECT TeamName FROM jfl.teams WHERE TeamID="+teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            teamName = output.getString("TeamName");
           
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting obtaining Team Name:" + sqle.toString());

        }
        
        return teamName;  
    }

}
