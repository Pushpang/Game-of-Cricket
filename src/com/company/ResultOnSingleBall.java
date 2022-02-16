package com.company;

public class ResultOnSingleBall {
    private int ballNumber;
    private int batterId;
    private int bowlerId;
    private String outcome;
    //private int overNumber;
     public ResultOnSingleBall(int ballNumber,int batterId,int bowlerId,String outcome){
         this.ballNumber = ballNumber;
         this.batterId = batterId;
         this.bowlerId = bowlerId;
         this.outcome = outcome;
     }

    public int getBallNumber() {
        return ballNumber;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public int getBatterId() {
        return batterId;
    }

    public void setBatterId(int batterId) {
        this.batterId = batterId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(int bowlerId) {
        this.bowlerId = bowlerId;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }


}
