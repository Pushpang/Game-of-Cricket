package com.company.repository;

import com.company.CONSTANTS.Constants;
import com.company.Queries.Queries;
import com.company.Utility.UtilityClass;
import com.company.beans.Game;
import com.company.entities.Match;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
    public void getAllMatches() {
        Connection con = UtilityClass.getConnection();
        List<Match> allMatches;
        Match match;
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.getAllMatches);
            preStmt.executeQuery();
//            ResultSet matchResult = preStmt.executeQuery();
//            while(matchResult.next())
//            {
////                match.
////            }
////            matchResult.setId( teamResult.getInt(1));
////            matchResult.setName(teamResult.getString(2));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Match getMatchById(int matchId) {
        Connection con = UtilityClass.getConnection();
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.getMatchById);
            preStmt.setInt(1,matchId);
            ResultSet matchResult = preStmt.executeQuery();
            matchResult.next();
            Match match = new Match(matchResult.getInt(1),matchResult.getInt(2),matchResult.getInt(3),
                   matchResult.getInt(4),matchResult.getInt(5),matchResult.getInt(6));
            return match;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}

