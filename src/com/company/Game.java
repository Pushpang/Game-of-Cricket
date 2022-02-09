package com.company;

public class Game {
    Team team1 = new Team();
    Team team2 = new Team();

    public void setupTeams(){

        team1.setName("CSK");
        team2.setName("MI");
        team1.setPlayers(11) ;
        team2.setPlayers(11) ;
        System.out.println("Team 1 name: " + team1.getName());
        System.out.println("Team 2 name: " + team2.getName());
    }
    public void match()
    {
        int team1_w = team1.getPlayers(), team2_w = team2.getPlayers();
        int noOfBalls=30;

        while(noOfBalls>0 && team1_w>1)
        {
            String outcome = randomOutcome();
            if(outcome.equals("W"))
            {
                team1_w--;
                System.out.println("And it is OUT");
            }
            else
            {
                team1.setScore(team1.getScore() + Integer.parseInt(outcome));
                System.out.println("Score on this ball: "+outcome + "run/s");
            }
            noOfBalls--;
        }
        System.out.println(team1.getPlayers());
        System.out.println("...................");
        System.out.println(team1.getName() + " SCORE IS : " + team1.getScore());
        System.out.println("...................");

        noOfBalls = 30;

        while(noOfBalls>0 && team2_w>1)
        {
            String outcome = randomOutcome();
            if(outcome.equals("W"))
            {
                team2_w--;
                System.out.println("And it is OUT");
            }
            else
            {
                team2.setScore(team2.getScore() + Integer.parseInt(outcome));
                System.out.println("Score on this ball: "+outcome + "run/s");
            }
            noOfBalls--;
        }
        System.out.println("...................");
        System.out.println(team2.getName() + "SCORE IS : " + team2.getScore());
        System.out.println("...................");


        if(team1.getScore()>team2.getScore())
        {
            System.out.println(team1.getName() + " Won");
        }
        else if(team1.getScore()<team2.getScore())
        {
            System.out.println(team2.getName() + " Won");
        }
        else
        {
            System.out.println("Its a Tie");
        }

    }

    public String randomOutcome(){
        String[] outcomes = {"0","1","2","3","4","6","W"};
        return outcomes[(int) (Math.random()*7)];
    }
}
