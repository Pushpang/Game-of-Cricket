package com.company.Queries;

public class Queries {
    public static String insertIntoTeamsInfoQuery = "INSERT INTO teams_info (teamId,teamName) VALUES (?,?);";
    public static String insertIntoPlayersInfoQuery = "INSERT INTO players_info (playerId,teamId,playerName,playerRole,inAt) VALUES (?,?,?,?,?);";
    public static String insertIntoMatchStatsQuery = "INSERT INTO match_stats (matchId, overs,tossWinningTeamId,firstBattingTeamId, secondBattingTeamId,winnerTeamId) VALUES (?,?,?,?,?,?);";
    public static String insertIntoPerBallStatsQuery = "INSERT INTO per_ball_stats (matchId,battingTeamId,overNumber,ballNumber,batterId,bowlerId,resultOnThatBall) VALUES (?,?,?,?,?,?,?);";
    public static String teamQueryCheck = "SELECT EXISTS(SELECT * from teams_info WHERE teamId= ? )";
    public static String matchQueryCheck = "SELECT EXISTS(SELECT * from teams_info WHERE teamId= ? )";
    public static String teamQuery = "SELECT * from teams_info WHERE teamId= ? ";
    public static String playerQuery = "SELECT * from players_info WHERE teamId= ? ";
    public static String getNewMatchId = "SELECT COUNT(*) from match_stats";
    public static String getAllMatches = "SELECT * from match_stats";
    public static String getMatchById = "SELECT * from match_stats WHERE matchId= ? ";
    public static String getPlayerInfoById = "SELECT * from players_info WHERE playerId= ? ";
    public static String getBatterDetailsFromMatch = "select resultOnThatBall from per_ball_stats where batterId = ? AND matchId = ?";
    public static String getBowlerDetailsFromMatch = "select resultOnThatBall from per_ball_stats where bowlerId = ? AND matchId = ?";
    public static String getBowlerIdWhoTookWicket = "select bowlerId from per_ball_stats where batterId = ? AND resultOnThatBall = 'W'";



}
