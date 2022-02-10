package com.company;

import java.util.ArrayList;

public class Team {
    private int teamId;
    private String name;
    private int noOfPlayers;
    private int score=0;
    private int notOut;
    ArrayList<Player> member = new ArrayList<>();

    public void setId(int id)
    {
        teamId = id;
    }
    public int getId() {
        return teamId;
    }
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
    public int getNotOut(){
        return notOut;
    }
    public void setNotOut(int num){
        notOut = num;
    }

    public void setupPlayers(int idCode){
        for(int i=0;i<noOfPlayers;i++)
        {
            Player p = new Player();
            p.name = name + (i+1);
            p.playerId =  idCode*10 + i+1;
            if(i<5)
            {
                p.role = "Batsman";
            }
            else if(i<7)
            {
                p.role = "All-rounder";
            }
            else
            {
                p.role = "Bowler";
            }
            member.add(p);
        }
    }

}
