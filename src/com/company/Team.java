package com.company;

import java.util.ArrayList;

public class Team {

    private int teamId;
    private String name;
    private int score=0;
    private int wickets;
    public static final int NUMBER_OF_PLAYERS_IN_A_TEAM = 11;
    private ArrayList<Player> member = new ArrayList<>();

    public void setId(int teamId)
    {
        this.teamId = teamId;
    }
    public int getId() {
        return teamId;
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

    public int getWickets(){
        return wickets;
    }
    public void setWickets(int wickets){
        this.wickets = wickets;
    }

    public void setupPlayers(int idCode){
        for(int i=0;i<NUMBER_OF_PLAYERS_IN_A_TEAM;i++)
        {
            Player p = new Player();
            p.setName( name + (i+1) );    //for the sake of convenience, player name is declared as team name plus integer. eg: CSK1,CSK2...
            p.setPlayerId( idCode*10 + (i+1));
            p.setInAt(i+1);
            if(i<5)
            {
                p.setRole(PlayerRole.BATTER);
            }
            else if(i<7)
            {
                p.setRole(PlayerRole.ALL_ROUNDER);
            }
            else
            {
                p.setRole(PlayerRole.BOWLER );
            }
            member.add(p);
        }
    }

    public ArrayList<Player> getMember() {
        return member;
    }

}
