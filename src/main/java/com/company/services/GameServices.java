package com.company.services;

import com.company.beans.Game;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Scanner;

@Service
public interface GameServices {
    public abstract void setupTeams(Game game,int team1Id,int team2Id,String team1Name,String team2Name,Connection con);
    public abstract void toss(Game game);
    public abstract void startMatch(Game game);
}
