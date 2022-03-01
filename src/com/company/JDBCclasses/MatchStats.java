package com.company.JDBCclasses;

import com.company.beans.Game;

import java.sql.*;

public class MatchStats {
    public static void insetIntoMatchStats(Game game,Connection con){
        try{
            String query = "INSERT INTO matchStats (matchID, overs,tossWinningTeamId,firstBattingTeamId, secondBattingTeamId,winnerTeamId) VALUES (?,?,?,?,?,?);";
            PreparedStatement preStmt = con.prepareStatement(query);
            preStmt.setInt(1,game.getMatchId());
            preStmt.setInt(2,game.getOvers());
            preStmt.setInt(3,game.getTossWinningTeamId());
            preStmt.setInt(4,game.getBowlingTeam().getId());
            preStmt.setInt(5,game.getBattingTeam().getId());
            preStmt.setInt(6,game.getWinnerTeamID());
            preStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
