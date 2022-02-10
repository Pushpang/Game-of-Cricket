package com.company;

import java.util.ArrayList;

public class Game {

    ArrayList<Team> teams= new ArrayList<>();
    int overs = 6;
    public void setupTeams(){
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setId(1001);
        team2.setId(1002);
        team1.setName("CSK");
        team2.setName("MI");
        team1.setPlayers(11) ;
        team2.setPlayers(11) ;
        team1.setNotOut(11);
        team2.setNotOut(11);
        System.out.println("Team 1 name: " + team1.getName());
        System.out.println("Team 2 name: " + team2.getName());
        team1.setupPlayers(101);
        team2.setupPlayers(201);
        teams.add(team1);
        teams.add(team2);

    }



    public void match()
    {
        //int team1_w = team1.getPlayers(), team2_w = team2.getPlayers();
        int totalBalls = overs*6;
        int tossVar = toss();
        if(tossVar==1)
        {
            Team t = teams.get(0);
            teams.remove(0);
            teams.add(t);
        }
        System.out.println("Toss is WON by: "+teams.get(0).getName()+" and they choose to bat");
        System.out.println("");
        //int strike=0,nonStrike=0,playerNext=3;
        //team 1 batting starts
        onCrease strike = new onCrease(0,0,0,1);
        onCrease nonStrike = new onCrease(0,0,0,2);
        onCrease temp;
        int next = 3, bowlAt = 0,prevRuns=0,i,j;
        for( i=0;i<overs && teams.get(0).getNotOut()>1;i++)
        {
            for( j=0;j<6;j++) {
                String outcome = randomOutcome();
                if (outcome.equals("W")) {
                    teams.get(0).setNotOut(teams.get(0).getNotOut() - 1);
                    System.out.println(teams.get(0).member.get(strike.inAt -1 ).name + " is OUT");
                    teams.get(0).member.get(strike.inAt -1).runsScored = strike.runs;
                    teams.get(0).member.get(strike.inAt -1).ballsFaced = strike.ballFaced + 1;
                    teams.get(1).member.get(10-bowlAt).wicketsTaken ++;
                    if(teams.get(0).getNotOut()==1)
                    {
                        break;
                    }
                    strike = new onCrease(teams.get(0).member.get(next -1).playerId,0,0,next);
                    next++;
                }
                else if(outcome.equals("0") || outcome.equals("2") || outcome.equals("4") || outcome.equals("6")) {
                    teams.get(0).setScore(teams.get(0).getScore() + Integer.parseInt(outcome));
                    strike.runs += Integer.parseInt(outcome);
                    strike.ballFaced++;

                    System.out.println("Score on this ball: " + outcome + "run/s");
                }
                else {
                    teams.get(0).setScore(teams.get(0).getScore() + Integer.parseInt(outcome));
                    strike.runs += Integer.parseInt(outcome);
                    strike.ballFaced++;

                    System.out.println("Score on this ball: " + outcome + "run/s");
                    temp =  strike;
                    strike = nonStrike;
                    nonStrike = temp;
                }

                totalBalls--;
            }
            if(teams.get(0).getNotOut()==1){
                teams.get(1).member.get(10-bowlAt).ballsDelivered += (j+1);
                System.out.println("****");
                System.out.println("Score after "+i+"."+(j+1)+" over(s) is "+ teams.get(0).getScore() );
                System.out.println(teams.get(0).member.get(strike.inAt - 1).name + " : " + strike.runs+"("+strike.ballFaced+")");
                System.out.println(teams.get(0).member.get(nonStrike.inAt - 1).name + " : " + nonStrike.runs+"("+nonStrike.ballFaced+")");
                System.out.println("****");
                break;
            }
            temp =  strike;
            strike = nonStrike;
            nonStrike = temp;
            System.out.println("****");
            System.out.println("Score after "+(i+1)+" over(s) is "+ teams.get(0).getScore() );
            System.out.println(teams.get(0).member.get(strike.inAt - 1).name + " : " + strike.runs+"("+strike.ballFaced+")");
            System.out.println(teams.get(0).member.get(nonStrike.inAt - 1).name + " : " + nonStrike.runs+"("+nonStrike.ballFaced+")");
            System.out.println("****");

            teams.get(1).member.get(10 - bowlAt).runsGiven = teams.get(0).getScore() - prevRuns;
            prevRuns = teams.get(0).getScore();
            teams.get(1).member.get(10 - bowlAt).ballsDelivered += 6;

            bowlAt =(bowlAt+1)%6;

        }
        teams.get(0).member.get(strike.inAt -1).runsScored = strike.runs;
        teams.get(0).member.get(strike.inAt -1).ballsFaced = strike.ballFaced;
        teams.get(0).member.get(nonStrike.inAt -1).runsScored = nonStrike.runs;
        teams.get(0).member.get(nonStrike.inAt -1).ballsFaced = nonStrike.ballFaced;

        System.out.println("...................");
        System.out.println(teams.get(0).getName() + " SCORE IS : " + teams.get(0).getScore()+"/"+(11-teams.get(0).getNotOut()));
        System.out.println("...................");
        for(Player currentPlayer : teams.get(0).member)
        {
            System.out.println(currentPlayer.name + " scored " + currentPlayer.runsScored
                    + " and faced " + currentPlayer.ballsFaced + " balls");
        }
        System.out.println("");
        for(Player currentPlayer : teams.get(1).member)
        {
            if(currentPlayer.ballsDelivered>0)
            System.out.println(currentPlayer.name + " took " + currentPlayer.wicketsTaken
                    + " wickets and delivered " + currentPlayer.ballsDelivered + " balls");
        }
        //team 1 batting ends
        //team 2 batting starts
        System.out.println("");
        System.out.println(teams.get(1).getName()+" batting starts and they have to chase "+teams.get(0).getScore()+" runs");
        System.out.println("");


        strike = new onCrease(0,0,0,1);
        nonStrike = new onCrease(0,0,0,2);

        next = 3;
        bowlAt = 0;
        prevRuns=0;
        for( i=0;i<overs && teams.get(1).getNotOut()>1;i++)
        {
            for( j=0;j<6;j++) {
                String outcome = randomOutcome();
                if (outcome.equals("W")) {
                    teams.get(1).setNotOut(teams.get(1).getNotOut() - 1);
                    System.out.println(teams.get(1).member.get(strike.inAt -1 ).name + " is OUT");
                    teams.get(1).member.get(strike.inAt -1).runsScored = strike.runs;
                    teams.get(1).member.get(strike.inAt -1).ballsFaced = strike.ballFaced + 1;
                    teams.get(0).member.get(10-bowlAt).wicketsTaken ++;
                    if(teams.get(1).getNotOut()==1)
                    {
                        break;
                    }
                    strike = new onCrease(teams.get(1).member.get(next -1).playerId,0,0,next);
                    next++;
                }
                else if(outcome.equals("0") || outcome.equals("2") || outcome.equals("4") || outcome.equals("6")) {
                    teams.get(1).setScore(teams.get(1).getScore() + Integer.parseInt(outcome));
                    strike.runs += Integer.parseInt(outcome);
                    strike.ballFaced++;

                    System.out.println("Score on this ball: " + outcome + "run/s");
                    if(teams.get(1).getScore()>teams.get(0).getScore())
                        break;

                }
                else {
                    teams.get(1).setScore(teams.get(1).getScore() + Integer.parseInt(outcome));
                    strike.runs += Integer.parseInt(outcome);
                    strike.ballFaced++;

                    System.out.println("Score on this ball: " + outcome + "run/s");
                    if(teams.get(1).getScore()>teams.get(0).getScore())
                        break;

                    temp =  strike;
                    strike = nonStrike;
                    nonStrike = temp;
                }

                totalBalls--;
            }
            if(teams.get(1).getNotOut()==1 || teams.get(1).getScore()>teams.get(0).getScore()){
                teams.get(0).member.get(10-bowlAt).ballsDelivered += (j+1);
                System.out.println("****");
                System.out.println("Score after "+i+"."+(j+1)+" over(s) is "+ teams.get(1).getScore() );
                System.out.println(teams.get(1).member.get(strike.inAt - 1).name + " : " + strike.runs+"("+strike.ballFaced+")");
                System.out.println(teams.get(1).member.get(nonStrike.inAt - 1).name + " : " + nonStrike.runs+"("+nonStrike.ballFaced+")");
                System.out.println("****");
                break;
            }
            temp =  strike;
            strike = nonStrike;
            nonStrike = temp;
            System.out.println("****");
            System.out.println("Score after "+(i+1)+" over(s) is "+ teams.get(1).getScore() );
            System.out.println(teams.get(1).member.get(strike.inAt - 1).name + " : " + strike.runs+"("+strike.ballFaced+")");
            System.out.println(teams.get(1).member.get(nonStrike.inAt - 1).name + " : " + nonStrike.runs+"("+nonStrike.ballFaced+")");
            System.out.println("****");

            teams.get(0).member.get(10 - bowlAt).runsGiven = teams.get(1).getScore() - prevRuns;
            prevRuns = teams.get(1).getScore();
            teams.get(0).member.get(10 - bowlAt).ballsDelivered += 6;

            bowlAt =(bowlAt+1)%6;

        }
        teams.get(1).member.get(strike.inAt -1).runsScored = strike.runs;
        teams.get(1).member.get(strike.inAt -1).ballsFaced = strike.ballFaced;
        teams.get(1).member.get(nonStrike.inAt -1).runsScored = nonStrike.runs;
        teams.get(1).member.get(nonStrike.inAt -1).ballsFaced = nonStrike.ballFaced;

        System.out.println("...................");
        System.out.println(teams.get(1).getName() + " SCORE IS : " + teams.get(1).getScore()+"/"+(11-teams.get(1).getNotOut()));
        System.out.println("...................");
        for(Player currentPlayer : teams.get(1).member)
        {
            System.out.println(currentPlayer.name + " scored " + currentPlayer.runsScored
                    + " and faced " + currentPlayer.ballsFaced + " balls");
        }
        System.out.println("");
        for(Player currentPlayer : teams.get(0).member)
        {
            if(currentPlayer.ballsDelivered>0)
                System.out.println(currentPlayer.name + " took " + currentPlayer.wicketsTaken
                        + " wickets and delivered " + currentPlayer.ballsDelivered + " balls");
        }
        //team2 batting ends
        System.out.println("");
        System.out.println("And the final result is:");
        System.out.println("");
        if(teams.get(1).getScore()>teams.get(0).getScore())
        {
            System.out.println(teams.get(1).getName() + " Won");
        }
        else if(teams.get(1).getScore()<teams.get(0).getScore())
        {
            System.out.println(teams.get(0).getName() + " Won");
        }
        else
        {
            System.out.println("Its a Tie");
        }

    }
    public int toss(){
        return (int)(Math.random()*2);
    }
    public String randomOutcome(){
        String[] outcomes = {"0","1","2","3","4","6","W"};
        return outcomes[(int) (Math.random()*7)];
    }
}
