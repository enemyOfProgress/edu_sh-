package com.shpp.p2p.cs.bvorobiov.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {
        // Karel puts beeper on first cell and turn left
        putBeeperIfItMissing();
        turnLeft();

        // Karel build column from wall and turns back to start-line position
        // Karel repeat it while front on start-line position is clear
        if (frontIsClear()) {
            while (frontIsClear()) {
                if (facingNorth()) {
                    buildColumn();
                    doFourSteps();
                }
            }
        } else if (frontIsBlocked()) {
            turnRight();
            doFourSteps();
        }


    }

    private void putBeeperIfItMissing() throws Exception {
        // Karel puts beeper on first cell, where Karel starts
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    private void doFourSteps() throws Exception {
        // Karel do four steps and turn left for build new column
        if (frontIsClear()) {
            for (int i = 0; i < 4; i++) {
                move();
            }
            if (noBeepersPresent()) {
                putBeeper();
            }
            turnLeft();

            // If Karel turns left and front is blocked
            // Karel turn right and go to next column
            // Else is Karel turn left and front is clear, Karel build column
            if (frontIsBlocked()) {
                turnRight();
                doFourSteps();
            } else {
                buildColumn();
                doFourSteps();
            }
        }
    }


    private void buildColumn() throws Exception {
        // Karel goes to wall and there is on beepers on cell — puts it
        // Then Karel turns around and go to start-line position
        while (frontIsClear()) {
            move();
            if (beepersPresent()) {
            } else if (noBeepersPresent()) {
                putBeeper();
            }
        }
        turnAround();
        goBack();
    }

    private void goBack() throws Exception {
        // Karel goes back from column to start-line position
        while (frontIsClear()) {
            move();
        }
        turnLeft();
    }

    private void turnAround() throws Exception {
        // Karel turns around by self position
        turnLeft();
        turnLeft();
    }


    private void turnRight() throws Exception {
        // Karel turns right by self position
        turnLeft();
        turnLeft();
        turnLeft();
    }

}