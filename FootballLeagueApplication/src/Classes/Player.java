
package Classes;

import Connection.DBConnection;
import java.sql.*;

/**
 *
 * @author samslaycock
 */
public class Player extends DBConnection {

    /**
     * Creates a new instance of the player class, and creates a connection to
     * the named database
     *
     * @param dbName Service name of database
     */
    public Player(final String dbName) {
        this.connectDatabase(dbName);
    }

    /**
     * Insert a new Player record into the database
     * PlayerID of player
     * @param firstname first name of player
     * @param lastname last name of player
     * @param teamid id of team player belongs to
     * @param teamposition football position of player
     */
    public void insertRecord (final String firstname, final String lastname, final int teamid, final String teamposition) {
        final String insertStmt = "INSERT INTO jfl.players (PlayerID, FirstName, LastName, TeamID, TeamPosition) VALUES (?,?,?,?,?)";
        try {
            int recordCount;
            try {
                final String countQuery = "SELECT COUNT(PlayerID) AS playerCount FROM jfl.players";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                recordCount = output.getInt("playerCount");
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
            pstmt.setString(5, teamposition);
           
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting player record:" + sqle.toString());
        }
    }

}
