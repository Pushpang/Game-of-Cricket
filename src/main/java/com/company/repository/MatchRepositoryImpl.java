package com.company.repository;

import com.company.Queries.Queries;
import com.company.Utility.UtilityClass;
import com.company.beans.Game;
import com.company.beans.MatchStats;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class MatchRepositoryImpl implements MatchRepository {

    @Override
    public int getNewMatchId() {
        Connection con  = UtilityClass.getConnection();
        try {
            PreparedStatement preStmtTeam = con.prepareStatement(Queries.getNewMatchId);
            ResultSet matchIdResult = preStmtTeam.executeQuery();
            matchIdResult.next();
            return (matchIdResult.getInt(1) + 1);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void insertMatch(Connection con, Game game) {
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoMatchStatsQuery);
            preStmt.setInt(1,game.getMatchStats().getMatchId());
            preStmt.setInt(2,game.getMatchStats().getOvers());
            preStmt.setInt(3,game.getMatchStats().getTossWinningTeam());
            preStmt.setInt(4,game.getMatchStats().getFirstBattingTeam());
            preStmt.setInt(5,game.getMatchStats().getSecondBattingTeam());
            preStmt.setInt(6,game.getWinnerTeamID());
            preStmt.setLong(7, System.currentTimeMillis());
            preStmt.setLong(8, System.currentTimeMillis());
            preStmt.setBoolean(9, false);
            preStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public MatchStats getMatchById(int matchId) {
        Connection con = UtilityClass.getConnection();
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.getMatchById);
            preStmt.setInt(1,matchId);
            ResultSet matchResult = preStmt.executeQuery();
            matchResult.next();
            return new MatchStats(matchResult.getInt(1),matchResult.getInt(2),matchResult.getInt(3),
                   matchResult.getInt(4),matchResult.getInt(5),matchResult.getInt(6),
                    matchResult.getLong(7),matchResult.getLong(8),matchResult.getBoolean(9));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}

