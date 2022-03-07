package com.company.beans;

import com.company.enums.RandomOutputOfBall;

import java.util.ArrayList;
import java.util.List;

public class DetailedScoreSheet {
    private int matchId;
    private int tossWinningTeamId;
    private int firstBattingTeamId;
    private int secondBattingTeamId;
    private final List<ResultOnSingleBall> scoreOnEveryBall= new ArrayList<>();




    public List<ResultOnSingleBall> getScoreOnEveryBall() {
        return scoreOnEveryBall;
    }


    public int getFirstBattingTeamId() {
        return firstBattingTeamId;
    }

    public void setFirstBattingTeamId(int firstBattingTeamId) {
        this.firstBattingTeamId = firstBattingTeamId;
    }

    public int getSecondBattingTeamId() {
        return secondBattingTeamId;
    }

    public void setSecondBattingTeamId(int secondBattingTeamId) {
        this.secondBattingTeamId = secondBattingTeamId;
    }

    public void setOutcome(int ballCount, int battingTeamId, int batterId, int bowlerId, RandomOutputOfBall outcome) {
        ResultOnSingleBall result = new ResultOnSingleBall(ballCount,battingTeamId,batterId, bowlerId, outcome);

            scoreOnEveryBall.add(result);

    }



    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTossWinningTeamId() {
        return tossWinningTeamId;
    }

    public void setTossWinningTeam(int tossWinningTeamId) {
        this.tossWinningTeamId = tossWinningTeamId;
    }




}
