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
     * Insert a new Manager record into the database
     * ManagerID of player
     * @param firstname first name of manager
     * @param lastname last name of manager
     * @param teamid id of team manager belongs to
     * 
     */
    public void insertRecord (final String firstname, final String lastname, final int teamid) {
        final String insertStmt = "INSERT INTO jfl.managers (ManagerID, FirstName, LastName, TeamID) VALUES (?,?,?,?)";
        try {
            int recordCount;
            try {
                final String countQuery = "SELECT COUNT(ManagerID) AS recordCount FROM jfl.managers";
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
            System.out.println("Exception when inserting manager record:" + sqle.toString());
        }
    }
    
    public Object[][] loadTeamManagers(int teamid){
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(ManagerID) AS managerCount FROM jfl.managers WHERE TeamID="+teamid;
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("managerCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Manager count:" + sqle.toString());
        }
        
        Object[][] playerRecords = new Object[recordCount][5];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.managers WHERE TeamID="+teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                playerRecords[arrayCount][0] = "Manager";
                playerRecords[arrayCount][1] = output.getString("ManagerID");
                playerRecords[arrayCount][2] = output.getString("FirstName");
                playerRecords[arrayCount][3] = output.getString("LastName");
                playerRecords[arrayCount][4] = "Manager";
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating manager array:" + sqle.toString());
        }
        return playerRecords;
    }
    
    
    
    
    
}
