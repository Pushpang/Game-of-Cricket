package com.company.entities;



public class Match {
    private int matchId;
    private int overs;
    private int tossWinningTeam;
    private int firstBattingTeam;
    private int secondBattingTeam;
    private int winnerTeamId;

//    public Match() {
//    }

    public Match(int matchId, int overs, int tossWinningTeam, int firstBattingTeam, int secondBattingTeam, int winnerTeamId) {
        this.matchId = matchId;
        this.overs = overs;
        this.tossWinningTeam = tossWinningTeam;
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
        this.winnerTeamId = winnerTeamId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getOvers() {
        return overs;
    }

    public int getFirstBattingTeam() {
        return firstBattingTeam;
    }

    public int getSecondBattingTeam() {
        return secondBattingTeam;
    }

    public int getWinnerTeamId() {
        return winnerTeamId;
    }

    public int getTossWinningTeam() {
        return tossWinningTeam;
    }
}
