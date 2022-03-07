package com.company.repository;

import com.company.Queries.Queries;
import com.company.Utility.UtilityClass;
import com.company.beans.Player;
import com.company.beans.Team;
import com.company.entities.PlayerStats;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

@Service
public class PlayerRepositoryImpl implements PlayerRepository {
    @Override
    public void insertPlayers(Connection con, Team team) {
        try{
            PreparedStatement preStmtPlayer = con.prepareStatement(Queries.insertIntoPlayersInfoQuery);

                for (Player currPlayer : team.getMember()) {
                    preStmtPlayer.setInt(1, currPlayer.getPlayerId());
                    preStmtPlayer.setInt(2, team.getId());
                    preStmtPlayer.setString(3, currPlayer.getName());
                    preStmtPlayer.setString(4, currPlayer.getRole().toString());
                    preStmtPlayer.setInt(5, currPlayer.getInAt());
                    preStmtPlayer.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId) {
        Connection con = UtilityClass.getConnection();
        PlayerStats player;
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.getPlayerInfoById);
            preStmt.setInt(1,playerId);
            ResultSet playerResult = preStmt.executeQuery();
            playerResult.next();
            player  = new PlayerStats(playerResult.getInt(1),playerResult.getInt(2),playerResult.getString(3)
                    ,playerResult.getString(4), playerResult.getInt(5));

            preStmt = con.prepareStatement(Queries.getBatterDetailsFromMatch);
            preStmt.setInt(1,playerId);
            preStmt.setInt(2,matchId);
            ResultSet batterResult = preStmt.executeQuery();
            playerResult.next();
            while(batterResult.next())
            {
                player.getBattingStats().put(batterResult.getString(1),player.getBattingStats().get(batterResult.getString(1))+1);
            }

            preStmt = con.prepareStatement(Queries.getBowlerDetailsFromMatch);
            preStmt.setInt(1,playerId);
            preStmt.setInt(2,matchId);
            ResultSet bowlerResult = preStmt.executeQuery();
            playerResult.next();
            while(bowlerResult.next())
            {
                player.getBowlingStats().put(bowlerResult.getString(1),player.getBowlingStats().get(bowlerResult.getString(1))+1);
            }
            HashMap <String,Integer> batting = player.getBattingStats(),bowling=player.getBowlingStats();
            int runsScored = batting.get("1") + 2*batting.get("2")+ 3*batting.get("3") + 4*batting.get("4")+ 6*batting.get("6");
            int runsGiven = bowling.get("1") + 2*bowling.get("2")+ 3*bowling.get("3") + 4*bowling.get("4")+ 6*bowling.get("6");
            int ballsFaced = batting.get("0") + batting.get("1") +batting.get("2")+ batting.get("3") + batting.get("4")+ batting.get("6") + batting.get("W");
            int ballsDelivered = bowling.get("0")+ bowling.get("1") + bowling.get("2")+ bowling.get("3") + bowling.get("4")+ bowling.get("6") + bowling.get("W");

            player.setRunsScored(runsScored);
            player.setRunsGiven(runsGiven);
            player.setBallsFaced(ballsFaced);
            player.setBallsDelivered(ballsDelivered);
            if(batting.get("W")==1)
            {
                preStmt = con.prepareStatement(Queries.getBowlerIdWhoTookWicket);
                preStmt.setInt(1,playerId);
                ResultSet bowlerId = preStmt.executeQuery();
                bowlerId.next();
                player.setWicketTakenByBowlerId(bowlerId.getInt(1));
            }

            return player;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

