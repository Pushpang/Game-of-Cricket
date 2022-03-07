package com.company.services;

import com.company.beans.Game;
import com.company.beans.Player;
import com.company.beans.Team;
import com.company.CONSTANTS.Constants;
import com.company.enums.RandomOutputOfBall;
import com.company.repository.TeamRepository;
import com.company.repository.TeamRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.*;


@Service
public class GameServicesImp implements GameServices{


    @Override
    public void setupTeams(Game game, int team1Id,int team2Id,String team1Name,String team2Name,Connection con) {
        game.getScoreBoard().setMatchId(game.getMatchId());
        Team team1 = new Team(),team2 = new Team();

        TeamRepository fetchingTeams = new TeamRepositoryImpl();


        if (fetchingTeams.teamCheckInDB(con,team1Id)  != 0) {
            team1 = fetchingTeams.fetchingTeamFromDB(team1Id);
        }
        else{
            team1.setId(team1Id);
            team1.setName(team1Name);
            team1.setupPlayers();
        }


        if (fetchingTeams.teamCheckInDB(con,team2Id)  != 0) {
            team2 = fetchingTeams.fetchingTeamFromDB(team2Id);
        }
        else{
            team2.setId(team2Id);
            team2.setName(team2Name);
            team2.setupPlayers();
        }


        game.setTeam1(team1);
        game.setTeam2(team2);

    }

    @Override
    public void toss(Game game) {
        int tossVar = (int) (Math.random() * 2);
        if (tossVar == 1)
        {
            Team t = game.getTeam2();
            game.setTeam2(game.getTeam1());
            game.setTeam1(t);
        }
        game.getScoreBoard().setTossWinningTeam(game.getTeam1().getId());
        game.getScoreBoard().setFirstBattingTeamId(game.getTeam1().getId());
        game.getScoreBoard().setSecondBattingTeamId(game.getTeam2().getId());

    }

    @Override
    public void startMatch(Game game) {
        inning(game,false);

        inning(game,true);

    }
    public void inning(Game game,boolean secondInning) {
        Team battingTeam = game.getTeam1();
        Team bowlingTeam = game.getTeam2();
        if(secondInning)
        {
            battingTeam = game.getTeam2();
            bowlingTeam = game.getTeam1();
        }
        Player onStrike = new Player();
        Player nonStrike = new Player();

        onStrike = battingTeam.getMember().get(0);
        nonStrike = battingTeam.getMember().get(1);
        Player bowler;
        int bowlAt = 0, i, j;
        for (i = 0; i < Constants.OVERS && battingTeam.getWickets() < 10; i++) {
            bowler = bowlingTeam.getMember().get(Constants.NUMBER_OF_PLAYERS_IN_A_TEAM - 1 - bowlAt);

            for (j = 0; j < 6 && battingTeam.getWickets() < 10; j++) {
                RandomOutputOfBall outcome = ballOutcome(game,i*6 + j + 1,onStrike,nonStrike,bowler,battingTeam);

                if (outcome == RandomOutputOfBall.WICKET) {
                    battingTeam.setWickets(battingTeam.getWickets() + 1);

                    if (battingTeam.getWickets() < 10) {
                        onStrike = battingTeam.getMember().get(Math.max(onStrike.getInAt(), nonStrike.getInAt()));
                    }

                } else {
                    battingTeam.setScore(battingTeam.getScore() + Integer.parseInt(outcome.getValue()));

                    if (!(outcome == RandomOutputOfBall.ZERO || outcome == RandomOutputOfBall.TWO ||
                            outcome == RandomOutputOfBall.FOUR || outcome == RandomOutputOfBall.SIX)) {
                       // strikeRotate(onStrike,nonStrike);
                        Player tempPlayer;
                        tempPlayer = onStrike;
                        onStrike = nonStrike;
                        nonStrike = tempPlayer;

                    }
                }
            }


            if (secondInning && (battingTeam.getScore() > bowlingTeam.getScore())) {
                break;
            }
            //strikeRotate(onStrike,nonStrike);
            Player tempPlayer;
            tempPlayer = onStrike;
            onStrike = nonStrike;
            nonStrike = tempPlayer;
            bowlAt = (bowlAt + 1) % 6;

        }

        result(game);


    }
    public RandomOutputOfBall ballOutcome(Game game,int ballCount,Player onStrike,Player nonStrike,Player bowler,Team battingTeam ) {

        RandomOutputOfBall outcome = RandomOutputOfBall.randomOutcome(onStrike);
        game.getScoreBoard().setOutcome(ballCount,battingTeam.getId(), onStrike.getPlayerId(), bowler.getPlayerId(), outcome);


        return outcome;
    }

    // ?? THIS FUNCTION DECLARED OUTSIDE IS NOT WORKING
//    public void strikeRotate(Player onStrike,Player nonStrike) {
//        Player tempPlayer;
//        tempPlayer = onStrike;
//        onStrike = nonStrike;
//        nonStrike = tempPlayer;
//    }

    public void result(Game game){
        if(game.getTeam1().getScore()>game.getTeam2().getScore())
        {
            game.setWinnerTeamID(game.getTeam1().getId());
        }
        else if(game.getTeam1().getScore()<game.getTeam2().getScore())
        {
            game.setWinnerTeamID(game.getTeam2().getId());
        }
        else{
            game.setWinnerTeamID(-1);
        }


    }

}
