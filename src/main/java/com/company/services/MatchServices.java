package com.company.services;

import com.company.beans.MatchStats;

import java.util.List;

public interface MatchServices {
    void startNewMatch(int overs,int team1Id,int team2Id, String team1Name,String team2Name);
    List<MatchStats> getAllMatches();
    MatchStats getMatchById(int matchId);

}
