package com.company.services;

import com.company.responses.PlayerStats;

public interface PlayerServices {
    PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId);
}
