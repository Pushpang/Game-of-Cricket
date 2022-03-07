package com.company.entities;

public class PlayersInfo {
    private int playerId;
    private int teamId;
    private String playerName;
    private int playerRole;
    private int inAt;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(int playerRole) {
        this.playerRole = playerRole;
    }

    public int getInAt() {
        return inAt;
    }

    public void setInAt(int inAt) {
        this.inAt = inAt;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
