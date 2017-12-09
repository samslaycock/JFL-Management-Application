
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
            int maxCount;
            try {
                final String countQuery = "SELECT MAX(PlayerID) AS maxCount FROM jfl.players";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                maxCount = output.getInt("maxCount");
                }
            catch(SQLException sqle) {
                maxCount = 0;
            }
            maxCount++;
            
            
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            pstmt.setInt(1, maxCount);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, teamid);
            pstmt.setString(5, teamposition);
           
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting player record:" + sqle.toString());
        }
    }
    
    public void updateRecord (int id, final String firstname, final String lastname, final int teamid, final String teamposition){
        final String updateStmt = "UPDATE jfl.players SET FirstName='"+firstname+"', LastName='"+lastname+"', TeamID="+teamid+", TeamPosition='"+teamposition+"' WHERE PlayerID="+id;
        try {
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating player record:" + sqle.toString());
        }
    }
    
    
    public Object[][] loadTeamPlayers(int teamid){
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(PlayerID) AS playerCount FROM jfl.players WHERE TeamID="+teamid;
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("playerCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Player count:" + sqle.toString());
        }
        
        Object[][] playerRecords = new Object[recordCount][5];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.players WHERE TeamID="+teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                playerRecords[arrayCount][0] = "Player";
                playerRecords[arrayCount][1] = output.getString("PlayerID");
                playerRecords[arrayCount][2] = output.getString("FirstName");
                playerRecords[arrayCount][3] = output.getString("LastName");
                playerRecords[arrayCount][4] = output.getString("TeamPosition");
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating player array:" + sqle.toString());
        }
        return playerRecords;
    }

    public Object[][] loadAllPlayers(){
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(PlayerID) AS playerCount FROM jfl.players";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("playerCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Player count:" + sqle.toString());
        }
        
        Object[][] playerRecords = new Object[recordCount][6];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.players";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                Team t = new Team("jflDB");
                String teamName = t.getTeamName(output.getInt("TeamID"));
                t.closeConnection();

                playerRecords[arrayCount][0] = "Player";
                playerRecords[arrayCount][1] = output.getString("PlayerID");
                playerRecords[arrayCount][2] = output.getString("FirstName");
                playerRecords[arrayCount][3] = output.getString("LastName");
                playerRecords[arrayCount][4] = teamName;
                playerRecords[arrayCount][5] = output.getString("TeamPosition");
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating player array:" + sqle.toString());
        }
        return playerRecords;
    }
    
     public Object[] loadDetails(int id){
        Object[] playerRecord = new Object[5];
        try {
            final String allQuery = "SELECT * FROM jfl.players WHERE PlayerID="+id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            playerRecord[0] = output.getString("PlayerID");
            playerRecord[1] = output.getString("FirstName");
            playerRecord[2] = output.getString("LastName");
            playerRecord[3] = output.getString("TeamID");
            playerRecord[4] = output.getString("TeamPosition");
         } catch (SQLException sqle) {
            System.out.println("Exception when loading player details:" + sqle.toString());
        }   
         return playerRecord;
    }
     
     
     
     
}
    
    
    

