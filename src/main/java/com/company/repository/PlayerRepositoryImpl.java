package com.company.repository;

import com.company.Queries.Queries;
import com.company.Utility.UtilityClass;
import com.company.responses.PlayerStats;
import com.company.beans.PlayersInfo;
import com.company.enums.PlayerRole;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


@Service
public class PlayerRepositoryImpl implements PlayerRepository {
    @Override
    public void insertPlayers(Connection con, PlayersInfo player) {
        try{
            PreparedStatement preStmtPlayer = con.prepareStatement(Queries.insertIntoPlayersInfoQuery);
            preStmtPlayer.setInt(1, player.getPlayerId());
            preStmtPlayer.setInt(2, player.getTeamId());
            preStmtPlayer.setString(3, player.getPlayerName());
            preStmtPlayer.setString(4, player.getPlayerRole().toString());
            preStmtPlayer.setInt(5, player.getInAt());
            preStmtPlayer.setLong(6, System.currentTimeMillis());
            preStmtPlayer.setLong(7, System.currentTimeMillis());
            preStmtPlayer.setBoolean(8, false);
            preStmtPlayer.executeUpdate();
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

            return player;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getBowledByPlayerId(int playerId, int matchId) {
        Connection con = UtilityClass.getConnection();
        try {
            PreparedStatement preStmt = con.prepareStatement(Queries.getBowlerIdWhoTookWicket);
            preStmt.setInt(1,playerId);
            preStmt.setInt(2,matchId);
            ResultSet bowlerId = preStmt.executeQuery();
            bowlerId.next();
            return bowlerId.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public ArrayList<PlayersInfo> fetchingPlayersOfTeam(int teamId) {
        Connection con = UtilityClass.getConnection();
        ArrayList<PlayersInfo> playersOfTeam = new ArrayList<>();

        try {
            PreparedStatement preStmtPlayers = con.prepareStatement(Queries.playerQuery);
            preStmtPlayers.setInt(1,teamId);
            ResultSet playersResult = preStmtPlayers.executeQuery();
            playersResult.next();

            for(int i=0;i<11;i++)
            {
                PlayersInfo player = new PlayersInfo();
                player.setPlayerId(playersResult.getInt(1));
                player.setPlayerName(playersResult.getString(3));
                player.setPlayerRole(PlayerRole.valueOf(playersResult.getString(4)));
                player.setInAt(playersResult.getInt(5));
                playersOfTeam.add(player);
                playersResult.next();
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }

        return playersOfTeam;
    }
}

