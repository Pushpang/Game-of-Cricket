package com.company.beans;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Game {
    private MatchStats matchStats = new MatchStats();
    private TeamInfo team1Info, team2Info;
    private ArrayList<PlayersInfo> playersTeam1, playersTeam2;
    private ArrayList<ResultOnSingleBall> perBallStats = new ArrayList<>();
    private int scoreOfFirstInning=0,scoreOfSecondInning=0;
    private int winnerTeamID;


    public MatchStats getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(MatchStats matchStats) {
        this.matchStats = matchStats;
    }

    public TeamInfo getTeam1Info() {
        return team1Info;
    }

    public void setTeam1Info(TeamInfo team1Info) {
        this.team1Info = team1Info;
    }

    public TeamInfo getTeam2Info() {
        return team2Info;
    }

    public void setTeam2Info(TeamInfo team2Info) {
        this.team2Info = team2Info;
    }

    public ArrayList<PlayersInfo> getPlayersTeam1() {
        return playersTeam1;
    }

    public void setPlayersTeam1(ArrayList<PlayersInfo> playersTeam1) {
        this.playersTeam1 = playersTeam1;
    }

    public ArrayList<PlayersInfo> getPlayersTeam2() {
        return playersTeam2;
    }

    public void setPlayersTeam2(ArrayList<PlayersInfo> playersTeam2) {
        this.playersTeam2 = playersTeam2;
    }

    public int getScoreOfFirstInning() {
        return scoreOfFirstInning;
    }

    public void setScoreOfFirstInning(int scoreOfFirstInning) {
        this.scoreOfFirstInning = scoreOfFirstInning;
    }

    public int getScoreOfSecondInning() {
        return scoreOfSecondInning;
    }

    public void setScoreOfSecondInning(int scoreOfSecondInning) {
        this.scoreOfSecondInning = scoreOfSecondInning;
    }

    public int getWinnerTeamID() {
        return winnerTeamID;
    }

    public ArrayList<ResultOnSingleBall> getPerBallStats() {
        return perBallStats;
    }

    public void setPerBallStats(ArrayList<ResultOnSingleBall> perBallStats) {
        this.perBallStats = perBallStats;
    }

    public void setWinnerTeamID(int winnerTeamID) {
        this.winnerTeamID = winnerTeamID;
    }





}