package com.company;


import com.company.JDBCclasses.*;
import com.company.Utility.UtilityClass;
import com.company.beans.Game;
import com.company.services.GameServices;
import com.company.services.GameServicesImp;

import java.sql.*;
import java.util.Scanner;

public class GameLaunch {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        Connection con = UtilityClass.getConnection();
        int matchId=0;


        GameServices gameServices = new GameServicesImp() ;

        matchId = gameServices.matchIdSetup(con,sc);
        Game iplGame = new Game(matchId);
        gameServices.setupTeams(iplGame,con,sc);
        gameServices.toss(iplGame);
        gameServices.startMatch(iplGame);
        iplGame.printResult();



        // classes used here are for inserting data in DB
        DAOCricket insertIntoDB = new DAOCricketImp();
        insertIntoDB.insertIntoTeamsInfoAndPlayersInfo(iplGame,con);
        insertIntoDB.insetIntoMatchStats(iplGame,con);
        insertIntoDB.insertIntoPerBallStats(iplGame.getScoreBoard(),con);

        try {
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
