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
    
    public void updateRecord (int id, final String firstname, final String lastname, final int teamid){
        final String updateStmt = "UPDATE jfl.managers SET FirstName='"+firstname+"', LastName='"+lastname+"', TeamID="+teamid+" WHERE ManagerID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating manager record:" + sqle.toString());
        }
    }
    
    public void deleteRecord (int id){
        final String updateStmt = "DELETE FROM jfl.managers WHERE ManagerID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting manager record:" + sqle.toString());
        }
    }

    public Object[][] loadTeamManagers(int teamid) {
        int recordCount;
        try {
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

        Object[][] playerRecords = new Object[recordCount][5];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.managers WHERE TeamID=" + teamid;
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

    public Object[][] loadAllManagers() {
        int recordCount;
        try {
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
            final String allQuery = "SELECT * FROM jfl.managers";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                Team t = new Team("jflDB");
                String teamName = t.getTeamName(output.getInt("TeamID"));
                t.closeConnection();

                managerRecords[arrayCount][0] = "Manager";
                managerRecords[arrayCount][1] = output.getString("ManagerID");
                managerRecords[arrayCount][2] = output.getString("FirstName");
                managerRecords[arrayCount][3] = output.getString("LastName");
                managerRecords[arrayCount][4] = teamName;
                managerRecords[arrayCount][5] = "Manager";
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating manager array:" + sqle.toString());
        }
        return managerRecords;
    }

    public Object[] loadDetails(int id) {
        Object[] managerRecord = new Object[4];
        try {
            final String allQuery = "SELECT * FROM jfl.managers WHERE ManagerID=" + id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();

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
