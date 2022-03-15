package com.company.services;

import com.company.Queries.Queries;
import com.company.responses.PlayerStats;
import com.company.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.HashMap;

@Service
public class PlayerServicesImpl implements PlayerServices {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId) {
        PlayerStats player =  playerRepository.getPlayerDetailsFromMatch(playerId,matchId);
        HashMap<String,Integer> batting = player.getBattingStats(),bowling=player.getBowlingStats();
        int runsScored = batting.get("1") + 2*batting.get("2")+ 3*batting.get("3") + 4*batting.get("4")+ 6*batting.get("6");
        int runsGiven = bowling.get("1") + 2*bowling.get("2")+ 3*bowling.get("3") + 4*bowling.get("4")+ 6*bowling.get("6");
        int ballsFaced = batting.get("0") + batting.get("1") +batting.get("2")+ batting.get("3") + batting.get("4")+ batting.get("6") + batting.get("W");
        int ballsDelivered = bowling.get("0")+ bowling.get("1") + bowling.get("2")+ bowling.get("3") + bowling.get("4")+ bowling.get("6") + bowling.get("W");

        player.setRunsScored(runsScored);
        player.setRunsGiven(runsGiven);
        player.setBallsFaced(ballsFaced);
        player.setBallsDelivered(ballsDelivered);
        if(batting.get("W")==1)
        {
           player.setWicketTakenByBowlerId( playerRepository.getBowledByPlayerId(playerId,matchId) );
        }

        return player;
    }
}
