package com.company.beans;



public class MatchStats {
    private int matchId;
    private int overs;
    private int tossWinningTeam;
    private int firstBattingTeam;
    private int secondBattingTeam;
    private int winnerTeamId;
    private long createdTime;
    private long modifiedTime;
    private boolean deleted;

//    public Match() {
//    }

    public MatchStats(){

    }
    public MatchStats(int matchId, int overs, int tossWinningTeam, int firstBattingTeam, int secondBattingTeam, int winnerTeamId, long createdTime, long modifiedTime, boolean deleted) {
        this.matchId = matchId;
        this.overs = overs;
        this.tossWinningTeam = tossWinningTeam;
        this.firstBattingTeam = firstBattingTeam;
        this.secondBattingTeam = secondBattingTeam;
        this.winnerTeamId = winnerTeamId;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.deleted = deleted;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public boolean getDeleted(){
        return deleted;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public void setTossWinningTeam(int tossWinningTeam) {
        this.tossWinningTeam = tossWinningTeam;
    }

    public void setFirstBattingTeam(int firstBattingTeam) {
        this.firstBattingTeam = firstBattingTeam;
    }

    public void setSecondBattingTeam(int secondBattingTeam) {
        this.secondBattingTeam = secondBattingTeam;
    }

    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
