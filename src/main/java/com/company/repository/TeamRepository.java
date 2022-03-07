package com.company.repository;

import com.company.beans.Game;
import com.company.beans.Team;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface TeamRepository{
    public abstract int teamCheckInDB(Connection con,int teamId);
    public abstract Team fetchingTeamFromDB(int teamId);
    public abstract void insertTeam(Connection con,Team team);
}
