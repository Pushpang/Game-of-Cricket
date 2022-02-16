package com.company;

public class GameLaunch {
    public static void main(String[] args)
    {
        Game iplGame = new Game(37);  //passing matchID having value 37
        iplGame.setupTeams();
        iplGame.toss();
        iplGame.startMatch();
        iplGame.printResult();
        iplGame.printScoreBoardPerBall();

    }

}
