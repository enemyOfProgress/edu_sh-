package com.shpp.p2p.cs.bvorobiov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number:  ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        /*
         * 1. Result need for return to main method run();.
         * 2. Pre result need for calculate inverted string.*/
        String result = "";
        String preResult = "";

        /*
         * 1. Method return the first inverted string.
         * 2. Method return the second inverted string.
         * 3. Method looking for short string.
         */
        reversingFirstString(n1);
        reversingSecondString(n2);
        lookingForShortString(n1, n2);

        /*
         * 1. Filled first string n1.
         * 2. Filled second string n2.
         * 3. So after filling short string to long, we have two equal length string.
         * And we can declare length in single variable.
         * 4. Need to not use locally in if and for.
         * 5. Need to not use locally in if and for.
         * 6. Declare the counter for the next number in string if the sum of previous >= 10. */
        String firstString = fillingN1(n1, n2);
        String secondString = fillingN2(n1, n2);
        int stringLength = fillingN1(n1, n2).length();
        int numFirstString;
        int numSecondString;
        int counter = 0;

        /* Checks if the string is empty, because the method can't add empty values.*/
        if (n1.length() == 0 || n2.length() == 0){
            return "String is empty. Please, input the numbers";
        }

        if (checkIsHereAreNotNumbers(n1, n2)) {
            return "Hah, that's fine, because i dont know how to calculate not the numbers. So try again";
        }

        /*
         * Calculates single numbers.*/
        if (stringLength == 1) {
            numFirstString = firstString.charAt(0) - '0';
            numSecondString = secondString.charAt(0) - '0';
            result += numFirstString + numSecondString;
            return result;
        }

        /* So, when we have two reversed strings with same length, we can calculate it.
         * And this cycle exactly for it.*/
        for (int i = 0; i < stringLength; i++) {

            numFirstString = firstString.charAt(i) - '0';
            numSecondString = secondString.charAt(i) - '0';
            int sumOfNumbers = numFirstString + numSecondString + counter;

            // Checks if there are last number is string. And if it's true just calculate last numbers and break cycle.
            if (i == stringLength - 1) {
                result += numFirstString + numSecondString + counter;
                break;
            }

            /* Checks if sum of i-numbers is >= 10, and it's not a last number in string.
             * Then calculate sum of number - 10, and counter = 1.
             * Counter = 1 is used to transfer the 1 to the next number.
             * If sum of i-numbers < 10 counter = 0.*/
            if (sumOfNumbers >= 10 && i != stringLength - 1) {
                sumOfNumbers -= 10;
                counter = 1;
            } else {
                counter = 0;
            }
            preResult += sumOfNumbers;
        }


        // Just inverts the pre result for return correct result of calculates.
        invertingPreResult(preResult);

        return result.concat(invertingPreResult(preResult));
    }

    /**
     * Since we can’t add numbers, we need to check what the user is typing in.
     * If these are not numbers, we must notify the user that the method can't add no numbers.
     */
    private boolean checkIsHereAreNotNumbers(String n1, String n2) {
        String numbers = "1234567890";
        for (int i = 0; i < n1.length(); i++) {
            if (!numbers.contains(String.valueOf(n1.charAt(i)))) {
                return true;
            }
        }

        for (int i = 0; i < n2.length(); i++) {
            if (!numbers.contains(String.valueOf(n2.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Since at first we inverted the line for easier calculation, we need to invert it to return the correct result.
     */
    private String invertingPreResult(String preResult) {
        String result = "";

        for (int i = preResult.length() - 1; i >= 0; i--) {
            result += preResult.charAt(i);
        }
        return result;
    }

    /**
     * Below are two methods that inverted the strings.
     * <p>
     * It's need because we must calculate numbers from right to left side.
     * <p>
     * And it's the easiest method for calculate two numbers.
     */
    private String reversingFirstString(String n1) {
        String n1ReversedNums = "";

        for (int i = n1.length() - 1; i >= 0; i--) {
            n1ReversedNums += n1.charAt(i);
        }
        return n1ReversedNums;
    }

    private String reversingSecondString(String n2) {
        String n2ReversedNums = "";
        for (int i = n2.length() - 1; i >= 0; i--) {
            n2ReversedNums += n2.charAt(i);
        }
        return n2ReversedNums;
    }

    /**
     * This method just searches for a short string to fill it with 0.
     */
    private void lookingForShortString(String n1, String n2) {
        if (n1.length() > n2.length()) {
            fillingN2(n1, n2);
        } else {
            fillingN1(n1, n2);
        }
    }

    /**
     * Below are two methods that filling the shortest string.
     * <p>
     * This is to equalize rows in length, so it is easier to iterate them by "column" to count.
     */
    private String fillingN1(String n1, String n2) {
        String n1ResultString = "";
        for (int i = 0; i < n2.length() - n1.length(); i++) {
            n1ResultString += '0';
        }
        return reversingSecondString(n1).concat(n1ResultString);
    }

    private String fillingN2(String n1, String n2) {
        String n2ResultString = "";
        for (int i = 0; i < n1.length() - n2.length(); i++) {
            //noinspection SingleStatementInBlock
            n2ResultString += '0';
        }
        return reversingSecondString(n2).concat(n2ResultString);
    }
}
