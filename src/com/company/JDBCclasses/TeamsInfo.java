package com.company.JDBCclasses;

import com.company.beans.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeamsInfo {
    public static void insertIntoTeamsInfo(Game game, Connection con){
        try{
            String query = "INSERT INTO teams_info (teamId,teamName) VALUES (?,?);";
            PreparedStatement preStmt = con.prepareStatement(query);
            preStmt.setInt(1,game.getBattingTeam().getId());
            preStmt.setString(2,game.getBattingTeam().getName());
            preStmt.executeUpdate();

            preStmt.setInt(1,game.getBowlingTeam().getId());
            preStmt.setString(2,game.getBowlingTeam().getName());
            preStmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
