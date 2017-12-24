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
     * @param firstname first name of coach
     * @param lastname last name of coach
     * @param teamid id of team coach belongs to
     * 
     */
    public void insertRecord (final String firstname, final String lastname, final int teamid) {
        //Set up record insertion statement
        final String insertStmt = "INSERT INTO jfl.coaches (CoachID, FirstName, LastName, TeamID) VALUES (?,?,?,?)";
        //Determine new id for a Coach Record
        try {
            int maxCount;
            try {
                //Get maximum ID from Coach table
                final String countQuery = "SELECT MAX(CoachID) AS maxCount FROM jfl.coaches";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                maxCount = output.getInt("maxCount");
                }
            catch(SQLException sqle) {
                maxCount = 0;
            }
            //increment the maxCount, meaning that the newID will always be unique
            maxCount++;
            
            //prepare insert statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            //insert values into insert statement
            pstmt.setInt(1, maxCount);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, teamid);

           //execute statement, adding team record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting coach record:" + sqle.toString());
        }
    }
    
    /**
     * Update a new Coach record into the database
     * @param id numeric id of coach
     * @param firstname first name of coach
     * @param lastname last name of coach
     * @param teamid id of team coach belongs to
     * 
     */
    public void updateRecord (int id, final String firstname, final String lastname, final int teamid){
        //Set up record update statement, with given values from update form
        final String updateStmt = "UPDATE jfl.coaches SET FirstName='"+firstname+"', LastName='"+lastname+"', TeamID="+teamid+" WHERE CoachID="+id;
        try {
        //Prepare update statement
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        
        //Execute update statement
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating coach record:" + sqle.toString());
        }
    }
    
    /**
     * Delete Coach record from database using coach ID
     * @param id numeric id of coach
     */
    public void deleteRecord (int id){
        //Set up delete statement, with given id from delete form
        final String updateStmt = "DELETE FROM jfl.coaches WHERE CoachID="+id;
        try {
        //Prepare delete statement
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //Execute delete statement
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting coach record:" + sqle.toString());
        }
    }
    
    /**
     * Loads all of the Coach records (all fields) for a given teamID into a 2d array
     * @param teamid numeric id of team
     * 
     * @return coachRecords - 2d array of all coach records for a specific team 
     */
    public Object[][] loadTeamCoaches(int teamid){
        int recordCount;
        try {
            //Set up load statement, given the teamID
            final String countQuery = "SELECT COUNT(CoachID) AS coachCount FROM jfl.coaches WHERE TeamID="+teamid;
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            //get count of Coach Records for specific team
            recordCount = output.getInt("coachCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Coach count:" + sqle.toString());
        }
        //Declares coachRecord 2d array
        Object[][] coachRecords = new Object[recordCount][5];
        int arrayCount = 0;
        try {
            //Set up load statement, loads coaches which match with the given teamID
            final String allQuery = "SELECT * FROM jfl.coaches WHERE TeamID="+teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            //Insert coachRecords into the 2d array;
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
    
     /**
     * Loads all of the Coach records (all fields) from the database into a 2d array
     * 
     * @return coachRecords - 2d array of all coach records
     */
    public Object[][] loadAllCoaches(){
            int recordCount;
        try {
            //determine how many coachRecords are in coach table
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
            //Select all coach records in coach table
            final String allQuery = "SELECT * FROM jfl.coaches";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                //get Team name of teamID which coach belongs to
                Team t = new Team("jflDB");
                String teamName = t.getTeamName(output.getInt("TeamID"));
                t.closeConnection();
                
                //insert coach details into coachRecords array
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
    
    /**
     * Loads a Coach record (all fields) for a specific coach ID
     * @param id numeric id of coach
     * 
     * @return coachRecord - array, holding fields of a coachRecord with the given ID
     */
    public Object[] loadDetails(int id){
        Object[] coachRecord = new Object[4];
        try {
            //Select coach details for a given coach ID
            final String allQuery = "SELECT * FROM jfl.coaches WHERE CoachID="+id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            //put details into the coachRecord array
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
