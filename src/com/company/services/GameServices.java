package com.company.services;

import com.company.beans.Game;

import java.sql.Connection;
import java.util.Scanner;

public interface GameServices {
    public abstract int matchIdSetup(Connection con,Scanner sc);
    public abstract void setupTeams(Game game, Connection con, Scanner sc);
    public abstract void toss(Game game);
    public abstract void startMatch(Game game);
}
