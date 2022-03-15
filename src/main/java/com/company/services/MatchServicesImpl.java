package com.company.services;

import com.company.Utility.UtilityClass;
import com.company.beans.Game;
import com.company.beans.MatchStats;
import com.company.beans.PlayersInfo;
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
        int matchId = matchRepository.getNewMatchId();
        Connection con = UtilityClass.getConnection();
        Game game = new Game();
        game.getMatchStats().setMatchId(matchId);
        game.getMatchStats().setOvers(overs);
        gameServices.setupTeams(game,team1Id,team2Id,team1Name,team2Name,con);
        gameServices.toss(game);
        gameServices.startMatch(game);
        matchRepository.insertMatch(con,game);
        if(teamRepository.teamCheckInDB(con,game.getTeam1Info().getTeamId())==0)
        {
            teamRepository.insertTeam(con,game.getTeam1Info());
            for(PlayersInfo currPlayerInfo:game.getPlayersTeam1()){
                playerRepository.insertPlayers(con,currPlayerInfo);
            }
        }
        if(teamRepository.teamCheckInDB(con,game.getTeam2Info().getTeamId())==0) {
            teamRepository.insertTeam(con, game.getTeam2Info());
            for (PlayersInfo currPlayerInfo : game.getPlayersTeam2()) {
                playerRepository.insertPlayers(con, currPlayerInfo);
            }
        }

        ballRepository.insertPerBallData(con,game);

        try {
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MatchStats> getAllMatches() {
        List<MatchStats> matchStatsList = new ArrayList<>();
        int lastMatchId = matchRepository.getNewMatchId();
        for (int i=1;i<lastMatchId;i++)
        {
            matchStatsList.add(matchRepository.getMatchById(i));
        }
        return matchStatsList;
    }

    public MatchStats getMatchById(int matchId)
    {
        return matchRepository.getMatchById(matchId);
    }

}
