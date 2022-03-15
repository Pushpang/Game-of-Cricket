package com.company.services;

import com.company.beans.Game;
import com.company.beans.ResultOnSingleBall;
import com.company.CONSTANTS.Constants;
import com.company.beans.PlayersInfo;
import com.company.beans.TeamInfo;
import com.company.enums.PlayerRole;
import com.company.enums.RandomOutputOfBall;
import com.company.repository.PlayerRepository;
import com.company.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;


@Service
public class GameServicesImp implements GameServices{

    @Autowired
    private TeamRepository fetchingTeams;
    @Autowired
    private PlayerRepository fetchingPlayers;
    @Override
    public void setupTeams(Game game, int team1Id,int team2Id,String team1Name,String team2Name,Connection con) {
        TeamInfo team1Info,team2Info;
        ArrayList<PlayersInfo> playersInfoTeam1,playersInfoTeam2;


        if (fetchingTeams.teamCheckInDB(con,team1Id)  != 0) {
            team1Info = fetchingTeams.fetchingTeamInfoFromDB(team1Id);
            playersInfoTeam1 = fetchingPlayers.fetchingPlayersOfTeam(team1Id);
        }
        else{
            team1Info = new TeamInfo();
            team1Info.setTeamId(team1Id);
            team1Info.setTeamName(team1Name);
            playersInfoTeam1 = new ArrayList<>();
            playersInfoTeam1 = setupNewPlayers(team1Info);
        }


        if (fetchingTeams.teamCheckInDB(con,team2Id)  != 0) {
            team2Info = fetchingTeams.fetchingTeamInfoFromDB(team2Id);
            playersInfoTeam2 = fetchingPlayers.fetchingPlayersOfTeam(team2Id);
        }
        else{
            team2Info = new TeamInfo();
            team2Info.setTeamId(team2Id);
            team2Info.setTeamName(team2Name);
            playersInfoTeam2 = new ArrayList<>();
            playersInfoTeam2 = setupNewPlayers(team2Info);
        }


        game.setTeam1Info(team1Info);
        game.setTeam2Info(team2Info);
        game.setPlayersTeam1(playersInfoTeam1);
        game.setPlayersTeam2(playersInfoTeam2);

    }
    @Override
    public ArrayList<PlayersInfo> setupNewPlayers(TeamInfo teamInfo) {
        ArrayList<PlayersInfo> playersOfTeam = new ArrayList<>();

        for(int i = 0; i< Constants.NUMBER_OF_PLAYERS_IN_A_TEAM; i++)
        {
            PlayersInfo p = new PlayersInfo();
            p.setPlayerId( (teamInfo.getTeamId()*100 + (i+1)));
            p.setTeamId(teamInfo.getTeamId());
            p.setPlayerName(teamInfo.getTeamName() + (i+1) );    //for the sake of convenience, player name is declared as team name plus integer. eg: CSK1,CSK2...
            p.setInAt(i+1);
            if(i<5)
            {
                p.setPlayerRole(PlayerRole.BATTER);
            }
            else if(i<7)
            {
                p.setPlayerRole(PlayerRole.ALL_ROUNDER);
            }
            else
            {
                p.setPlayerRole(PlayerRole.BOWLER);
            }
            playersOfTeam.add(p);
        }
        return playersOfTeam;
    }

    @Override
    public void toss(Game game) {
        int tossVar = (int) (Math.random() * 2);
        if (tossVar == 1)
        {
            TeamInfo t = game.getTeam2Info();
            game.setTeam2Info(game.getTeam1Info());
            game.setTeam1Info(t);

            ArrayList<PlayersInfo> temp = game.getPlayersTeam2();
            game.setPlayersTeam2(game.getPlayersTeam1());
            game.setPlayersTeam1(temp);
        }
        game.getMatchStats().setTossWinningTeam(game.getTeam1Info().getTeamId());
        game.getMatchStats().setFirstBattingTeam(game.getTeam1Info().getTeamId());
        game.getMatchStats().setSecondBattingTeam(game.getTeam2Info().getTeamId());

    }

    @Override
    public void startMatch(Game game) {
        inning(game,false);

        inning(game,true);

    }
    public void inning(Game game,boolean secondInning) {
        TeamInfo battingTeam = game.getTeam1Info();
        ArrayList<PlayersInfo> playersOfBattingTeam = game.getPlayersTeam1();
        ArrayList<PlayersInfo> playersOfBowlingTeam = game.getPlayersTeam2();
        if(secondInning)
        {
            battingTeam = game.getTeam2Info();
            playersOfBattingTeam = game.getPlayersTeam2();
            playersOfBowlingTeam = game.getPlayersTeam1();
        }
        PlayersInfo onStrike,nonStrike,bowler;
        PlayersInfo[] rotatedPlayers;

                onStrike = playersOfBattingTeam.get(0);
        nonStrike = playersOfBattingTeam.get(1);
        int bowlAt = 0, i, j,wickets=0;
        for (i = 0; i < game.getMatchStats().getOvers() && wickets < 10; i++) {
            bowler = playersOfBowlingTeam.get(Constants.NUMBER_OF_PLAYERS_IN_A_TEAM - 1 - bowlAt);

            for (j = 0; j < 6 && wickets < 10; j++) {
                RandomOutputOfBall outcome = ballOutcome(game,i*6 + j + 1,onStrike,bowler,battingTeam);

                if (outcome == RandomOutputOfBall.WICKET) {
                    wickets++;

                    if (wickets < 10) {
                        onStrike = playersOfBattingTeam.get(Math.max(onStrike.getInAt(), nonStrike.getInAt()));
                    }

                } else {
                    if(secondInning)
                        game.setScoreOfSecondInning(game.getScoreOfSecondInning() + Integer.parseInt(outcome.getValue()));
                    else
                        game.setScoreOfFirstInning(game.getScoreOfFirstInning() + Integer.parseInt(outcome.getValue()));


                    if (!(outcome == RandomOutputOfBall.ZERO || outcome == RandomOutputOfBall.TWO ||
                            outcome == RandomOutputOfBall.FOUR || outcome == RandomOutputOfBall.SIX)) {
                        rotatedPlayers = strikeRotate(onStrike,nonStrike);
                        onStrike = rotatedPlayers[0];
                        nonStrike = rotatedPlayers[1];
                    }
                }
            }
            if (secondInning && (game.getScoreOfFirstInning() < game.getScoreOfSecondInning())) break;
            rotatedPlayers =  strikeRotate(onStrike,nonStrike);
            onStrike = rotatedPlayers[0];
            nonStrike = rotatedPlayers[1];
            bowlAt = (bowlAt + 1) % 6;

        }

        result(game);

    }
    public RandomOutputOfBall ballOutcome(Game game,int ballCount,PlayersInfo onStrike,PlayersInfo bowler,TeamInfo battingTeam ) {

        RandomOutputOfBall outcome = RandomOutputOfBall.randomOutcome(onStrike);
        ResultOnSingleBall result = new ResultOnSingleBall(game.getMatchStats().getMatchId(),battingTeam.getTeamId(),
                ballCount/6,ballCount,onStrike.getPlayerId(),bowler.getPlayerId(),outcome);
        game.getPerBallStats().add(result);


        return outcome;
    }

    public PlayersInfo[] strikeRotate(PlayersInfo onStrike,PlayersInfo nonStrike) {
        return new PlayersInfo[]{nonStrike, onStrike};
    }

    public void result(Game game){
        if(game.getScoreOfFirstInning()>game.getScoreOfSecondInning())
        {
            game.setWinnerTeamID(game.getTeam1Info().getTeamId());
        }
        else if(game.getScoreOfFirstInning()<game.getScoreOfSecondInning())
        {
            game.setWinnerTeamID(game.getTeam2Info().getTeamId());
        }
        else{
            game.setWinnerTeamID(-1);
        }

    }

}
