package com.company.services;

import com.company.beans.Team;
import com.company.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServicesImpl implements TeamServices {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team getTeamById(int teamId) {
        return teamRepository.fetchingTeamFromDB(teamId);
    }
}
