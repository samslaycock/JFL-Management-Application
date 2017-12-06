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
            }
            recordCount++;

            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            pstmt.setInt(1, recordCount);
            pstmt.setString(2, teamName);
            pstmt.setInt(3, -1);

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting team record:" + sqle.toString());
        }
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

}
