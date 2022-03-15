package com.company.beans;

import com.company.enums.RandomOutputOfBall;

public class ResultOnSingleBall {

    private int ballId;
    private int matchId;
    private int battingTeamId;
    private int overNumber;
    private int ballNumber;
    private int batterId;
    private int bowlerId;
    public RandomOutputOfBall outcome;

    public ResultOnSingleBall(int matchId, int battingTeamId,int overNumber,int ballNumber, int batterId, int bowlerId, RandomOutputOfBall outcome) {
        this.matchId = matchId;
        this.overNumber = overNumber;
        this.ballNumber = ballNumber;
        this.battingTeamId = battingTeamId;
        this.batterId = batterId;
        this.bowlerId = bowlerId;
        this.outcome = outcome;
    }

    public int getBallId() {
        return ballId;
    }

    public void setBallId(int ballId) {
        this.ballId = ballId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getBattingTeamId() {
        return battingTeamId;
    }

    public void setBattingTeamId(int battingTeamId) {
        this.battingTeamId = battingTeamId;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
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
}

