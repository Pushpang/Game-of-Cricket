package com.company.scoreUtilitiesClasses;

public class ResultOnSingleBall {
    private final int ballNumber;
    private final int overNumber;
    private final int batterId;
    private final int bowlerId;
    public RandomOutputOfBall outcome;
    //private int overNumber;
     public ResultOnSingleBall(int ballNumber,int overNumber,int batterId,int bowlerId,RandomOutputOfBall outcome){
         this.ballNumber = ballNumber;
         this.overNumber = overNumber;
         this.batterId = batterId;
         this.bowlerId = bowlerId;
         this.outcome = outcome;
     }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public int getBatterId() {
        return batterId;
    }

    public int getBowlerId() {
        return bowlerId;
    }



}
