package com.company.JDBCclasses;

import com.company.beans.Game;
import com.company.beans.Player;
import com.company.scoreUtilitiesClasses.RandomOutputOfBall;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayersStats {
    public static void insertIntoPlayersStats(Game game, Connection con){
        try{
            String query = "INSERT INTO players_stats (matchId,playerId,runsScored,ballsFaced,dotPlayed," +
                    "onesScored,twosScored,threesScored,foursScored,sixesScored,bowledBy," +
                    "strikeRate,runsGiven,ballsDelivered,economy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preStmt = con.prepareStatement(query);

            for(Player currPlayer: game.getBowlingTeam().getMember())
            {
                preStmt.setInt(1, game.getMatchId());
                preStmt.setInt(2, currPlayer.getPlayerId());
                preStmt.setInt(3, currPlayer.getRunsScored());
                preStmt.setInt(4,currPlayer.getBallsFaced());
                preStmt.setInt(5, currPlayer.getRunsFrequency().get(RandomOutputOfBall.ZERO));
                preStmt.setInt(6, currPlayer.getRunsFrequency().get(RandomOutputOfBall.ONE));
                preStmt.setInt(7, currPlayer.getRunsFrequency().get(RandomOutputOfBall.TWO));
                preStmt.setInt(8, currPlayer.getRunsFrequency().get(RandomOutputOfBall.THREE));
                preStmt.setInt(9, currPlayer.getRunsFrequency().get(RandomOutputOfBall.FOUR));
                preStmt.setInt(10, currPlayer.getRunsFrequency().get(RandomOutputOfBall.SIX));
                preStmt.setInt(11, currPlayer.getBowledBy());
                preStmt.setFloat(12, currPlayer.getStrikeRate());
                preStmt.setInt(13, currPlayer.getRunsGiven());
                preStmt.setInt(14, currPlayer.getBallsDelivered());
                preStmt.setFloat(15, currPlayer.getEconomy());
                preStmt.executeUpdate();
            }
            for(Player currPlayer: game.getBattingTeam().getMember())
            {
                preStmt.setInt(1, game.getMatchId());
                preStmt.setInt(2, currPlayer.getPlayerId());
                preStmt.setInt(3, currPlayer.getRunsScored());
                preStmt.setInt(4,currPlayer.getBallsFaced());
                preStmt.setInt(5, currPlayer.getRunsFrequency().get(RandomOutputOfBall.ZERO));
                preStmt.setInt(6, currPlayer.getRunsFrequency().get(RandomOutputOfBall.ONE));
                preStmt.setInt(7, currPlayer.getRunsFrequency().get(RandomOutputOfBall.TWO));
                preStmt.setInt(8, currPlayer.getRunsFrequency().get(RandomOutputOfBall.THREE));
                preStmt.setInt(9, currPlayer.getRunsFrequency().get(RandomOutputOfBall.FOUR));
                preStmt.setInt(10, currPlayer.getRunsFrequency().get(RandomOutputOfBall.SIX));
                preStmt.setInt(11, currPlayer.getBowledBy());
                preStmt.setFloat(12, currPlayer.getStrikeRate());
                preStmt.setInt(13, currPlayer.getRunsGiven());
                preStmt.setInt(14, currPlayer.getBallsDelivered());
                preStmt.setFloat(15, currPlayer.getEconomy());
                preStmt.executeUpdate();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
