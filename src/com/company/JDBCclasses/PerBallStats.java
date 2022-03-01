package com.company.JDBCclasses;

import com.company.scoreUtilitiesClasses.DetailedScoreSheet;
import com.company.scoreUtilitiesClasses.ResultOnSingleBall;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PerBallStats {
    public static void insertIntoPerBallStats(DetailedScoreSheet scoreSheet, Connection con)
    {
        try{
            String query = "INSERT INTO per_ball_stats (matchId,battingTeamId,OverNumber,ballNumber," +
                    "batterId,bowlerId,resultOnThatBall) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preStmt = con.prepareStatement(query);

            for(ResultOnSingleBall currBall: scoreSheet.getScoreOnEveryBallFirstInning())
            {
                preStmt.setInt(1, scoreSheet.getMatchId());
                preStmt.setInt(2, scoreSheet.getFirstBattingTeamId());
                preStmt.setInt(3, currBall.getOverNumber());
                preStmt.setInt(4,currBall.getBallNumber());
                preStmt.setInt(5, currBall.getBatterId());
                preStmt.setInt(6,  currBall.getBowlerId());
                preStmt.setString(7,  currBall.outcome.getValue());
                preStmt.executeUpdate();
            }
            for(ResultOnSingleBall currBall: scoreSheet.getScoreOnEveryBallSecondInning())
            {
                preStmt.setInt(1, scoreSheet.getMatchId());
                preStmt.setInt(2, scoreSheet.getSecondBattingTeamId());
                preStmt.setInt(3, currBall.getOverNumber());
                preStmt.setInt(4,currBall.getBallNumber());
                preStmt.setInt(5, currBall.getBatterId());
                preStmt.setInt(6,  currBall.getBowlerId());
                preStmt.setString(7,  currBall.outcome.getValue());
                preStmt.executeUpdate();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
