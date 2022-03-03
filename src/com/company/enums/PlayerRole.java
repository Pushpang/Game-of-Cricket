package com.company.enums;

public enum PlayerRole {
    BATTER("BATTER"),
    ALL_ROUNDER("ALL_ROUNDER"),
    BOWLER("BOWLER");
    private final String value;

    PlayerRole(String value)
    {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
