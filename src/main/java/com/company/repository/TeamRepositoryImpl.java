package com.company.repository;

import com.company.Queries.Queries;
import com.company.Utility.UtilityClass;
import com.company.beans.TeamInfo;
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
    public void insertTeam(Connection con,TeamInfo team) {
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoTeamsInfoQuery);
                preStmt.setInt(1, team.getTeamId());
                preStmt.setString(2, team.getTeamName());
                preStmt.setLong(3, System.currentTimeMillis());
                preStmt.setLong(4, System.currentTimeMillis());
                preStmt.setBoolean(5, false);
                preStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public TeamInfo fetchingTeamInfoFromDB(int teamId) {
        Connection con = UtilityClass.getConnection();
        TeamInfo teamInfoFromDB = new TeamInfo();

        try {
            PreparedStatement preStmtTeam = con.prepareStatement(Queries.teamQuery);
            preStmtTeam.setInt(1,teamId);
            ResultSet teamResult = preStmtTeam.executeQuery();
            teamResult.next();
            teamInfoFromDB.setTeamId( teamResult.getInt(1));
            teamInfoFromDB.setTeamName(teamResult.getString(2));
            teamInfoFromDB.setCreatedTime(teamResult.getLong(3));
            teamInfoFromDB.setModifiedTime(teamResult.getLong(4));
            teamInfoFromDB.setDeleted(teamResult.getBoolean(5));

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return teamInfoFromDB;
    }


}


