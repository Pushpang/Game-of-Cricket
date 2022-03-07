package com.company.services;

import com.company.CONSTANTS.Constants;
import com.company.Utility.UtilityClass;
import com.company.beans.Game;
import com.company.entities.Match;
import com.company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServicesImpl implements MatchServices{
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private GameServices gameServices ;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BallRepository ballRepository;

    @Override
    public void startNewMatch(int overs,int team1Id,int team2Id, String team1Name,String team2Name)
    {

        Constants.OVERS = overs;
        int matchId = matchRepository.getNewMatchId();
        Connection con = UtilityClass.getConnection();
        Game game = new Game(matchId);
        gameServices.setupTeams(game,team1Id,team2Id,team1Name,team2Name,con);
        gameServices.toss(game);
        gameServices.startMatch(game);
        matchRepository.insertMatch(con,game);
        if(teamRepository.teamCheckInDB(con,game.getTeam1().getId())==0)
        {
            teamRepository.insertTeam(con,game.getTeam1());
            playerRepository.insertPlayers(con,game.getTeam1());
        }
        if(teamRepository.teamCheckInDB(con,game.getTeam2().getId())==0)
        {
            teamRepository.insertTeam(con,game.getTeam2());
            playerRepository.insertPlayers(con,game.getTeam2());
        }


        ballRepository.insertPerBallData(con,game);

        try {
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Match> getAllMatches() {
        List<Match> matchList = new ArrayList<>();
        int lastMatchId = matchRepository.getNewMatchId();
        for (int i=1;i<lastMatchId;i++)
        {
            matchList.add(matchRepository.getMatchById(i));
        }
        return matchList;
    }

    @Override
    public Match getMatchById(int matchId)
    {
        return matchRepository.getMatchById(matchId);
    }

}
