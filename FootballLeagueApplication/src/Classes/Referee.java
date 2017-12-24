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
public class Referee extends DBConnection {

    /**
     * Creates a new instance of the player class, and creates a connection to
     * the named database
     *
     * @param dbName Service name of database
     */
    public Referee(final String dbName) {
        this.connectDatabase(dbName);
    }

    /**
     * Insert a new Referee record into the database
     * 
     *
     * @param firstname first name of referee
     * @param lastname last name of referee
     *
     *
     */
    public void insertRecord(final String firstname, final String lastname) {
        //Set up insert statement for creation of referee record
        final String insertStmt = "INSERT INTO jfl.referees (RefereeID, FirstName, LastName) VALUES (?,?,?)";
        try {
            int maxCount;
            try {
                //determine the max value of refereeID in table
                final String countQuery = "SELECT MAX(RefereeID) AS maxCount FROM jfl.referees";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                maxCount = output.getInt("recordCount");
            } catch (SQLException sqle) {
                maxCount = 0;
            }
            //increment the max value to create a unique refereeID
            maxCount++;

            //prepare the insertion statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            pstmt.setInt(1, maxCount);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);

            //execute the prepared statement to add the referee record to the datbase
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting referee record:" + sqle.toString());
        }
    }
    
      /**
     * Update a referee record, given the refereeID of the record
     *
     * @param id RefereeID of record being updated
     * @param firstname first name of referee
     * @param lastname last name of referee
     *
     */
    public void updateRecord (int id, final String firstname, final String lastname){
        //set up update statement, putting the parameters into it
        final String updateStmt = "UPDATE jfl.referees SET FirstName='"+firstname+"', LastName='"+lastname+"' WHERE RefereeID="+id;
        try {
        //prepare the update statement for execution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute statement to update the existing referee record
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating referee record:" + sqle.toString());
        }
    }
    
     /**
     * Delete a referee record, given it's RefereeID
     *
     * @param id RefereeID of record being deleted
     *
     */
    public void deleteRecord (int id){
        
        //set up the delete statement, with the passed id
        final String updateStmt = "DELETE FROM jfl.referees WHERE RefereeID="+id;
        try {
        //prepare the delete statement for execution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute prepared statement, deleting the referee record
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting referee record:" + sqle.toString());
        }
    }

    /**
     * Loads all referee records into an array
     *
     * @return refereeRecords - 2d array holding all of the referee records and their fields
     *
     */
    public Object[][] loadAllReferees() {
        int recordCount;
        try {
            //determines the number of referee records in referee table
            final String countQuery = "SELECT COUNT(RefereeID) AS refereeCount FROM jfl.referees";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("refereeCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Referee count:" + sqle.toString());
        }

        Object[][] refereeRecords = new Object[recordCount][6];
        int arrayCount = 0;
        try {
            //selects all of the referees
            final String allQuery = "SELECT * FROM jfl.referees";
            this.setQuery(allQuery);
            this.runQuery();

            ResultSet output = this.getResultSet();
            //loads the referee records into the array
            while ((output.next()) && (arrayCount < recordCount)) {
                refereeRecords[arrayCount][0] = "Referee";
                refereeRecords[arrayCount][1] = output.getString("RefereeID");
                refereeRecords[arrayCount][2] = output.getString("FirstName");
                refereeRecords[arrayCount][3] = output.getString("LastName");
                refereeRecords[arrayCount][4] = "JFL Official";
                refereeRecords[arrayCount][5] = "Referee";
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating referee array:" + sqle.toString());
        }
        return refereeRecords;
    }

     /**
     * Load the details of the record into an array
     *
     * @param id RefereeID of the record which data is being retrieved from
     * 
     * @return refereeRecord - 2d array holding all of the referee records and their fields
     *
     */
    public Object[] loadDetails(int id) {
        //
        Object[] refereeRecord = new Object[3];
        try {
            //select referee record with the given ID
            final String allQuery = "SELECT * FROM jfl.referees WHERE RefereeID=" + id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //load the record details into the array
            refereeRecord[0] = output.getString("RefereeID");
            refereeRecord[1] = output.getString("FirstName");
            refereeRecord[2] = output.getString("LastName");

        } catch (SQLException sqle) {
            System.out.println("Exception when loading referee details:" + sqle.toString());
        }
        return refereeRecord;
    }

}
