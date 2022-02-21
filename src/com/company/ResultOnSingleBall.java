package com.company;

public class ResultOnSingleBall {
    private final int ballNumber;
    private final int batterId;
    private final int bowlerId;
    public RandomOutputOfBall outcome;
    //private int overNumber;
     public ResultOnSingleBall(int ballNumber,int batterId,int bowlerId,RandomOutputOfBall outcome){
         this.ballNumber = ballNumber;
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



}
