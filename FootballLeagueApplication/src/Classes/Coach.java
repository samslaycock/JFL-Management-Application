/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Connection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author blao
 */
public class Coach extends DBConnection {
    
     /**
     * Creates a new instance of the player class, and creates a connection to
     * the named database
     *
     * @param dbName Service name of database
     */
    public Coach(final String dbName) {
        this.connectDatabase(dbName);
    }

    /**
     * Insert a new Coach record into the database
     * coachID of player
     * @param firstname first name of coach
     * @param lastname last name of coach
     * @param teamid id of team coach belongs to
     * 
     */
    public void insertRecord (final String firstname, final String lastname, final int teamid) {
        final String insertStmt = "INSERT INTO jfl.coaches (CoachID, FirstName, LastName, TeamID) VALUES (?,?,?,?)";
        try {
            int recordCount;
            try {
                final String countQuery = "SELECT COUNT(CoachID) AS recordCount FROM jfl.coaches";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                recordCount = output.getInt("recordCount");
                }
            catch(SQLException sqle) {
                recordCount = 0;
            }
            recordCount++;
            
            
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            pstmt.setInt(1, recordCount);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, teamid);

           
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting coach record:" + sqle.toString());
        }
    }
    
    public void updateRecord (int id, final String firstname, final String lastname, final int teamid){
        final String updateStmt = "UPDATE jfl.coaches SET FirstName='"+firstname+"', LastName='"+lastname+"', TeamID="+teamid+" WHERE CoachID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating coach record:" + sqle.toString());
        }
    }
    
    public Object[][] loadTeamCoaches(int teamid){
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(CoachID) AS coachCount FROM jfl.coaches WHERE TeamID="+teamid;
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("coachCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Coach count:" + sqle.toString());
        }
        
        Object[][] coachRecords = new Object[recordCount][5];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.coaches WHERE TeamID="+teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                coachRecords[arrayCount][0] = "Coach";
                coachRecords[arrayCount][1] = output.getString("CoachID");
                coachRecords[arrayCount][2] = output.getString("FirstName");
                coachRecords[arrayCount][3] = output.getString("LastName");
                coachRecords[arrayCount][4] = "Coach";
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating coach array:" + sqle.toString());
        }
        return coachRecords;

    }
    
    
    public Object[][] loadAllCoaches(){
            int recordCount;
        try {
            final String countQuery = "SELECT COUNT(CoachID) AS coachCount FROM jfl.coaches";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("coachCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Coach count:" + sqle.toString());
        }
        
        Object[][] coachRecords = new Object[recordCount][6];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.coaches";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                Team t = new Team("jflDB");
                String teamName = t.getTeamName(output.getInt("TeamID"));
                t.closeConnection();

                coachRecords[arrayCount][0] = "Coach";
                coachRecords[arrayCount][1] = output.getString("CoachID");
                coachRecords[arrayCount][2] = output.getString("FirstName");
                coachRecords[arrayCount][3] = output.getString("LastName");
                coachRecords[arrayCount][4] = teamName;
                coachRecords[arrayCount][5] = "Coach";
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating coach array:" + sqle.toString());
        }
        return coachRecords;
    }
    
    public Object[] loadDetails(int id){
        Object[] coachRecord = new Object[4];
        try {
            final String allQuery = "SELECT * FROM jfl.coaches WHERE CoachID="+id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            coachRecord[0] = output.getString("CoachID");
            coachRecord[1] = output.getString("FirstName");
            coachRecord[2] = output.getString("LastName");
            coachRecord[3] = output.getString("TeamID");
 
         } catch (SQLException sqle) {
            System.out.println("Exception when loading coach details:" + sqle.toString());
        }    
            
        
            
       return coachRecord;
    }
    
}
