package com.company.controller;

import com.company.beans.Team;
import com.company.services.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    @Autowired
    private TeamServices teamServices;

    @GetMapping("/team/{teamId}")
    public Team getTeamById(@PathVariable int teamId){
        return teamServices.getTeamById(teamId);
    }
}
