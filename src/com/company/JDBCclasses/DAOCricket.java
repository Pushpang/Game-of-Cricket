package com.company.JDBCclasses;

import com.company.beans.Game;
import com.company.beans.Team;
import com.company.scoreUtilityClasses.DetailedScoreSheet;

import java.sql.Connection;

public interface DAOCricket {
    public abstract void insertIntoTeamsInfoAndPlayersInfo(Game game, Connection con);
    public abstract void insetIntoMatchStats(Game game, Connection con);
    public abstract void insertIntoPerBallStats(DetailedScoreSheet scoreSheet, Connection con);
    public abstract int teamCheckInDB(Connection con,int teamId);
    public abstract int matchCheckInDB(Connection con, int matchId);
    public abstract Team fetchingTeamFromDB(Connection con,int teamId);
}
