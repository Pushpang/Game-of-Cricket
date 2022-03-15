package com.company.repository;

import com.company.beans.TeamInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public interface TeamRepository{
    int teamCheckInDB(Connection con,int teamId);
    void insertTeam(Connection con,TeamInfo team);
    TeamInfo fetchingTeamInfoFromDB(int team1Id);
}
