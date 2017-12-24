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
public class Manager extends DBConnection {

    /**
     * Creates a new instance of the player class, and creates a connection to
     * the named database
     *
     * @param dbName Service name of database
     */
    public Manager(final String dbName) {
        this.connectDatabase(dbName);
    }

    /**
     * Insert a new Manager record into the database ManagerID of player
     *
     * @param firstname first name of manager
     * @param lastname last name of manager
     * @param teamid id of team manager belongs to
     *
     */
    public void insertRecord(final String firstname, final String lastname, final int teamid) {
        final String insertStmt = "INSERT INTO jfl.managers (ManagerID, FirstName, LastName, TeamID) VALUES (?,?,?,?)";
        try {
            int maxCount;
            try {
                final String countQuery = "SELECT MAX(ManagerID) AS maxCount FROM jfl.managers";
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
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, teamid);

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting manager record:" + sqle.toString());
        }
    }
    
    /**
     * Update a Manager record, given its ID
     *
     * @param id ManagerID of manager record
     * @param firstname first name of manager
     * @param lastname last name of manager
     * @param teamid id of team manager belongs to
     *
     */
    public void updateRecord (int id, final String firstname, final String lastname, final int teamid){
        final String updateStmt = "UPDATE jfl.managers SET FirstName='"+firstname+"', LastName='"+lastname+"', TeamID="+teamid+" WHERE ManagerID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating manager record:" + sqle.toString());
        }
    }
    
     /**
     * delete a Manager record, given its ID
     *
     * @param id ManagerID of manager record being deleted
     *
     */
    public void deleteRecord (int id){
        //set up delete statement using manager id
        final String updateStmt = "DELETE FROM jfl.managers WHERE ManagerID="+id;
        try {
        //prepare the delete statement
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute deletion of manager record
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting manager record:" + sqle.toString());
        }
    }
    
     /**
     * Load all Manager records for a given team(id) into a 2d array
     *
     * @param teamid id of the team which the method loads the managers for
     * @return managerRecords - array holding all the manager records for the given team
     *
     */
    public Object[][] loadTeamManagers(int teamid) {
        int recordCount;
        try {
            //determine number of managers for team
            final String countQuery = "SELECT COUNT(ManagerID) AS managerCount FROM jfl.managers WHERE TeamID=" + teamid;
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("managerCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Manager count:" + sqle.toString());
        }

        Object[][] managerRecords = new Object[recordCount][5];
        int arrayCount = 0;
        try {
            //select all managers which belong to the team
            final String allQuery = "SELECT * FROM jfl.managers WHERE TeamID=" + teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            //load all the managers into the array
            while ((output.next()) && (arrayCount < recordCount)) {
                managerRecords[arrayCount][0] = "Manager";
                managerRecords[arrayCount][1] = output.getString("ManagerID");
                managerRecords[arrayCount][2] = output.getString("FirstName");
                managerRecords[arrayCount][3] = output.getString("LastName");
                managerRecords[arrayCount][4] = "Manager";
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating manager array:" + sqle.toString());
        }
        return managerRecords;
    }
    
    
     /**
     * Load all Manager records into a 2d array
     * 
     * @return managerRecords - array holding all the manager records
     *
     */
    public Object[][] loadAllManagers() {
        int recordCount;
        try {
            //Determine the number of manager records
            final String countQuery = "SELECT COUNT(ManagerID) AS managerCount FROM jfl.managers";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("managerCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Manager count:" + sqle.toString());
        }

        Object[][] managerRecords = new Object[recordCount][6];
        int arrayCount = 0;
        try {
            //Select all of the manager records
            final String allQuery = "SELECT * FROM jfl.managers";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            //Loads all of the managers into the array
            while ((output.next()) && (arrayCount < recordCount)) {
                //Obtains the team name from the teamID
                Team t = new Team("jflDB");
                String teamName = t.getTeamName(output.getInt("TeamID"));
                t.closeConnection();
                    
                //loads the manager record into the array
                managerRecords[arrayCount][0] = "Manager";
                managerRecords[arrayCount][1] = output.getString("ManagerID");
                managerRecords[arrayCount][2] = output.getString("FirstName");
                managerRecords[arrayCount][3] = output.getString("LastName");
                managerRecords[arrayCount][4] = teamName;
                managerRecords[arrayCount][5] = "Manager";
                //Increment to put into next index
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating manager array:" + sqle.toString());
        }
        return managerRecords;
    }

     /**
     * Load a Manager record given their managerID
     *
     * @param id of the manager
     * @return managerRecord - array holding the fields of the selected manager record
     *
     */
    public Object[] loadDetails(int id) {
        Object[] managerRecord = new Object[4];
        try {
            //selects manager record which has the given id
            final String allQuery = "SELECT * FROM jfl.managers WHERE ManagerID=" + id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //loads the fields into the manager array
            managerRecord[0] = output.getString("ManagerID");
            managerRecord[1] = output.getString("FirstName");
            managerRecord[2] = output.getString("LastName");
            managerRecord[3] = output.getString("TeamID");

        } catch (SQLException sqle) {
            System.out.println("Exception when loading manager details:" + sqle.toString());
        }
        return managerRecord;

    }

}
