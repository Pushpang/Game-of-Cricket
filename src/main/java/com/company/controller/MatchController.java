package com.company.controller;

import com.company.beans.MatchStats;
import com.company.requests.NewMatchDetails;
import com.company.services.MatchServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {
    @Autowired
    private MatchServicesImpl matchServices;
    @PostMapping("/match/new")
    public String newMatch(@RequestBody NewMatchDetails newMatchDetails ){
        int overs= newMatchDetails.overs;
        int team1Id= newMatchDetails.team1Id;
        int team2Id= newMatchDetails.team2Id;
        String team1Name = newMatchDetails.team1Name;
        String team2Name = newMatchDetails.team2Name;

        matchServices.startNewMatch(overs,team1Id,team2Id,team1Name,team2Name);
        return newMatchDetails.overs+" "+newMatchDetails.team1Id+" "+newMatchDetails.team2Id+" "+newMatchDetails.team1Name+" "+newMatchDetails.team2Name+" ";
    }

    @GetMapping("/match/{matchId}")
    public MatchStats displayMatches(@PathVariable int matchId){
        return matchServices.getMatchById(matchId);
    }

    @GetMapping("/match/all")
    public List<MatchStats> displayMatches(){
        return matchServices.getAllMatches();
    }
}
