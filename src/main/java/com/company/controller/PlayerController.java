package com.company.controller;

import com.company.entities.Match;
import com.company.entities.PlayerStats;
import com.company.entities.PlayersInfo;
import com.company.repository.PlayerRepository;
import com.company.services.MatchServicesImpl;
import com.company.services.PlayerServices;
import com.company.services.PlayerServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerServicesImpl playerServices;

    @GetMapping("/player/{matchId}/{playerId}")
    public PlayerStats getPlayerDetailsFromMatch (@PathVariable int matchId,@PathVariable int playerId){
        return playerServices.getPlayerDetailsFromMatch(playerId,matchId);
    }

}
