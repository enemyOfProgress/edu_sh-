package com.shpp.p2p.cs.bvorobiov.assignment3;


import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    // Variables that take the base number and exponential
    private double base;
    private int exponent;


    // The method displays the returned number raised to a power
    public void run() {
        println("Base number raised to a power is "+raiseToPower(base, exponent));
    }

    /**Method elevates to number
     Passes three checks
     Exponential > 0
     Exponential == 0
     Exponential  < 0
     and, depending on the check, returns the base number raised
     * */
    private double raiseToPower(double baseNum, int exponentNum) {
        base = readDouble("Please, enter the base number");
        exponent = readInt("Please, enter the exponent");
        if (exponent > 0){
            return calculateIfExponentIsPositive();
        } else if (exponent < 0){
            return calculateIfExponentIsNegative();
        } else {
            return 1;
        }
    }

    // If the exponent is negative, we take the absolute exponents and raise the base number
    // The next operation divides 1 by the raised to power base number
    private double calculateIfExponentIsNegative() {
        double baseNumberRaiseToPower = base;
        for (int i = 1; i < (-exponent); i++) {
            baseNumberRaiseToPower *= base;
        }
        baseNumberRaiseToPower = 1 / baseNumberRaiseToPower;
        return baseNumberRaiseToPower;
    }

    // Check and calculate if exponent is positive
    private double calculateIfExponentIsPositive() {
        double baseNumberRaiseToPower = base;
        for (int i = 1; i < exponent; i++) {
            baseNumberRaiseToPower *= base;
        }
        return baseNumberRaiseToPower;
    }

}




