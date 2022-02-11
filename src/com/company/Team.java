package com.company;

import java.util.ArrayList;

public class Team {
    private int teamId;
    private String name;
    private int noOfPlayers;
    private int score=0;
    private int notOut;
    private ArrayList<Player> member = new ArrayList<>();

    public void setId(int teamId)
    {
        this.teamId = teamId;
    }
    public int getId() {
        return teamId;
    }

    public void setPlayers(int noOfPlayers){
        this.noOfPlayers = noOfPlayers;
    }
    public int getPlayers(){
        return noOfPlayers;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    public int getNotOut(){
        return notOut;
    }
    public void setNotOut(int notOut){
        this.notOut = notOut;
    }

    public void setupPlayers(int idCode){
        for(int i=0;i<noOfPlayers;i++)
        {
            Player p = new Player();
            p.setName( name + (i+1) );    //for the sake of convenience, player name is declared as team name plus integer. eg: CSK1,CSK2...
            p.setPlayerId( idCode*10 + i+1);
            p.setInAt(i+1);
            if(i<5)
            {
                p.setRole("Batsman");
            }
            else if(i<7)
            {
                p.setRole("All-Rounder");
            }
            else
            {
                p.setRole("Bowler");
            }
            member.add(p);
        }
    }

    public ArrayList<Player> getMember() {
        return member;
    }

    public void setMember(ArrayList<Player> member) {
        this.member = member;
    }
}
