package com.company;

public class Game {
    private final int matchId;
    private Team battingTeam, bowlingTeam;
    public static final int OVERS = 20;
    private Player onStrike, nonStrike, bowler;
    private final DetailedScoreSheet scoreBoard = new DetailedScoreSheet();

    public Game(int matchId) {
        this.matchId = matchId;
    }

    // setting up both the teams
    public void setupTeams() {

        scoreBoard.setMatchId(matchId);

        battingTeam = new Team();
        bowlingTeam = new Team();
        battingTeam.setId(1001);
        bowlingTeam.setId(1002);
        battingTeam.setName("CSK");
        bowlingTeam.setName("MI");
        battingTeam.setWickets(0);
        bowlingTeam.setWickets(0);
        System.out.println("Team 1 name: " + battingTeam.getName());
        System.out.println("Team 2 name: " + bowlingTeam.getName());
        battingTeam.setupPlayers(101);
        bowlingTeam.setupPlayers(201);

    }

    public void toss() {
        int tossVar = (int) (Math.random() * 2);
        if (tossVar == 1)      // if random number is 0 then no swap but when it is 1 then swapping the teams
        {
            Team t = battingTeam;
            battingTeam = bowlingTeam;
            bowlingTeam = t;
        }
        System.out.println("\nToss is WON by: " + battingTeam.getName() + " and they choose to bat\n");

    }


    public void printingScore() {
        scoreBoard.printDataPerBall();
        scoreBoard.printDataPerOver();
    }

    //for a single ball, made a ballOutcome method which stores the particular ball outcome to players profile, prints result per ball
    public void ballOutcome(int ballCount, boolean secondInning, OverDetails over) {

        RandomOutputOfBall outcome = RandomOutputOfBall.randomOutcome(onStrike);
        scoreBoard.setOutcome(ballCount, onStrike.getPlayerId(), bowler.getPlayerId(), outcome, secondInning);
        over.setOutcomeFrequency(outcome);

        if (outcome == RandomOutputOfBall.WICKET) {
            battingTeam.setWickets(battingTeam.getWickets() + 1);
            onStrike.setBallsFaced(onStrike.getBallsFaced() + 1);
            bowler.setWicketsTaken(bowler.getWicketsTaken() + 1);

            if (battingTeam.getWickets() < 10) {
                onStrike = battingTeam.getMember().get(Math.max(onStrike.getInAt(), nonStrike.getInAt()));
            }

        } else {
            battingTeam.setScore(battingTeam.getScore() + Integer.parseInt(outcome.getValue()));
            onStrike.setRunsScored(onStrike.getRunsScored() + Integer.parseInt(outcome.getValue()));
            onStrike.setBallsFaced(onStrike.getBallsFaced() + 1);
            onStrike.setRunsFrequency(outcome);  // to store in hashmap the count of 1s,2s, etc.
            bowler.setRunsGiven(bowler.getRunsGiven() + Integer.parseInt(outcome.getValue()));

            over.setTotalRunsInOver(over.getTotalRunsInOver() + Integer.parseInt(outcome.getValue()));

            if (!(outcome == RandomOutputOfBall.ZERO || outcome == RandomOutputOfBall.TWO ||
                    outcome == RandomOutputOfBall.FOUR || outcome == RandomOutputOfBall.SIX)) {
                    strikeRotate();
            }
        }
        bowler.setBallsDelivered(bowler.getBallsDelivered() + 1);
    }

    //for an inning, made a method which is responsible for conducting overs of an inning, in it the bowlers are changed, ballOutcome method is called repeatedly (also nextPlayer variable is passed in it so that which player will come to bat next can be tracked) . It is also responsible for change of strike after every over, and also prints score after every over and detailed info at the end of the inning
    public void inning(boolean secondInning) {
        onStrike = battingTeam.getMember().get(0);
        nonStrike = battingTeam.getMember().get(1);
        int bowlAt = 0, i, j;
        for (i = 0; i < OVERS && battingTeam.getWickets() < 10; i++) {
            bowler = bowlingTeam.getMember().get(Team.NUMBER_OF_PLAYERS_IN_A_TEAM - 1 - bowlAt);
            OverDetails over = new OverDetails(i, bowler.getInAt());
            for (j = 0; j < 6 && battingTeam.getWickets() < 10; j++) {
                ballOutcome(i * 6 + j + 1, secondInning, over);

                if (secondInning && (battingTeam.getScore() > bowlingTeam.getScore())) {
                    break;
                }

            }

            scoreBoard.setOverData(over, secondInning);

            if (secondInning && (battingTeam.getScore() > bowlingTeam.getScore())) {
                break;
            }
            strikeRotate();
            bowlAt = (bowlAt + 1) % 6;

        }
        System.out.println("Score of " + battingTeam.getName() + ": " + battingTeam.getScore() + "/" + battingTeam.getWickets() + "\n");
        for (Player currentPlayer : battingTeam.getMember())     //code for strikeRate, only for those who got batting
        {
            if (currentPlayer.getBallsFaced() != 0) {
                float strikeRate = ((currentPlayer.getRunsScored() / (float) currentPlayer.getBallsFaced()) * 100);
                currentPlayer.setStrikeRate(strikeRate);
            }

        }
        for (Player currentPlayer : bowlingTeam.getMember())     //code for economy, only for those who got bowling
        {
            if (currentPlayer.getBallsDelivered() > 0) {
                float economy = (currentPlayer.getRunsGiven() / (float) currentPlayer.getBallsDelivered()) * 100;
                currentPlayer.setEconomy(economy);
            }

        }
    }

    //in match method, called inning method twice for both innings (although with slight variations so that in second inning the target is also considered and the game stops on that ball when target is achieved). Moreover, printing the final result here after due comparison of stats.
    public void startMatch() {
        //team 1 batting starts
        System.out.println(battingTeam.getName() + " batting starts");
        inning(false);          //calling inning method for batting of team1, passing false in it as it is NOT second inning
        Team tempTeam = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = tempTeam;
        //team 2 batting starts
        System.out.println(battingTeam.getName() + " batting starts");
        inning(true);           ////calling inning method for batting of team2, passing true in it as it is NOT second inning

    }

    public void printResult() {
        System.out.println("And the final result is:");
        if (bowlingTeam.getScore() > battingTeam.getScore()) {
            System.out.println(bowlingTeam.getName() + " Won");
        } else if (bowlingTeam.getScore() < battingTeam.getScore()) {
            System.out.println(battingTeam.getName() + " Won");
        } else {
            System.out.println("Its a Tie");
        }
    }

    public void strikeRotate() {
        Player tempPlayer;
        tempPlayer = onStrike;
        onStrike = nonStrike;
        nonStrike = tempPlayer;
    }


}