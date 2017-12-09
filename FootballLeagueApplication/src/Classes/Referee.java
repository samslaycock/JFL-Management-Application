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
     * Insert a new Referee record into the database refereeID record id of
     * referee
     *
     * @param firstname first name of referee
     * @param lastname last name of referee
     *
     *
     */
    public void insertRecord(final String firstname, final String lastname) {
        final String insertStmt = "INSERT INTO jfl.referees (RefereeID, FirstName, LastName) VALUES (?,?,?)";
        try {
            int recordCount;
            try {
                final String countQuery = "SELECT COUNT(RefereeID) AS recordCount FROM jfl.referees";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                recordCount = output.getInt("recordCount");
            } catch (SQLException sqle) {
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
    
    public void updateRecord (int id, final String firstname, final String lastname){
        final String updateStmt = "UPDATE jfl.referees SET FirstName='"+firstname+"', LastName='"+lastname+"' WHERE RefereeID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating referee record:" + sqle.toString());
        }
    }

    public Object[][] loadAllReferees() {
        int recordCount;
        try {
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
            final String allQuery = "SELECT * FROM jfl.referees";
            this.setQuery(allQuery);
            this.runQuery();

            ResultSet output = this.getResultSet();
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

    public Object[] loadDetails(int id) {
        Object[] refereeRecord = new Object[3];
        try {
            final String allQuery = "SELECT * FROM jfl.referees WHERE RefereeID=" + id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();

            refereeRecord[0] = output.getString("RefereeID");
            refereeRecord[1] = output.getString("FirstName");
            refereeRecord[2] = output.getString("LastName");

        } catch (SQLException sqle) {
            System.out.println("Exception when loading referee details:" + sqle.toString());
        }
        return refereeRecord;
    }

}
