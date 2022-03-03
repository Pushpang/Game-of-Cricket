package com.company.JDBCclasses;

import com.company.beans.Game;
import com.company.beans.Player;
import com.company.beans.Team;
import com.company.CONSTANTS.Constants;
import com.company.enums.PlayerRole;
import com.company.scoreUtilityClasses.DetailedScoreSheet;
import com.company.scoreUtilityClasses.ResultOnSingleBall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOCricketImp implements DAOCricket{

    @Override
    public void insertIntoTeamsInfoAndPlayersInfo(Game game, Connection con) {
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoTeamsInfoQuery);
            PreparedStatement preStmtPlayer = con.prepareStatement(Queries.insertIntoPlayersInfoQuery);

            if(teamCheckInDB(con,game.getTeam1().getId())!=1) {
                preStmt.setInt(1, game.getTeam1().getId());
                preStmt.setString(2, game.getTeam1().getName());
                preStmt.executeUpdate();

                for (Player currPlayer : game.getTeam1().getMember()) {
                    preStmtPlayer.setInt(1, currPlayer.getPlayerId());
                    preStmtPlayer.setInt(2, game.getTeam1().getId());
                    preStmtPlayer.setString(3, currPlayer.getName());
                    preStmtPlayer.setString(4, currPlayer.getRole().toString());
                    preStmtPlayer.setInt(5, currPlayer.getInAt());
                    preStmtPlayer.executeUpdate();
                }
            }

            if(teamCheckInDB(con,game.getTeam2().getId())!=1){
                preStmt.setInt(1, game.getTeam2().getId());
                preStmt.setString(2, game.getTeam2().getName());
                preStmt.executeUpdate();

                for (Player currPlayer : game.getTeam2().getMember()) {
                    preStmtPlayer.setInt(1, currPlayer.getPlayerId());
                    preStmtPlayer.setInt(2, game.getTeam2().getId());
                    preStmtPlayer.setString(3, currPlayer.getName());
                    preStmtPlayer.setString(4, currPlayer.getRole().toString());
                    preStmtPlayer.setInt(5, currPlayer.getInAt());
                    preStmtPlayer.executeUpdate();
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public void insetIntoMatchStats(Game game, Connection con) {
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoMatchStatsQuery);
            preStmt.setInt(1,game.getMatchId());
            preStmt.setInt(2, Constants.OVERS);
            preStmt.setInt(3,game.getScoreBoard().getTossWinningTeamId());
            preStmt.setInt(4,game.getScoreBoard().getFirstBattingTeamId());
            preStmt.setInt(5,game.getScoreBoard().getSecondBattingTeamId());
            preStmt.setInt(6,game.getWinnerTeamID());
            preStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void insertIntoPerBallStats(DetailedScoreSheet scoreSheet, Connection con) {
        try{

            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoPerBallStatsQuery);

            for(ResultOnSingleBall currBall: scoreSheet.getScoreOnEveryBall()) {
                preStmt.setInt(1, scoreSheet.getMatchId());
                preStmt.setInt(2, currBall.getBattingTeamId());
                preStmt.setInt(3, currBall.getBallNumber() / 6);
                preStmt.setInt(4, currBall.getBallNumber());
                preStmt.setInt(5, currBall.getBatterId());
                preStmt.setInt(6, currBall.getBowlerId());
                preStmt.setString(7, currBall.outcome.getValue());
                preStmt.executeUpdate();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int teamCheckInDB(Connection con,int teamId) {
        try {
            PreparedStatement preStmtTeam = con.prepareStatement(Queries.teamQueryCheck);
            preStmtTeam.setInt(1,teamId);
            ResultSet teamResult = preStmtTeam.executeQuery();
            teamResult.next();
            return teamResult.getInt(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int matchCheckInDB(Connection con,int matchId) {
        try {
            PreparedStatement preStmtMatch = con.prepareStatement(Queries.matchQueryCheck);
            preStmtMatch.setInt(1,matchId);
            ResultSet matchResult = preStmtMatch.executeQuery();
            matchResult.next();
            return matchResult.getInt(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Team fetchingTeamFromDB(Connection con,int teamId) {
        Team teamFromDB = new Team();

        try {
            PreparedStatement preStmtTeam = con.prepareStatement(Queries.teamQuery);
            preStmtTeam.setInt(1,teamId);
            ResultSet teamResult = preStmtTeam.executeQuery();
            teamResult.next();
            teamFromDB.setId( teamResult.getInt(1));
            teamFromDB.setName(teamResult.getString(2));

            PreparedStatement preStmtPlayers = con.prepareStatement(Queries.playerQuery);
            preStmtPlayers.setInt(1,teamId);
            ResultSet playersResult = preStmtPlayers.executeQuery();
            playersResult.next();

            for(int i=0;i<11;i++)
            {
                Player player = new Player();
                player.setPlayerId(playersResult.getInt(1));
                player.setName(playersResult.getString(3));
                player.setRole(PlayerRole.valueOf(playersResult.getString(4)));
                player.setInAt(playersResult.getInt(5));
                teamFromDB.getMember().add(player);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return teamFromDB;
    }

}
