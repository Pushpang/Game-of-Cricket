package com.company.repository;

import com.company.beans.Game;
import com.company.entities.Match;
import com.company.services.MatchServices;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface MatchRepository {
    int getNewMatchId();
    void insertMatch(Connection con, Game game);
    Match getMatchById(int matchId);
}
