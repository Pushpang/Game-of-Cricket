package com.company;


public class Game {

    private Team battingTeam,bowlingTeam;
    public static final int OVERS = 5;
    private Player onStrike,nonStrike,bowler;

    // setting up both the teams
    public void setupTeams(){
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

    //for a single ball, made a ballOutcome method which stores the particular ball outcome to players profile, prints result per ball
    public void ballOutcome(){
        String outcome = randomOutcome();
        if (outcome.equals("W")) {
            battingTeam.setWickets(battingTeam.getWickets() + 1);
            System.out.println(onStrike.getName() + " is OUT");
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

            System.out.println("Score on this ball: " + outcome + "run/s");
        }
        else {
            battingTeam.setScore(battingTeam.getScore() + Integer.parseInt(outcome));
            onStrike.setRunsScored(onStrike.getRunsScored() + Integer.parseInt(outcome) );
            onStrike.setBallsFaced(onStrike.getBallsFaced() + 1);
            bowler.setRunsGiven(bowler.getRunsGiven() + Integer.parseInt(outcome));

            System.out.println("Score on this ball: " + outcome + "run/s");
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
                ballOutcome();
                if(secondInning && (battingTeam.getScore()>bowlingTeam.getScore()) )
                {
                    break;
                }

            }

            System.out.println("****");
            if(j==6)
                System.out.println("Score after "+(i+1)+"."+(bowler.getBallsDelivered())%6+" over(s) is "+ battingTeam.getScore() );
            else
                System.out.println("Score after "+(i)+"."+(bowler.getBallsDelivered())%6+" over(s) is "+ battingTeam.getScore() );

            System.out.println(onStrike.getName() + " : " + onStrike.getRunsScored()+"("+ onStrike.getBallsFaced()+")");
            System.out.println(nonStrike.getName() + " : " + nonStrike.getRunsScored()+"("+nonStrike.getBallsFaced()+")");
            System.out.println("****");
            if(secondInning && (battingTeam.getScore()>bowlingTeam.getScore()) )
            {
                break;
            }
            strikeRotate();
            bowlAt =(bowlAt+1)%6;

        }

        System.out.println("...................");
        System.out.println(battingTeam.getName() + " SCORE IS : " + battingTeam.getScore()+"/"+(battingTeam.getWickets()));
        System.out.println("...................");
        for(Player currentPlayer : battingTeam.getMember())
        {
            System.out.print(currentPlayer.getName() + " scored " + currentPlayer.getRunsScored()
                    + " and faced " + currentPlayer.getBallsFaced()+" balls");
            if(currentPlayer.getBallsFaced()!=0)
            {
                float strikeRate =( (currentPlayer.getRunsScored()/(float)currentPlayer.getBallsFaced()) * 100 );

                System.out.print(" with a strike rate of ");
                System.out.printf("%.2f", strikeRate);
                System.out.print("%");

            }
            System.out.println("");

        }
        System.out.println("");
        for(Player currentPlayer : bowlingTeam.getMember())
        {
            if(currentPlayer.getBallsDelivered()>0){
                float economy = (currentPlayer.getRunsGiven()/(float)currentPlayer.getBallsDelivered())*100;
                System.out.print(currentPlayer.getName() + " took " + currentPlayer.getWicketsTaken()
                        + " wickets and delivered " + currentPlayer.getBallsDelivered() + " balls with an economy of ");
                System.out.printf("%.2f",economy);
                System.out.print("%");
                System.out.println("");
            }

        }
    }

    //in match method, called inning method twice for both innings (although with slight variations so that in second inning the target is also considered and the game stops on that ball when target is achieved). Moreover, printing the final result here after due comparison of stats.
    public void startMatch()
    {

        //team 1 batting starts
        System.out.println(battingTeam.getName()+" batting starts");
        System.out.println("");
        inning(false);          //calling inning method for batting of team1, passing false in it as it is NOT second inning
        Team tempTeam = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = tempTeam;
        System.out.println("");
        //team 2 batting starts
        System.out.println(battingTeam.getName() + " batting starts");
        System.out.println("");
        inning(true);           ////calling inning method for batting of team2, passing true in it as it is NOT second inning

        System.out.println("");
        System.out.println("^^^^");
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
        System.out.println("^^^^");

    }

    public void strikeRotate()
    {
        Player tempPlayer;
        tempPlayer = onStrike;
        onStrike = nonStrike;
        nonStrike= tempPlayer;
    }

    public String randomOutcome(){
        String[] outcomes = {"0","1","2","3","4","6","W"};
        return outcomes[(int) (Math.random()*7)];
    }
}
