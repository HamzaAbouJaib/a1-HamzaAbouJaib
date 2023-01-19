package pk;

public class Game {
    public void turn(Player p) {
        // At the start of a turn roll 8 dice
        Faces[] dice= p.rollEightDice();
        // print the faces rolled
        for (Faces die: dice) System.out.print(die + ", ");
        System.out.println();
        if (skullCountChecker(dice)){
            System.out.println("Player " + p.playerID + " has rolled 3 or more skulls, their turn is over.");
        } else {
            // simulates a player deciding to re-roll or keep their roll by basically a coin flip
            while(true) {
                boolean reroll = Math.random() > 0.5;
                if(reroll) {
                    // Re-roll non skull dice and print the result of the re-roll
                    dice = p.rerollDice(dice);
                    System.out.print("Player " + p.playerID + "'s new roll: ");
                    for (Faces die : dice) System.out.print(die + ", ");
                    System.out.println();
                    // check if the number of skulls surpassed or equals 3 after the re-roll
                    if (skullCountChecker(dice)) {
                        System.out.println("Player " + p.playerID + " has rolled 3 or more skulls, their turn is over.");
                        break;
                    }
                } else {
                    // simulates the player deciding to keep his roll
                    break;
                }
            }
            // Only add the score earned in the roll if the number of skulls <= 3
            if (!skullCountChecker(dice)) p.updateScore(dice);
            System.out.println("Player " + p.playerID + " ended their turn with a score of " + p.score);
        }
        System.out.println("turn done");
    }

    public boolean skullCountChecker(Faces[] dice){
        int num_of_skulls = 0;
        // Calculate the number of skulls in a given roll
        for (Faces die: dice) {
            if(die == Faces.SKULL){
                num_of_skulls++;
            }
        }
        // return if the number of dice is >= 3 meaning the players turn ended.
        return num_of_skulls >= 3;
    }

}
