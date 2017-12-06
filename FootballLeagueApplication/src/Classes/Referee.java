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
     * refereeID        record id of referee
     * @param firstname first name of referee
     * @param lastname last name of referee
     * 
     * 
     */
    public void insertRecord (final String firstname, final String lastname) {
        final String insertStmt = "INSERT INTO jfl.referees (CoachID, FirstName, LastName) VALUES (?,?,?)";
        try {
            int recordCount;
            try {
                final String countQuery = "SELECT COUNT(RefereeID) AS recordCount FROM jfl.referees";
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
            

           
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting referee record:" + sqle.toString());
        }
    }

    
    
}
