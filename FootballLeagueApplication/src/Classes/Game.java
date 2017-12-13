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
public class Game extends DBConnection {
    
     /**
     * Creates a new instance of the game class, and creates a connection to
     * the named database
     *
     * @param dbName Service name of database
     */
    public Game(final String dbName) {
        this.connectDatabase(dbName);
    }
    
    public void insertRecord(int gameSet, int gameID, int homeID, int awayID, int homeGoals, int awayGoals, int refereeID, String date){
       final String insertStmt = "INSERT INTO jfl.games (GameSet, GameID, HomeID, AwayID, HomeGoals, AwayGoals, RefereeID, Date) VALUES (?,?,?,?,?,?,?,?)";
        try {
 

            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);

            pstmt.setInt(1, gameSet);
            pstmt.setInt(2, gameID);
            pstmt.setInt(3, homeID);
            pstmt.setInt(4, awayID);
            pstmt.setInt(5, homeGoals);
            pstmt.setInt(6, awayGoals);
            pstmt.setInt(7, refereeID);
            pstmt.setString(8, date);

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting game record:" + sqle.toString());
        }
    }
    
    public Object[][] loadGames() {
        int recordCount;
        try {
            final String countQuery = "SELECT COUNT(GameSet) AS gameCount FROM jfl.games";
            this.setQuery(countQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            output.next();
            recordCount = output.getInt("gameCount");
        } catch (SQLException sqle) {
            recordCount = 0;
            System.out.println("Exception when getting Game count:" + sqle.toString());
        }

        Object[][] gameRecords = new Object[recordCount][7];
        int arrayCount = 0;
        try {
            final String allQuery = "SELECT * FROM jfl.games";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                Team t = new Team("jflDB");
                String homeTeamName = t.getTeamName(output.getInt("HomeID"));
                String awayTeamName = t.getTeamName(output.getInt("AwayID"));
                t.closeConnection();
                
                String refereeName;
                String firstName;
                String lastName;
                Referee r = new Referee("jflDB");
                Object[] refereeRecord = r.loadDetails(output.getInt("RefereeID"));
                firstName = refereeRecord[1].toString();
                lastName = refereeRecord[2].toString();
                refereeName = firstName +" "+ lastName;

                gameRecords[arrayCount][0] = output.getInt("GameSet");
                gameRecords[arrayCount][1] = homeTeamName;
                gameRecords[arrayCount][2] = awayTeamName;
                gameRecords[arrayCount][3] = output.getInt("HomeGoals");
                gameRecords[arrayCount][4] = output.getInt("AwayGoals");
                gameRecords[arrayCount][5] = refereeName;
                gameRecords[arrayCount][6] = output.getString("Date");
                arrayCount++;
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting populating game array:" + sqle.toString());
        }
        return gameRecords;
    }
        
    public int getGameSet(){
        int gameSet;
            try {
                final String countQuery = "SELECT MAX(GameSet) AS maxCount FROM jfl.games";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                gameSet = output.getInt("maxCount");
            } catch (SQLException sqle) {
                gameSet = 0;
            }
            gameSet++;
            return gameSet;
    }
    
    public Object[][] loadDetails(int gameSet) {
        Object[][] gameSetRecord = new Object[2][8];
        try {
            final String game1Query = "SELECT * FROM jfl.games WHERE GameID=1 AND GameSet=" + gameSet;
            this.setQuery(game1Query);
            this.runQuery();
            ResultSet output1 = this.getResultSet();
            output1.next();

            gameSetRecord[0][0] = output1.getInt("GameSet");
            gameSetRecord[0][1] = output1.getInt("GameID");
            gameSetRecord[0][2] = output1.getInt("HomeID");
            gameSetRecord[0][3] = output1.getInt("AwayID");
            gameSetRecord[0][4] = output1.getInt("HomeGoals");
            gameSetRecord[0][5] = output1.getInt("AwayGoals");
            gameSetRecord[0][6] = output1.getInt("RefereeID");
            gameSetRecord[0][7] = output1.getString("Date");
            
            final String game2Query = "SELECT * FROM jfl.games WHERE GameID=2 AND GameSet=" + gameSet;
            this.setQuery(game2Query);
            this.runQuery();
            ResultSet output2 = this.getResultSet();
            output2.next();

            gameSetRecord[1][0] = output2.getInt("GameSet");
            gameSetRecord[1][1] = output2.getInt("GameID");
            gameSetRecord[1][2] = output2.getInt("HomeID");
            gameSetRecord[1][3] = output2.getInt("AwayID");
            gameSetRecord[1][4] = output2.getInt("HomeGoals");
            gameSetRecord[1][5] = output2.getInt("AwayGoals");
            gameSetRecord[1][6] = output2.getInt("RefereeID");
            gameSetRecord[1][7] = output2.getString("Date");

        } catch (SQLException sqle) {
            System.out.println("Exception when loading game details:" + sqle.toString());
        }
        return gameSetRecord;
    }

    
}
