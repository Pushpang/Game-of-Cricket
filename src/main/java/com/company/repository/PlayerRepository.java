package com.company.repository;

import com.company.responses.PlayerStats;
import com.company.beans.PlayersInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;

@Service
public interface PlayerRepository {
   void insertPlayers(Connection con,PlayersInfo player);
   PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId);
   int getBowledByPlayerId(int playerId,int matchId);
   ArrayList<PlayersInfo> fetchingPlayersOfTeam(int team1Id);
}
