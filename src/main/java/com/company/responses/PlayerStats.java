package com.company.responses;

import java.util.HashMap;

public class PlayerStats {
    private int playerId;
    private String name;
    private int inAt;
    private String role;
    private int teamId;
    private int runsScored=0;
    private int ballsFaced=0;
    private int runsGiven=0;
    private int ballsDelivered=0;
    private HashMap<String,Integer> battingStats = new HashMap<>();
    private HashMap<String,Integer> bowlingStats = new HashMap<>();
    private int wicketTakenByBowlerId = -1;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInAt() {
        return inAt;
    }

    public void setInAt(int inAt) {
        this.inAt = inAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }

    public HashMap<String, Integer> getBattingStats() {
        return battingStats;
    }

    public void setBattingStats(HashMap<String, Integer> battingStats) {
        this.battingStats = battingStats;
    }

    public HashMap<String, Integer> getBowlingStats() {
        return bowlingStats;
    }

    public void setBowlingStats(HashMap<String, Integer> bowlingStats) {
        this.bowlingStats = bowlingStats;
    }

    public int getWicketTakenByBowlerId() {
        return wicketTakenByBowlerId;
    }

    public void setWicketTakenByBowlerId(int wicketTakenByBowlerId) {
        this.wicketTakenByBowlerId = wicketTakenByBowlerId;
    }

    public int getBallsDelivered() {
        return ballsDelivered;
    }

    public void setBallsDelivered(int ballsDelivered) {
        this.ballsDelivered = ballsDelivered;
    }

    public PlayerStats(int playerId, int teamId, String name, String role, int inAt) {
        this.playerId = playerId;
        this.name = name;
        this.inAt = inAt;
        this.role = role;
        this.teamId = teamId;
        this.battingStats.put("0",0);
        this.battingStats.put("1",0);
        this.battingStats.put("2",0);
        this.battingStats.put("3",0);
        this.battingStats.put("4",0);
        this.battingStats.put("6",0);
        this.battingStats.put("W",0);

        this.bowlingStats.put("0",0);
        this.bowlingStats.put("1",0);
        this.bowlingStats.put("2",0);
        this.bowlingStats.put("3",0);
        this.bowlingStats.put("4",0);
        this.bowlingStats.put("6",0);
        this.bowlingStats.put("W",0);

    }

}
