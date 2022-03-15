package com.company.repository;

import com.company.beans.Game;
import com.company.beans.MatchStats;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface MatchRepository {
    int getNewMatchId();
    void insertMatch(Connection con, Game game);
    MatchStats getMatchById(int matchId);
}
