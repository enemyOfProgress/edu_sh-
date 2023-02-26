package com.shpp.p2p.cs.bvorobiov.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

    public void run() throws Exception {

        // Karel check is it world-with-one-cell
        if (frontIsBlocked()) {
            turnAround();
            putBeeper();
        }
            // If more than one cell, then Karel moves to the end of the line and put beeper
            else if (frontIsClear()){
                putBeeper();
                moveToTheEnd();
                putBeeper();
                turnAround();
                move();

                // Karel check is it world-with-two-cell
                if(beepersPresent()){
                    pickBeeper();
                }

                // If it is world with many cells, Karel start finding middle
                else {

                // Karel turns around and start to find middle of the line
                findMiddleOfTheLine();

                // Karel clean left side of middle from beepers
                cleanFromExcessBeepers();

                // Karel moves to the middle
                goToTheMiddle();

                // Karel clean right side of middle from beepers
                cleanFromExcessBeepers();
                }
            }
    }





    private void goToTheMiddle() throws Exception {
        // Karel moves to the middle and clear middle from excess beepers
        while(noBeepersPresent()){
            move();
        }
        pickBeeper();
        move();
        move();
        pickBeeper();
    }


    private void cleanFromExcessBeepers() throws Exception {
        // Karel clean left side of middle from beepers
        while (frontIsClear()){
            move();
            if (beepersPresent()){
                pickBeeper();
            }
        }
        turnAround();
    }

    private void findMiddleOfTheLine() throws Exception {
        // Karel puts the beepers until stops in the middle of the line
        while (noBeepersPresent()) {
            move();
            if (beepersPresent()) {
                turnAround();
                move();
                putBeeper();
                move();
            }
        }
    }

    private void moveToTheEnd() throws Exception {
        // Karel just move while front is clear
        while(frontIsClear()){
            move();
        }
    }

    private void turnAround() throws Exception {
        // Karel turns around by self position
        turnLeft();
        turnLeft();
    }

}