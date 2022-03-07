package com.company.repository;

import com.company.beans.Team;
import com.company.entities.PlayerStats;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface PlayerRepository {
   void insertPlayers(Connection con, Team team);
   PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId);
}
