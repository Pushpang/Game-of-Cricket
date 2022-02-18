package com.company;

public class MatchUtility {
    public static String randomOutcome(Player onStrike){
        String[] outcomes ;
        if(onStrike.getRole().equals("Batter"))
        {
            outcomes= new String[] {"0","1","2","3","4","6",
                    "0","1","2","3","4","6",
                    "0","1","2","W"};
        }
        else if(onStrike.getRole().equals("All-Rounder"))
        {
            outcomes= new String[] {"0","1","2","3","4","6",
                    "0","1","2","3","W"};
        }
        else
            outcomes= new String[] {"0","1","2","3","4","W","W"};

        return outcomes[(int) (Math.random()*outcomes.length)];
    }


}
