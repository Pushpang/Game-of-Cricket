package com.company.controller;

import com.company.entities.Match;
import com.company.services.MatchServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {
    @Autowired
    private MatchServicesImpl matchServices;
    @PostMapping("/match/new")
    public String newMatch(@RequestBody String[] input ){
        int overs= Integer.parseInt(input[0]);
        int team1Id= Integer.parseInt(input[1]);
        int team2Id= Integer.parseInt(input[2]);
        String team1Name = input[3];
        String team2Name = input[4];

        matchServices.startNewMatch(overs,team1Id,team2Id,team1Name,team2Name);
        return input[0]+" "+input[1]+" "+input[2]+" "+input[3]+" "+input[4]+" ";
    }

    @GetMapping("/match/{matchId}")
    public Match displayMatches(@PathVariable int matchId){
        return matchServices.getMatchById(matchId);
    }

    @GetMapping("/match/all")
    public List<Match> displayMatches(){
        return matchServices.getAllMatches();
    }
}
