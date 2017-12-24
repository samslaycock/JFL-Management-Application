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
     * Insert a new Team record into the database
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
                //determine the current max teamID in the team table
                final String countQuery = "SELECT Max(TeamID) AS maxCount FROM jfl.teams";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                maxCount = output.getInt("maxCount");
            } catch (SQLException sqle) {
                maxCount = 0;
            }
            //increment to create a new id for team being inserted
            maxCount++;

            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
                
           
            //insert fields into the statement
            pstmt.setInt(1, maxCount);
            pstmt.setString(2, teamName);
            pstmt.setInt(3, -1);
            
            
            //execute insert statement, inserting the record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting team record:" + sqle.toString());
        }
    }
    
    
    /**
     * Update a Team record
     *
     * @param id TeamID of the team being updated
     * @param teamName team name of the team being updated
     * @param captainID captainID of the team being updated
     * 
     */
    public void updateRecord(int id, final String teamName, final int captainID) {
        //set up the update statement using the passed parameters
        final String updateStmt = "UPDATE jfl.teams SET TeamName='" + teamName + "',  CaptainID=" + captainID + " WHERE TeamID=" + id;
        try {
            //prepare update statement to update team record
            PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
            //execute update statement, amending the team record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating team record:" + sqle.toString());
        }
    }

    /**
     * Delete Team record, given the id
     * 
     * @param id TeamID of the team being deleted
     */
    public void deleteRecord(int id) {
        //Set up delete statement to dete record with passed id
        String deleteStmt = "DELETE FROM jfl.teams WHERE TeamID=" + id;
        try {
            //prepare delete statement
            PreparedStatement pstmt = getConnection().prepareStatement(deleteStmt);
            //execute delete statement to delete the team record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting team record:" + sqle.toString());
        }
        
        //clears team id of deleted team from players in it
        deleteStmt = "UPDATE jfl.players SET TeamID=0 WHERE TeamID=" + id;
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(deleteStmt);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating player record:" + sqle.toString());
        }
        
        //clears team id of deleted team from coaches in it
        deleteStmt = "UPDATE jfl.coaches SET TeamID=0 WHERE TeamID=" + id;
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(deleteStmt);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating coach record:" + sqle.toString());
        }

        //clears team id of deleted team from managers in it
        deleteStmt = "UPDATE jfl.managers SET TeamID=0 WHERE TeamID=" + id;
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(deleteStmt);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating manager record:" + sqle.toString());
        }

    }

    /**
     *  Loads the data of a team record, given the team's id
     * 
     * @param teamID TeamID of the team
     * 
     * @return teamRecord - array holding the details of the team record
     */
    public Object[] loadDetails(final int teamID) {
        Object[] teamRecord = new Object[3];
        try {
            //select the team record from the table where team id matches the given teamID
            final String allQuery = "SELECT * FROM jfl.teams WHERE TeamID=" + teamID;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();

            //load the team record data into an array
            teamRecord[0] = output.getString("TeamID");
            teamRecord[1] = output.getString("TeamName");
            teamRecord[2] = output.getString("CaptainID");

        } catch (SQLException sqle) {
            System.out.println("Exception when loading team details:" + sqle.toString());
        }
        return teamRecord;
    }
    
    
    /**
     * Loads all of the team records into a 2d array
     * 
     * @return teamRecords
     */
    public Object[][] loadAllTeams() {
        int recordCount;
        try {
            //Determine how many team records are in the table
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
            //selects all of the team records
            final String allQuery = "SELECT * FROM jfl.teams";
            this.setQuery(allQuery);
            this.runQuery();
            
            //load the team record into an array
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                captainID = Integer.parseInt(output.getString("CaptainID"));
                if (captainID > 0) {
                    Player p = new Player("jflDB");
                    Object[] playerRecord = p.loadDetails(captainID);
                    p.closeConnection();
                    captainName = playerRecord[1] + " " + playerRecord[2];
                } else {
                    captainName = "No Captain";
                }

                teamRecords[arrayCount][0] = output.getString("TeamID");

                if (Integer.parseInt(teamRecords[arrayCount][0].toString()) > 0) {
                    teamRecords[arrayCount][1] = output.getString("TeamName");
                } else {
                    teamRecords[arrayCount][1] = "No Team";
                }

                teamRecords[arrayCount][2] = captainID;
                teamRecords[arrayCount][3] = captainName;

                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating team array:" + sqle.toString());
        }
        return teamRecords;
    }

     /**
     * Used to populate combo box with team names
     * 
     * @return comboArray - array with all the team names to be loaded into 
     * a combo box
     */
    public String[] populateTeamComboBox() {
        int recordCount;
        try {
            //Determine the number of teams
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

        //Determines the team's name
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
            System.out.println("Exception when  populating combo box:" + sqle.toString());

        }
        return comboArray;
    }

    
     /**
     * Gets the team name of a given teamID
     * 
     * @param teamid TeamID of the team
     * 
     * @return teamName name of the team 
     */
    public String getTeamName(int teamid) {
        
        //Determines the team name
        String teamName = "No Team";
        //If teamID is greater than 0
        if (teamid > 0) {
            try {
                //Select team name from record which matches with the ID
                final String allQuery = "SELECT TeamName FROM jfl.teams WHERE TeamID=" + teamid;
                this.setQuery(allQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                teamName = output.getString("TeamName");

            } catch (SQLException sqle) {
                System.out.println("Exception when  obtaining Team Name:" + sqle.toString());

            }
        }

        return teamName;
    }

}
