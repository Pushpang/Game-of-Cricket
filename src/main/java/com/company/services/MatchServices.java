package com.company.services;

import com.company.entities.Match;

import java.util.List;

public interface MatchServices {
    void startNewMatch(int overs,int team1Id,int team2Id, String team1Name,String team2Name);
    List<Match> getAllMatches();
     Match getMatchById(int matchId);

}
