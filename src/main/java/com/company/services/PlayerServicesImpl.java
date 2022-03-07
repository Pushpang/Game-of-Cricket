package com.company.services;

import com.company.entities.PlayerStats;
import com.company.entities.PlayersInfo;
import com.company.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServicesImpl implements PlayerServices {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public PlayerStats getPlayerDetailsFromMatch(int playerId, int matchId) {
        return playerRepository.getPlayerDetailsFromMatch(playerId,matchId);

    }
}
