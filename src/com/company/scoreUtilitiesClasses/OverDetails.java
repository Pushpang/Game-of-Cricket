package com.company.scoreUtilitiesClasses;

import java.util.HashMap;

public class OverDetails {
    private final int overNumber;
    private final int bowlerId;
    private int totalRunsInOver=0;
    private final HashMap<RandomOutputOfBall,Integer> outcomeFrequency = new HashMap<>();

    public OverDetails(int overNumber,int bowlerId){
        this.overNumber = overNumber;
        this.bowlerId = bowlerId;
        this.outcomeFrequency.put(RandomOutputOfBall.ZERO,0);
        this.outcomeFrequency.put(RandomOutputOfBall.ONE,0);
        this.outcomeFrequency.put(RandomOutputOfBall.TWO,0);
        this.outcomeFrequency.put(RandomOutputOfBall.THREE,0);
        this.outcomeFrequency.put(RandomOutputOfBall.FOUR,0);
        this.outcomeFrequency.put(RandomOutputOfBall.SIX,0);
        this.outcomeFrequency.put(RandomOutputOfBall.WICKET,0);
    }

    public int getOverNumber() {
        return overNumber;
    }

    public int getBowlerId() {
        return bowlerId;
    }


    public int getTotalRunsInOver() {
        return totalRunsInOver;
    }

    public void setTotalRunsInOver(int runsInOver) {
        this.totalRunsInOver = runsInOver;
    }


    public HashMap<RandomOutputOfBall, Integer> getOutcomeFrequency() {
        return outcomeFrequency;
    }
    public void setOutcomeFrequency(RandomOutputOfBall outcome) {
        outcomeFrequency.put(outcome,outcomeFrequency.get(outcome)+ 1);
    }
}

