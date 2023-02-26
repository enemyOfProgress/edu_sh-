package com.shpp.p2p.cs.bvorobiov.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        // Firstly Karel goes to corridor
        goToCorridor();

        // Karel pickup newspaper
        pickBeeper();

        // Karel go forward to start position
        goForwardToStartPosition();
    }

    private void goForwardToStartPosition() throws Exception {
        // The function describe what Karel needs to do to become at start position

        turnAround();
        move();
        move();
        move();
        move();
        turnRight();
        move();
        turnRight();
    }

    private void turnAround() throws Exception {
        // Karel turns around by self position
        turnLeft();
        turnLeft();
    }

    private void goToCorridor() throws Exception {
        // Karel goes to corridor from starting position
        turnRight();
        move();
        turnLeft();
        while(noBeepersPresent()){
            move();
        }
    }


    private void turnRight() throws Exception {
        // Karel turns right by self position
        turnLeft();
        turnLeft();
        turnLeft();

    }
}