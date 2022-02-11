package com.company;

public class GameLaunch {
    public static void main(String[] args)
    {
        Game iplGame = new Game();
        iplGame.setupTeams();
        iplGame.toss();
        iplGame.match();

    }

}
