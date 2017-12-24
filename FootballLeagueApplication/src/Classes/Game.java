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
    
     /**
     * Insert a game record into the games table
     * 
     *  @param gameSet id used to link game sets (two games, where one is played home for one team, then at home for other)
     *  @param gameID  will be either 1 or 2, meaning its the first or second game in the game set
     *  @param homeID  id of the team who is playing home this game
     *  @param awayID  id of the team who is playing away this game
     *  @param homeGoals number of goals scored by the home team
     *  @param awayGoals number of goals scored by the away team
     *  @param refereeID id of the referee who supervised this game
     *  @param date date which this game was played
     * 
     */
    public void insertRecord(int gameSet, int gameID, int homeID, int awayID, int homeGoals, int awayGoals, int refereeID, String date){
        //Set up game insert statement to add game
        final String insertStmt = "INSERT INTO jfl.games (GameSet, GameID, HomeID, AwayID, HomeGoals, AwayGoals, RefereeID, Date) VALUES (?,?,?,?,?,?,?,?)";
        try {
 
            //prepare insert statement to add game
            PreparedStatement pstmt = getConnection().prepareStatement(insertStmt);
            
            //insert parameters into statement
            pstmt.setInt(1, gameSet);
            pstmt.setInt(2, gameID);
            pstmt.setInt(3, homeID);
            pstmt.setInt(4, awayID);
            pstmt.setInt(5, homeGoals);
            pstmt.setInt(6, awayGoals);
            pstmt.setInt(7, refereeID);
            pstmt.setString(8, date);

            //execute statement to add game record
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when inserting game set record:" + sqle.toString());
        }
    }
    
    /**
     * Update a game record into the games table
     * 
     *  @param gameSet id used to link game sets (two games, where one is played home for one team, then at home for other)
     *  @param gameID  will be either 1 or 2, meaning its the first or second game in the game set
     *  @param homeID  id of the team who is playing home this game
     *  @param awayID  id of the team who is playing away this game
     *  @param homeGoals number of goals scored by the home team
     *  @param awayGoals number of goals scored by the away team
     *  @param refereeID id of the referee who supervised this game
     *  @param date date which this game was played
     * 
     */
    public void updateRecord(int gameSet, int gameID, int homeID, int awayID, int homeGoals, int awayGoals, int refereeID, String date) {
        //Set up update statement to update a game record, putting the parameters into it
        final String updateStmt = "UPDATE jfl.games SET HomeID="+homeID+", AwayID="+awayID+", HomeGoals="+homeGoals+", AwayGoals="+awayGoals+", RefereeID="+refereeID+", Date='"+date+"' WHERE GameID="+gameID+" AND GameSet=" + gameSet;
        try {
            //prepare update game statement for execution
            PreparedStatement pstmt = getConnection().prepareStatement(updateStmt);
            //execute game record update
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when updating team record:" + sqle.toString());
        }
    }
    
    
    /**
     * Delete games which are part of a given gameSet
     * 
     *  @param gameSet id used to link game sets (two games, where one is played home for one team, then at home for other)
     */
    public void deleteRecord(int gameSet){
        //Set up delete statement for a given gameSet
        String deleteStmt = "DELETE FROM jfl.games WHERE GameSet=" + gameSet;
        try {
            //Prepare delete statement for execution
            PreparedStatement pstmt = getConnection().prepareStatement(deleteStmt);
            //Execute statement to delete game
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Exception when deleting game set record:" + sqle.toString());
        }
    }
    
    /**
     * Load all games from the games table into a 2d array
     * 
     * @return gameRecords - 2d array holding all game records
     */
    public Object[][] loadGames() {
        int recordCount;
        try {
            //Determine how many game records are in the game table
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
            //Select all game records in the table
            final String allQuery = "SELECT * FROM jfl.games";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            while ((output.next()) && (arrayCount < recordCount)) {
                
                Team t = new Team("jflDB");
                //get homeTeamName using the HomeID
                String homeTeamName = t.getTeamName(output.getInt("HomeID"));
                //get the awayTeamName using the awayID
                String awayTeamName = t.getTeamName(output.getInt("AwayID"));
                t.closeConnection();
                
                String refereeName;
                String firstName;
                String lastName;
                Referee r = new Referee("jflDB");
                //Get Referee name from referee ID
                Object[] refereeRecord = r.loadDetails(output.getInt("RefereeID"));
                firstName = refereeRecord[1].toString();
                lastName = refereeRecord[2].toString();
                refereeName = firstName +" "+ lastName;
                
                //Put fields into gameRecord array
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
    
    /**
     * Determine gameSet for a new set of game records
     * 
     * @return gameSet - new gameSet for a new set of record
     */
    public int getGameSet(){
        int gameSet;
            try {
                //Determine current max GameSet featuring in the game table
                final String countQuery = "SELECT MAX(GameSet) AS maxCount FROM jfl.games";
                this.setQuery(countQuery);
                this.runQuery();
                ResultSet output = this.getResultSet();
                output.next();
                gameSet = output.getInt("maxCount");
            } catch (SQLException sqle) {
                gameSet = 0;
            }
            //Increment GameSet for a new gameSet to be returned
            gameSet++;
            return gameSet;
    }
    
    
    /**
     *  Load game records of a game set into an array
     * 
     *  @param gameSet id used to link game sets (two games, where one is played home for one team, then at home for other)
     *  @return gameSetRecord - 2d array holding the 2 game records of a given game set
     * 
     */
    public Object[][] loadDetails(int gameSet) {
        Object[][] gameSetRecord = new Object[2][8];
        try {
            //Load first game of a set, given the gameSet id for the two
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
            
            //Load second game of a set, given the gameSet id for the two
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
    
    
    /**
     *  Calculate fields for league table(win/loss/draw count, 
     *  goals for/goals against/goal difference, points) for a given team ID from game records
     *  
     *  @param teamID id of a team we're calculating the league table values for
     *  @return leagueRecord - array containing values to be loaded into LeagueTable form
     * 
     */
    public Object[] loadLeagueRecord(int teamID){
        int recordCount;
        try {
            //Determine amount of game records in the game table
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
        
      
        Object[] leagueRecord = new Object[9];
        //initialise leagueRecord fields
        String teamName = "";
        int homeID;
        int awayID;
        int gamesPlayed = 0;
        int homeGoals = 0;
        int awayGoals = 0;
        int winCount = 0;
        int drawCount = 0;
        int lossCount = 0;
        int goalsFor = 0;
        int goalsAgainst = 0;
        int goalDifference = 0;
        int points = 0;
        
        int arrayCount = 0;
        try {
            //Select all games from the games table
            final String allQuery = "SELECT * FROM jfl.games";
            this.setQuery(allQuery);
            this.runQuery();
            ResultSet output = this.getResultSet();
            //Go through each game, seeing if the given teamID matches the Home Team or Away Team
            //if it does, add to goals for/goals against and win/loss/draw count
            while ((output.next()) && (arrayCount < recordCount)) {
                Team t = new Team("jflDB");
                teamName = t.getTeamName(teamID);
                t.closeConnection();
                
                homeID = output.getInt("HomeID");
                awayID = output.getInt("AwayID");
                homeGoals = output.getInt("HomeGoals");
                awayGoals = output.getInt("AwayGoals");
                
                
                
                if(homeID == teamID){
                    goalsFor = goalsFor + homeGoals;
                    goalsAgainst = goalsAgainst + awayGoals;
                    gamesPlayed++;
                    
                    if(homeGoals > awayGoals){
                        winCount++;
                    }else if(homeGoals == awayGoals){
                        drawCount++;
                    }else{
                        lossCount++;
                    }
                    
                }else if(awayID == teamID){
                    goalsFor = goalsFor + awayGoals;
                    goalsAgainst = goalsAgainst + homeGoals;
                    gamesPlayed++;
                    
                    if(awayGoals > homeGoals){
                        winCount++;
                    }else if(homeGoals == awayGoals){
                        drawCount++;
                    }else{
                        lossCount++;
                    }
                }
                
            }
        } catch (SQLException sqle) {
            System.out.println("Exception when calculating league table:" + sqle.toString());
        }
        
        //Calculate goal difference (gd = gf - ga)
        goalDifference = goalsFor - goalsAgainst;
        
        //Calculate points, a win is equal to 3 points, a draw 1 point, and a loss 0 points.
        points = (winCount*3) + (drawCount);
        
       //load all the calculated information into the array to be loaded into league table
       leagueRecord[0] = teamName;
       leagueRecord[1] = gamesPlayed;
       leagueRecord[2] = winCount;
       leagueRecord[3] = drawCount;
       leagueRecord[4] = lossCount;
       leagueRecord[5] = goalsFor;
       leagueRecord[6] = goalsAgainst;
       leagueRecord[7] = goalDifference;
       leagueRecord[8] = points;
        
        return leagueRecord;
        
        
    }

    
}
