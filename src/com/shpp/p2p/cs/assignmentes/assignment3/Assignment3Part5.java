package com.shpp.p2p.cs.bvorobiov.assignment3;


import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {

    // Amount of bet
    private int bet = 1;

    // Variable keeps the total earn of all games
    private int totalEarn = 0;

    // Variable keeps the number of all games
    private int numberOfGames;

    // Values for heads and tails
    private static final int HEADS = 1;
    private static final int TAILS = 2;

    // Games counter
    private static final int GAMES_COUNTER = 1;

    // Variable keeps the side of coin
    private int headsOrTails;

    // Method starts game

    public void run() {
        letsPlay();
    }

    // Method shows heads or tails
    // If heads — return true
    // If tails — return false
    private int coinSide() {
        // Random that shows heads or tails
        headsOrTails = HEADS + (int) (Math.random() * TAILS);
        return headsOrTails;
    }

    /**
     * We start to play the game and end when our full salary is not = $20,
     * if at the time of earning we get heads, we continue to toss a coin until we get a head.
     */
    private void letsPlay() {
        while (totalEarn < 20) {
            flipTheCoin();
        }
        println("It took " + numberOfGames + " games to earn $20");
    }

    // Flip a coin and look at the result: heads or tails
    private void flipTheCoin() {
        if (coinSide() == 1) {
            calculateIfHeads();
        } else {
            calculateIfTails();
        }
    }

    /*
     * If tail, firstly we add number of games
     * Then we add bets for total earn
     * Then we reset bet to value 1
     * */
    private void calculateIfTails() {
        numberOfGames += GAMES_COUNTER;
        println("This game, you earned $" + bet);
        totalEarn += bet;
        println("Your total is $" + totalEarn);
        bet = 1;
    }

    // If heads — multiple bet on 2
    private void calculateIfHeads() {
        bet *= 2;
    }

}




