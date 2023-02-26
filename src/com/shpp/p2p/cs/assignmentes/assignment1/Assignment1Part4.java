package com.shpp.p2p.cs.bvorobiov.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {

    public void run() throws Exception {

        // Karel checking, is that one cell
        if (frontIsBlocked()){
            turnLeft();
        }

        // Karel goes by the east line and puts beeper through one cell
        karelPaintsChessBoard();


    }

    private void stopWorkingIfFrontIsBlocked() throws Exception {
        if (frontIsBlocked()){
            turnAround();
        }
    }

    private void karelPaintsChessBoard() throws Exception {
        // Karel moves east in a line and throws a beeper through one
        putBeeper();
        stopWorkingIfFrontIsBlocked();
        while(frontIsClear() && facingEast()){
            stopWorkingIfFrontIsBlocked();
            move();
            if(frontIsClear() && noBeepersPresent()){
                stopWorkingIfFrontIsBlocked();
                move();
                putBeeper();
            }

            // Karel checks this end of the line or not
            if (frontIsBlocked()){
                // If there is a beeper for a Karel, then Karel understands that it is an odd line
                // And goes to the execution of the algorithm with an odd line
                if (beepersPresent() && leftIsClear()){
                    turnLeft();
                    oddLine();
                }
                //If there is no Karel Beeper, then Karel understands that it is an even line
                // And goes on to run the algorithm for the even line
                else if (noBeepersPresent() && leftIsClear()){
                    turnLeft();
                    evenLine();
                }
            }
        }
    }

    private void evenLine() throws Exception {
        // Karel determines it is even line or not
        stopWorkingIfFrontIsBlocked();
        move();
        putBeeper();
        turnLeft();

        // Karel starts to walk the line and throws beepers through one
        while(frontIsClear() && facingWest()) {
            stopWorkingIfFrontIsBlocked();
            move();
            if (frontIsClear() && noBeepersPresent()) {
                stopWorkingIfFrontIsBlocked();
                move();
                putBeeper();
            }

            // Karel realizes that is at the end of the line and moves one line higher
            if (frontIsBlocked() && rightIsClear()){
                turnRight();
                // Karel determines if it is the end of the map then the job is done
                stopWorkingIfFrontIsBlocked();
                move();
                turnRight();
                karelPaintsChessBoard();
            }
        }
    }

    private void oddLine() throws Exception {
        // Karel determines it is odd line or not
        move();
        turnLeft();
            while(frontIsClear() && facingWest()) {
                move();
                if (frontIsClear() && noBeepersPresent()) {
                    putBeeper();
                    move();
                }

                // Karel realizes that is at the end of the line and moves one line higher
                if (frontIsBlocked() && rightIsClear()){
                    turnRight();
                    // Karel determines if it is the end of the map then the job is done
                    move();
                    turnRight();
                    karelPaintsChessBoard();
                }
            }

    }

    private void turnAround() throws Exception {
        // Karel turns around by self position
        turnLeft();
        turnLeft();
    }


    private void turnRight () throws Exception {
        // Karel turns right by self position
                for (int i = 0; i < 3; i++) {
                    turnLeft();
                }
            }

    }


