package com.shpp.p2p.cs.bvorobiov.assignment3;


import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    // The number the user enters and with which we start operations
    private int enteredNumber = readInt("Please, enter the number: ");

    // Dividing by half
    private static final int HALVE = 2;

    // The method asks the user for a number and starts to go to the number 1 by a given logic
    public void run() {
        calculateGradeNumbers();
    }


  /** The primary method that performs the basic operations with numbers
    So hard extra check that the input number is not <= 0
    If the number is even, just divide in half
    If the number is odd, the input number * 3 + 1
    */
    private void calculateGradeNumbers() {
        if (enteredNumber <= 0) {
            while (enteredNumber <= 0) {
                println("You cannot do this. Enter a positive number >0");
                enteredNumber = readInt("Please, enter the number: ");
            }
            goToTheNumberOne();
        } else {
            goToTheNumberOne();
        }
    }

    /** The method performs two operations until the number is 1
     * Separately for even numbers
     * Separate for odd numbers
     */
    private void goToTheNumberOne() {
        while (enteredNumber != 1) {
            calculateIfNumberIsOdd();
            calculateIfNumberIsEven();
        }
        print("Finally end!");
    }

    // If number is even divide half
    private void calculateIfNumberIsEven() {
        if (enteredNumber % 2 == 0) {
            println(enteredNumber + " is even so I take half: " + (enteredNumber / HALVE));
            enteredNumber = enteredNumber / HALVE;
        }
    }

    // If number is odd make 3*n+1
    private void calculateIfNumberIsOdd() {
        if (enteredNumber % 2 != 0) {
            println(enteredNumber + " is odd so I make 3n + 1: " + (enteredNumber * 3 + 1));
            enteredNumber = enteredNumber * 3 + 1;
        }
    }
}




