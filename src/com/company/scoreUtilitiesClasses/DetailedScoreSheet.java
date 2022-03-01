package com.company.scoreUtilitiesClasses;

import java.util.ArrayList;

public class DetailedScoreSheet {
    private int matchId;
    private int firstBattingTeamId;
    private int secondBattingTeamId;
    private final ArrayList<ResultOnSingleBall> scoreOnEveryBallFirstInning= new ArrayList<>(); // per ball data of 1st inning
    private final ArrayList<ResultOnSingleBall> scoreOnEveryBallSecondInning= new ArrayList<>(); // per ball data of 2nd inning
    private final ArrayList<OverDetails>  overDataFirstInning  =new ArrayList<>(); // per over data of 1st inning
    private final ArrayList<OverDetails>  overDataSecondInning = new ArrayList<>(); // per over data of 2nd inning

    public ArrayList<OverDetails> getOverDataFirstInning() {
        return overDataFirstInning;
    }

    public ArrayList<OverDetails> getOverDataSecondInning() {
        return overDataSecondInning;
    }

    public ArrayList<ResultOnSingleBall> getScoreOnEveryBallFirstInning() {
        return scoreOnEveryBallFirstInning;
    }

    public ArrayList<ResultOnSingleBall> getScoreOnEveryBallSecondInning() {
        return scoreOnEveryBallSecondInning;
    }

    public int getFirstBattingTeamId() {
        return firstBattingTeamId;
    }

    public int getSecondBattingTeamId() {
        return secondBattingTeamId;
    }

    public void setOutcome(int ballCount, int overNumber,int batterId, int bowlerId, RandomOutputOfBall outcome, boolean secondInning) {
        ResultOnSingleBall result = new ResultOnSingleBall(ballCount,overNumber, batterId, bowlerId, outcome);

        if(!secondInning) {
            scoreOnEveryBallFirstInning.add(result);
        }
        else
        {
            scoreOnEveryBallSecondInning.add(result);
        }
    }

    public void setOverData(OverDetails over,int battingTeamId ,boolean secondInning){

        if(!secondInning){
            firstBattingTeamId = battingTeamId;
            overDataFirstInning.add(over);
        }
        else{
            secondBattingTeamId = battingTeamId;
            overDataSecondInning.add(over);
        }

    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void printDataPerBall()
    {
        System.out.println("First Inning\n");
        for(ResultOnSingleBall currentBall: scoreOnEveryBallFirstInning)
        {
            System.out.println( "Ball no: "+currentBall.getBallNumber()+" Batter ID:"+currentBall.getBatterId() +
                    " Bowler ID: "+ currentBall.getBowlerId()+" Outcome: " + currentBall.outcome.getValue());
        }
        System.out.println("\nSecond Inning\n");
        for(ResultOnSingleBall currentBall: scoreOnEveryBallSecondInning)
        {
            System.out.println("Ball no: "+ currentBall.getBallNumber()+" Batter ID:"+currentBall.getBatterId() +
                    " Bowler ID: "+ currentBall.getBowlerId()+" Outcome: " + currentBall.outcome.getValue());
        }
    }

    public void printDataPerOver()
    {
        System.out.println("First Inning\n");
        for(OverDetails currentOver: overDataFirstInning)
        {
            System.out.println( "Over no: "+currentOver.getOverNumber()+" Bowler ID:"+currentOver.getBowlerId() +
                    " runs/wickets: " + currentOver.getTotalRunsInOver()+"/"+currentOver.getOutcomeFrequency().get(RandomOutputOfBall.WICKET));

        }
        System.out.println("\nSecond Inning\n");
        for(OverDetails currentOver: overDataSecondInning)
        {
            System.out.println( "Over no: "+currentOver.getOverNumber()+" Bowler ID:"+currentOver.getBowlerId() +
                    " runs/wickets: " + currentOver.getTotalRunsInOver()+"/"+currentOver.getOutcomeFrequency().get(RandomOutputOfBall.WICKET));
        }
    }

}
