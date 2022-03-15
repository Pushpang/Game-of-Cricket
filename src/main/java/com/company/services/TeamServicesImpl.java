package com.company.services;

import com.company.beans.TeamInfo;
import com.company.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServicesImpl implements TeamServices {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamInfo getTeamById(int teamId) {
        return teamRepository.fetchingTeamInfoFromDB(teamId);
    }
}
