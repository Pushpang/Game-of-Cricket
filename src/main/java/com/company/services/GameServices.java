package com.company.services;

import com.company.beans.Game;
import com.company.beans.PlayersInfo;
import com.company.beans.TeamInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;

@Service
public interface GameServices {
    void setupTeams(Game game,int team1Id,int team2Id,String team1Name,String team2Name,Connection con);
    ArrayList<PlayersInfo> setupNewPlayers(TeamInfo teamInfo);
    void toss(Game game);
    void startMatch(Game game);
}
