package com.company.beans;

import com.company.scoreUtilityClasses.DetailedScoreSheet;
import com.company.scoreUtilityClasses.ResultOnSingleBall;


public class Game {
    private final int matchId;
    private Team team1 ,team2;
    private final DetailedScoreSheet scoreBoard = new DetailedScoreSheet();
    private int winnerTeamID;

    public Game(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }


    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public DetailedScoreSheet getScoreBoard() {
        return scoreBoard;
    }

    public int getWinnerTeamID() {
        return winnerTeamID;
    }

    public void setWinnerTeamID(int winnerTeamID) {
        this.winnerTeamID = winnerTeamID;
    }

    public void printResult() {
        System.out.println("And the final result is:");
        for(ResultOnSingleBall currBall: scoreBoard.getScoreOnEveryBall()){
            System.out.println(currBall.getBallNumber()+" "+ currBall.getBatterId()+" "+currBall.getBowlerId()+" "+currBall.getBattingTeamId()+" "+currBall.outcome);
        }
    }




}