package com.company;

public class Team {
    private String name;
    private int noOfPlayers;
    private int score=0;
    public void setPlayers(int n){
        noOfPlayers = n;
    }
    public int getPlayers(){
        return noOfPlayers;
    }

    public void setName(String nameTeam)
    {
        name = nameTeam;
    }
    public String getName(){
        return name;
    }

    public void setScore(int runs){
        score = runs;
    }
    public int getScore(){
        return score;
    }

}
