package com.company.repository;

import com.company.Queries.Queries;
import com.company.Utility.UtilityClass;
import com.company.beans.Player;
import com.company.beans.Team;
import com.company.enums.PlayerRole;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class TeamRepositoryImpl implements TeamRepository{
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
    public Team fetchingTeamFromDB(int teamId) {
        Connection con = UtilityClass.getConnection();
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

    @Override
    public void insertTeam(Connection con,Team team) {
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoTeamsInfoQuery);
                preStmt.setInt(1, team.getId());
                preStmt.setString(2, team.getName());
                preStmt.setLong(3, System.currentTimeMillis());
                preStmt.setLong(4, System.currentTimeMillis());
                preStmt.setBoolean(5, false);
                preStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}


