package com.company;

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

    public static RandomOutputOfBall randomOutcome(Player onStrike){
        Integer[] outcomes ;
        if(onStrike.getRole() == PlayerRole.BATTER)
        {

            outcomes= new Integer[] {0,1,2,3,4,5,0,1,1,2,2,3,4,5,6};  // array of index hence 5 represents SIX and 6 represents WICKET as, SIX is at index 5 , W is at index 6
        }
        else if(onStrike.getRole() == PlayerRole.ALL_ROUNDER)
        {
            outcomes= new Integer[] {0,1,2,3,4,5,0,0,1,1,2,3,6};
        }
        else {
            outcomes = new Integer[]{0,1,2,3,4,6};

        }

        return values()[outcomes[(int) (Math.random()*outcomes.length)]];
    }


}
