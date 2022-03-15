package com.company.repository;

import com.company.Queries.Queries;
import com.company.beans.Game;
import com.company.beans.ResultOnSingleBall;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class BallRepositoryImpl implements BallRepository {
    @Override
    public void insertPerBallData(Connection con, Game game) {
        try{
            PreparedStatement preStmt = con.prepareStatement(Queries.insertIntoPerBallStatsQuery);

            for(ResultOnSingleBall currBall: game.getPerBallStats()) {
                preStmt.setInt(1, currBall.getMatchId());
                preStmt.setInt(2, currBall.getBattingTeamId());
                preStmt.setInt(3, currBall.getOverNumber());
                preStmt.setInt(4, currBall.getBallNumber());
                preStmt.setInt(5, currBall.getBatterId());
                preStmt.setInt(6, currBall.getBowlerId());
                preStmt.setString(7, currBall.outcome.getValue());
                preStmt.setLong(8, System.currentTimeMillis());
                preStmt.setLong(9, System.currentTimeMillis());
                preStmt.setBoolean(10, false);
                preStmt.executeUpdate();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
