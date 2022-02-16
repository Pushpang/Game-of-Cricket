package com.company;


public class Game {
    private int matchId;
    private Team battingTeam,bowlingTeam;
    public static final int OVERS = 5;
    private Player onStrike,nonStrike,bowler;
    private DetailedScoreSheet scoreBoardPerBall = new DetailedScoreSheet();
    public int ballCount = 0;

    public Game(int matchId){
        this.matchId = matchId;
    }

    // setting up both the teams
    public void setupTeams(){

        scoreBoardPerBall.setMatchId(matchId);

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
    public void toss(){
        int tossVar = (int)(Math.random()*2);
        if(tossVar==1)      // if random number is 0 then no swap but when it is 1 then swapping the teams
        {
            Team t = battingTeam;
            battingTeam = bowlingTeam;
            bowlingTeam = t;
        }
        System.out.println("");
        System.out.println("Toss is WON by: "+ battingTeam.getName()+" and they choose to bat\n");

    }

    public void storeOutcome( int batterId, int bowlerId, String outcome,boolean secondInning){
        scoreBoardPerBall.setOutcome(ballCount,batterId, bowlerId, outcome,secondInning);
    }

    public void printScoreBoardPerBall(){
        scoreBoardPerBall.printData();
    }

    //for a single ball, made a ballOutcome method which stores the particular ball outcome to players profile, prints result per ball
    public void ballOutcome(boolean secondInning){
        ballCount++;
        String outcome = randomOutcome();
        storeOutcome(onStrike.getPlayerId(),bowler.getPlayerId(),outcome,secondInning);

        if (outcome.equals("W")) {
            battingTeam.setWickets(battingTeam.getWickets() + 1);
            onStrike.setBallsFaced(onStrike.getBallsFaced() + 1);
            bowler.setWicketsTaken(bowler.getWicketsTaken() + 1);

            if(battingTeam.getWickets()<10) {
                onStrike = battingTeam.getMember().get(Math.max(onStrike.getInAt(),nonStrike.getInAt()));
            }

        }
        else if(outcome.equals("0") || outcome.equals("2") || outcome.equals("4") || outcome.equals("6")) {
            battingTeam.setScore(battingTeam.getScore() + Integer.parseInt(outcome));
            onStrike.setRunsScored(onStrike.getRunsScored() + Integer.parseInt(outcome) );
            onStrike.setBallsFaced(onStrike.getBallsFaced() + 1);
            bowler.setRunsGiven(bowler.getRunsGiven() + Integer.parseInt(outcome));

        }
        else {
            battingTeam.setScore(battingTeam.getScore() + Integer.parseInt(outcome));
            onStrike.setRunsScored(onStrike.getRunsScored() + Integer.parseInt(outcome) );
            onStrike.setBallsFaced(onStrike.getBallsFaced() + 1);
            bowler.setRunsGiven(bowler.getRunsGiven() + Integer.parseInt(outcome));

            strikeRotate();
        }
        bowler.setBallsDelivered(bowler.getBallsDelivered() + 1);
    }

    //for an inning, made a method which is responsible for conducting overs of an inning, in it the bowlers are changed, ballOutcome method is called repeatedly (also nextPlayer variable is passed in it so that which player will come to bat next can be tracked) . It is also responsible for change of strike after every over, and also prints score after every over and detailed info at the end of the inning
    public void inning(boolean secondInning){
        onStrike = battingTeam.getMember().get(0);
        nonStrike = battingTeam.getMember().get(1);
        int bowlAt = 0,i,j;
        for( i=0;i<OVERS && battingTeam.getWickets()<10;i++)
        {
            bowler = bowlingTeam.getMember().get(Team.NUMBER_OF_PLAYERS_IN_A_TEAM - 1 - bowlAt);
            for( j=0;j<6 && battingTeam.getWickets()<10;j++) {
                ballOutcome(secondInning);

                if(secondInning && (battingTeam.getScore()>bowlingTeam.getScore()) )
                {
                    break;
                }

            }

            if(secondInning && (battingTeam.getScore()>bowlingTeam.getScore()) )
            {
                break;
            }
            strikeRotate();
            bowlAt =(bowlAt+1)%6;

        }
        System.out.println("Score of "+battingTeam.getName()+": "+battingTeam.getScore()+"/"+battingTeam.getWickets()+"\n");
        for(Player currentPlayer : battingTeam.getMember())     //code for strikeRate, only for those who got batting
        {
            if(currentPlayer.getBallsFaced()!=0)
            {
                float strikeRate =( (currentPlayer.getRunsScored()/(float)currentPlayer.getBallsFaced()) * 100 );
                currentPlayer.setStrikeRate(strikeRate);
            }

        }
        for(Player currentPlayer : bowlingTeam.getMember())     //code for economy, only for those who got bowling
        {
            if(currentPlayer.getBallsDelivered()>0){
                float economy = (currentPlayer.getRunsGiven()/(float)currentPlayer.getBallsDelivered())*100;
                currentPlayer.setEconomy(economy);
            }

        }
    }

    //in match method, called inning method twice for both innings (although with slight variations so that in second inning the target is also considered and the game stops on that ball when target is achieved). Moreover, printing the final result here after due comparison of stats.
    public void startMatch()
    {
        //team 1 batting starts
        System.out.println(battingTeam.getName()+" batting starts");
        inning(false);          //calling inning method for batting of team1, passing false in it as it is NOT second inning
        Team tempTeam = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = tempTeam;
        //team 2 batting starts
        System.out.println(battingTeam.getName() + " batting starts");
        inning(true);           ////calling inning method for batting of team2, passing true in it as it is NOT second inning

    }
    public void printResult(){
        System.out.println("And the final result is:");
        if(bowlingTeam.getScore()>battingTeam.getScore())
        {
            System.out.println(bowlingTeam.getName() + " Won");
        }
        else if(bowlingTeam.getScore()<battingTeam.getScore())
        {
            System.out.println(battingTeam.getName() + " Won");
        }
        else
        {
            System.out.println("Its a Tie");
        }
    }

    public void strikeRotate()
    {
        Player tempPlayer;
        tempPlayer = onStrike;
        onStrike = nonStrike;
        nonStrike= tempPlayer;
    }

    public String randomOutcome(){              //changed the outcomes array according to the role of player on strike
        String[] outcomes ;
        if(onStrike.getRole().equals("Batter"))
        {
            outcomes= new String[] {"0","1","2","3","4","6",
                    "0","1","2","3","4","6",
                    "0","1","2","W"};
        }
        else if(onStrike.getRole().equals("All-Rounder"))
        {
            outcomes= new String[] {"0","1","2","3","4","6",
                    "0","1","2","3","W"};
        }
        else
        outcomes= new String[] {"0","1","2","3","4","W","W"};

        return outcomes[(int) (Math.random()*outcomes.length)];
    }
}
