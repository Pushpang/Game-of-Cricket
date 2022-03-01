package com.company.beans;

import com.company.scoreUtilitiesClasses.RandomOutputOfBall;

import java.util.HashMap;

public class Player {
    private String name;
    private int playerId;
    private int runsScored;
    private int ballsFaced;
    private float strikeRate;
    private int runsGiven;
    private int ballsDelivered;
    private float economy;
    private int wicketsTaken;
    private int inAt;
    private PlayerRole role;
    private int bowledBy;
    private final HashMap<RandomOutputOfBall,Integer> runsFrequency = new HashMap<>();

    Player(){
        this.runsFrequency.put(RandomOutputOfBall.ZERO,0);
        this.runsFrequency.put(RandomOutputOfBall.ONE,0);
        this.runsFrequency.put(RandomOutputOfBall.TWO,0);
        this.runsFrequency.put(RandomOutputOfBall.THREE,0);
        this.runsFrequency.put(RandomOutputOfBall.FOUR,0);
        this.runsFrequency.put(RandomOutputOfBall.SIX,0);

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getBallsDelivered() {
        return ballsDelivered;
    }

    public void setBallsDelivered(int ballsDelivered) {
        this.ballsDelivered = ballsDelivered;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public int getInAt() {
        return inAt;
    }

    public void setInAt(int inAt) {
        this.inAt = inAt;
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public float getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(float strikeRate) {
        this.strikeRate = strikeRate;
    }

    public float getEconomy() {
        return economy;
    }

    public void setEconomy(float economy) {
        this.economy = economy;
    }

    public void setRunsFrequency(RandomOutputOfBall outcome){
        runsFrequency.put(outcome,runsFrequency.get(outcome)+1);
    }

    public HashMap<RandomOutputOfBall, Integer> getRunsFrequency() {
        return runsFrequency;
    }

    public int getBowledBy() {
        return bowledBy;
    }

    public void setBowledBy(int bowledBy) {
        this.bowledBy = bowledBy;
    }
}

