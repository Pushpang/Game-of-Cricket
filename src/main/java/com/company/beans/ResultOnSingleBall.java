package com.company.beans;

import com.company.enums.RandomOutputOfBall;

public class ResultOnSingleBall {
    private final int ballNumber;
    private final int batterId;
    private final int bowlerId;
    private final int battingTeamId;
    public RandomOutputOfBall outcome;
     public ResultOnSingleBall(int ballNumber,int battingTeamId,int batterId,int bowlerId,RandomOutputOfBall outcome){
         this.ballNumber = ballNumber;
         this.battingTeamId = battingTeamId;
         this.batterId = batterId;
         this.bowlerId = bowlerId;
         this.outcome = outcome;
     }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getBatterId() {
        return batterId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public int getBattingTeamId() {
        return battingTeamId;
    }
}
