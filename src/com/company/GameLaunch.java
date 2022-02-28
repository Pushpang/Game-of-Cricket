package com.company;


import java.util.Scanner;

public class GameLaunch {
    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter match ID: ");
        int matchId = sc.nextInt();
        Game iplGame = new Game(matchId);  //passing matchID having value 37
        iplGame.setupTeams();
        iplGame.toss();
        iplGame.startMatch();
        iplGame.printResult();
        iplGame.printingScore();
        iplGame.insertIntoDB();
    }

}
