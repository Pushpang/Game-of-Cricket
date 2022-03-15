package com.company.enums;


import com.company.beans.PlayersInfo;

public enum RandomOutputOfBall {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    SIX("6"),
    WICKET("W");
    private final String value;

    RandomOutputOfBall(String value)
    {
        this.value = value;
    }

    public String  getValue(){
        return this.value;
    }

    public static RandomOutputOfBall randomOutcome(PlayersInfo onStrike){
        RandomOutputOfBall[] outcomes ;
        switch (onStrike.getPlayerRole()) {
            case BATTER : outcomes = new RandomOutputOfBall[]{ZERO, ONE, TWO, THREE, FOUR, SIX, ZERO, ONE, ONE, TWO, TWO, THREE, FOUR, SIX, WICKET};
                break;
            case ALL_ROUNDER : outcomes = new RandomOutputOfBall[]{ZERO, ONE, TWO, THREE, FOUR, SIX, ZERO, ZERO, ONE, ONE, TWO, THREE, WICKET};
                break;
            default : outcomes = new RandomOutputOfBall[]{ZERO, ONE, TWO, THREE, FOUR, WICKET};
                break;
        };

        return outcomes[(int) (Math.random()*outcomes.length)];
    }


}
