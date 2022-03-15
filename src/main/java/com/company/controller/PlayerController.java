package com.company.controller;

import com.company.responses.PlayerStats;
import com.company.services.PlayerServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerServicesImpl playerServices;

    @GetMapping("/player/{matchId}/{playerId}")
    public PlayerStats getPlayerDetailsFromMatch (@PathVariable int matchId,@PathVariable int playerId){
        return playerServices.getPlayerDetailsFromMatch(playerId,matchId);
    }

}
