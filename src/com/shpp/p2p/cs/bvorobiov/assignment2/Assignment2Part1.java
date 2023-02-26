package com.shpp.p2p.cs.bvorobiov.assignment2;


import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    // Variables to solve the quadratic equation
    public double a;
    public double b;
    public double c;
    public double discriminant;

    // First, we ask the user to enter the number for the quadratic equation and look for the discriminant.
    // Then one of the three conditions is fulfilled, depending on the discriminant:
    //Discriminant < 0
    //Discriminant == 0
    //Discriminant > 0
    public void run() {
        inputNumbersAndReturnDiscriminant();
        println("Discriminant is " + discriminant);
        discriminantLessZero();
        discriminantIsZero();
        discriminantGreaterZero();
    }

    private void discriminantGreaterZero() {
        // If the discriminant > 0, then we inform: "There are two roots"
        if (discriminant > 0) {
            double root1 = (((-b + Math.sqrt(discriminant)) / (2 * a)));
            double root2 = (((-b - Math.sqrt(discriminant)) / (2 * a)));
            println("There are two roots: " + root1 + " and " + root2);
        }
    }

    private void discriminantLessZero() {
        // If the discriminant is < 0,  then we inform: "There are no real roots"
        if (discriminant < 0) {
            println("There are no real roots");
        }
    }

    private void discriminantIsZero() {
        // If the discriminant is == 0, then we inform: "There is one root"
        if (discriminant == 0) {
            println("There is one root: " + (-b / (2 * a)));
        }
    }

    private double inputNumbersAndReturnDiscriminant() {

        // First we ask the user to enter values in digits
        a = readDouble("please input first number for a");
        b = readDouble("please input second number for b");
        c = readDouble("please input third number for c");

        // If a == 0, we ask the user try again to input the numbers
        // If a != 0 we find discriminant
        if (a == 0){
            println("a can't == 0. Please, try again.");
            inputNumbersAndReturnDiscriminant();
        } else {
            discriminant = (b * b) - (4 * a * c);
        }
        return discriminant;
    }
}