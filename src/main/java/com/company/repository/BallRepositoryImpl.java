package com.company.repository;

import com.company.Queries.Queries;
import com.company.beans.DetailedScoreSheet;
import com.company.beans.Game;
import com.company.beans.ResultOnSingleBall;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class BallRepositoryImpl implements BallRepository {
    @Override
    public void insertPerBallData(Connection con, Game game) {
        DetailedScoreSheet scoreSheet = game.getScoreBoard();
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
}
