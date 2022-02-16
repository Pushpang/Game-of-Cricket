package com.company;

import java.util.ArrayList;

public class DetailedScoreSheet {
    private int matchId;
    private final ArrayList<ResultOnSingleBall> scoreOnEveryBallFirstInning= new ArrayList<>();
    private final ArrayList<ResultOnSingleBall> scoreOnEveryBallSecondInning= new ArrayList<>();

    public void setOutcome(int ballCount, int batterId, int bowlerId, String outcome,boolean secondInning) {
        ResultOnSingleBall result = new ResultOnSingleBall(ballCount, batterId, bowlerId, outcome);

        if(!secondInning) {
            scoreOnEveryBallFirstInning.add(result);
        }
        else
        {
            scoreOnEveryBallSecondInning.add(result);
        }
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void printData()
    {
        System.out.println("First Inning\n");
        for(ResultOnSingleBall currentBall: scoreOnEveryBallFirstInning)
        {
            System.out.println( "Ball no: "+currentBall.getBallNumber()+" Batter ID:"+currentBall.getBatterId() +
                    " Bowler ID: "+ currentBall.getBowlerId()+" Outcome: " + currentBall.getOutcome());
        }
        System.out.println("\nSecond Inning\n");
        for(ResultOnSingleBall currentBall: scoreOnEveryBallSecondInning)
        {
            System.out.println("Ball no: "+ currentBall.getBallNumber()+" Batter ID:"+currentBall.getBatterId() +
                    " Bowler ID: "+ currentBall.getBowlerId()+" Outcome: " + currentBall.getOutcome());
        }
    }

}
