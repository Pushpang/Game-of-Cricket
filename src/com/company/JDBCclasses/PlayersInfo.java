package com.company.JDBCclasses;

import com.company.beans.Game;
import com.company.beans.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayersInfo {
    public static void insertIntoPlayersInfo(Game game, Connection con)
    {
        try{
            String query = "INSERT INTO players_info (playerId,teamId,playerName,playerRole,inAt) VALUES (?,?,?,?,?);";
            PreparedStatement preStmt = con.prepareStatement(query);

            for(Player currPlayer: game.getBattingTeam().getMember())
            {
                preStmt.setInt(1, currPlayer.getPlayerId());
                preStmt.setInt(2, game.getBattingTeam().getId());
                preStmt.setString(3,currPlayer.getName());
                preStmt.setString(4, currPlayer.getRole().toString());
                preStmt.setInt(5, currPlayer.getInAt());
                preStmt.executeUpdate();
            }
            for(Player currPlayer: game.getBowlingTeam().getMember())
            {
                preStmt.setInt(1, currPlayer.getPlayerId());
                preStmt.setInt(2, game.getBowlingTeam().getId());
                preStmt.setString(3,currPlayer.getName());
                preStmt.setString(4, currPlayer.getRole().toString());
                preStmt.setInt(5, currPlayer.getInAt());
                preStmt.executeUpdate();
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

