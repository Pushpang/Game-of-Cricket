package com.company.services;

import com.company.entities.PlayerStats;
import com.company.entities.PlayersInfo;

public interface PlayerServices {
    PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId);
}
