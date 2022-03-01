package com.company.JDBCclasses;

import com.company.beans.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeamsStats {
    public static void insertIntoTeamStats(Game game , Connection con)
    {
        try{
            String query = "INSERT INTO teams_stats (matchId,teamId,runsScored,ballsPlayed,wicketsDown) VALUES (?,?,?,?,?);";
            PreparedStatement preStmt = con.prepareStatement(query);
            preStmt.setInt(1,game.getMatchId());
            preStmt.setInt(2,game.getBowlingTeam().getId());
            preStmt.setInt(3,game.getBowlingTeam().getScore());
            preStmt.setInt(4,game.getScoreBoard().getScoreOnEveryBallFirstInning().size());
            preStmt.setInt(5,game.getBowlingTeam().getWickets());
            preStmt.executeUpdate();

            preStmt.setInt(1,game.getMatchId());
            preStmt.setInt(2,game.getBattingTeam().getId());
            preStmt.setInt(3,game.getBattingTeam().getScore());
            preStmt.setInt(4,game.getScoreBoard().getScoreOnEveryBallSecondInning().size());
            preStmt.setInt(5,game.getBowlingTeam().getWickets());
            preStmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
