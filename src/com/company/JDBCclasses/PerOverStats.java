package com.company.JDBCclasses;

import com.company.scoreUtilitiesClasses.OverDetails;
import com.company.scoreUtilitiesClasses.DetailedScoreSheet;
import com.company.scoreUtilitiesClasses.RandomOutputOfBall;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PerOverStats{
    public static void insertIntoPerOverSTATS(DetailedScoreSheet scoreSheet, Connection con)
    {
        try{
        String query = "INSERT INTO per_over_stats (matchId,battingTeamId,OverNumber,runsInOver,dotDelivered," +
                "onesGiven,twosGiven,threesGiven,foursGiven,sixesGiven,wickets,bowledBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement preStmt = con.prepareStatement(query);

        for(OverDetails currOver: scoreSheet.getOverDataFirstInning())
        {
            preStmt.setInt(1, scoreSheet.getMatchId());
            preStmt.setInt(2, scoreSheet.getFirstBattingTeamId());
            preStmt.setInt(3, currOver.getOverNumber());
            preStmt.setInt(4,currOver.getTotalRunsInOver());
            preStmt.setInt(5, currOver.getOutcomeFrequency().get(RandomOutputOfBall.ZERO));
            preStmt.setInt(6,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.ONE));
            preStmt.setInt(7,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.TWO));
            preStmt.setInt(8,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.THREE));
            preStmt.setInt(9,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.FOUR));
            preStmt.setInt(10, currOver.getOutcomeFrequency().get(RandomOutputOfBall.SIX));
            preStmt.setInt(11, currOver.getOutcomeFrequency().get(RandomOutputOfBall.WICKET));
            preStmt.setInt(12, currOver.getBowlerId());
            preStmt.executeUpdate();
        }
            for(OverDetails currOver: scoreSheet.getOverDataSecondInning())
            {
                preStmt.setInt(1, scoreSheet.getMatchId());
                preStmt.setInt(2, scoreSheet.getSecondBattingTeamId());
                preStmt.setInt(3, currOver.getOverNumber());
                preStmt.setInt(4,currOver.getTotalRunsInOver());
                preStmt.setInt(5, currOver.getOutcomeFrequency().get(RandomOutputOfBall.ZERO));
                preStmt.setInt(6,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.ONE));
                preStmt.setInt(7,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.TWO));
                preStmt.setInt(8,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.THREE));
                preStmt.setInt(9,  currOver.getOutcomeFrequency().get(RandomOutputOfBall.FOUR));
                preStmt.setInt(10, currOver.getOutcomeFrequency().get(RandomOutputOfBall.SIX));
                preStmt.setInt(11, currOver.getOutcomeFrequency().get(RandomOutputOfBall.WICKET));
                preStmt.setInt(12, currOver.getBowlerId());
                preStmt.executeUpdate();
            }


    }catch(Exception e){
        e.printStackTrace();
    }
    }
}
