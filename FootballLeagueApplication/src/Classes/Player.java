
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
     * 
     * @param firstname first name of player
     * @param lastname last name of player
     * @param teamid id of team player belongs to
     * @param teamposition football position of player
     */
    public void insertRecord (final String firstname, final String lastname, final int teamid, final String teamposition) {
        //set up insert statement to create a new player record
        final String insertStmt = "INSERT INTO jfl.players (PlayerID, FirstName, LastName, TeamID, TeamPosition) VALUES (?,?,?,?,?)";
        try {
            int maxCount;
            try {
                //determine current max playerID in the table players
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
            //increment the max to create a new max for the playerRecord being created
            maxCount++;
            
            //prepare insert statement
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
            
            //insert fields for player record into the statement
            pstmt.setInt(1, maxCount);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setInt(4, teamid);
            pstmt.setString(5, teamposition);
           
            //execute statement to add the new player record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting player record:" + sqle.toString());
        }
    }
    
    /**
     * Update a player record given its id and fields to update
     * 
     * 
     * @param id playerID of the player record being updated
     * @param firstname first name of player
     * @param lastname last name of player
     * @param teamid id of team player belongs to
     * @param teamposition football position of player
     */
    public void updateRecord (int id, final String firstname, final String lastname, final int teamid, final String teamposition){
        //Set up update statement, inserting the fields passed to the method
        final String updateStmt = "UPDATE jfl.players SET FirstName='"+firstname+"', LastName='"+lastname+"', TeamID="+teamid+", TeamPosition='"+teamposition+"' WHERE PlayerID="+id;
        try {
        //prepare the update statement for execution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the update statement, amending the record in the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating player record:" + sqle.toString());
        }
    }
    
    /**
     * Deletes a player record from the database, given it's playerID
     * 
     * @param id playerID of the record
     */
    public void deleteRecord (int id){
        //set up the delete statement, inserting the id of the player
        final String updateStmt = "DELETE FROM jfl.players WHERE PlayerID="+id;
        try {
        //prepare the delete statement for exeution
        PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
        //execute the delete statement, removing the player record from the table
        pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting player record:" + sqle.toString());
        }
    }
        
    /**
     * Load all of the players of a team, given the ID
     * 
     * @param teamid TeamID of the team we're loading players of
     * 
     * @return playerRecords - 2d array holding all of the player records in a given team
     * 
     */
    public Object[][] loadTeamPlayers(int teamid){
        int recordCount;
        try {
            //Determine number of players which are part of the team
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
            //selects all the players which are part of the team
            final String allQuery = "SELECT * FROM jfl.players WHERE TeamID="+teamid;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            //load all of the team players into an array
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

    
     /**
     * Load all of the players from the player table
     * 
     * @return playerRecords - 2d array holding all of the player records
     * 
     */
    public Object[][] loadAllPlayers(){
        int recordCount;
        try {
            //Determine number of players in the table
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
            //select all players in the players table
            final String allQuery = "SELECT * FROM jfl.players";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            
            //load all player records into the array
            while ((output.next()) && (arrayCount < recordCount)) {
                // get the team name which the player belongs to, using the team id
                Team t = new Team("jflDB");
                String teamName = t.getTeamName(output.getInt("TeamID"));
                t.closeConnection();
                
                //put all of the player record fields into the array
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
    
     /**
     * Loads the detail of a specific player, given their playerID
     * 
     * @param id PlayerID of the player details are being retrieved for
     * 
     * @return playerRecord - an array holding all the data from the player record
     * 
     */
     public Object[] loadDetails(int id){
        Object[] playerRecord = new Object[5];
        try {
            //select a player record, given the id related to that record
            final String allQuery = "SELECT * FROM jfl.players WHERE PlayerID="+id;
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            
            //put all of the record's field contents into an array
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
    
    
    

