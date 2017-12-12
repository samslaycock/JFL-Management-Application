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

    
}
