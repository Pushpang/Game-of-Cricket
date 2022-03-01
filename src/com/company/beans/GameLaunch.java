package com.company.beans;


import com.company.JDBCclasses.*;

import java.sql.*;
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

        Connection con =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/testDB";
            String userName = "root";
            String password = "Tekion@123";
            con = DriverManager.getConnection(url,userName,password);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        // classes used here are for inserting data in DB
        TeamsInfo.insertIntoTeamsInfo(iplGame,con);
        PlayersInfo.insertIntoPlayersInfo(iplGame,con);
        MatchStats.insetIntoMatchStats(iplGame,con);
        TeamsStats.insertIntoTeamStats(iplGame,con);
        PlayersStats.insertIntoPlayersStats(iplGame,con);
        PerOverStats.insertIntoPerOverSTATS(iplGame.getScoreBoard(),con);
        PerBallStats.insertIntoPerBallStats(iplGame.getScoreBoard(),con);

        try {
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
