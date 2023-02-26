package com.shpp.p2p.cs.bvorobiov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment5Part3 extends TextProgram {

    /**
     * 1. Array list for return all word with entered symbols.
     * <p>
     * 2. Array list for words from en-dictionary.txt
     */
    private ArrayList<String> result = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();

    /**
     * Main method doing:
     * <p>
     * 1. Reads the en-dictionary and put in the ArrayList words each word from file.
     * <p>
     * 2. Loop while for asking user for 3 letters and if here are 3 letters start to find words with the letters in order.
     * <p>
     * 3. Then returns array with all found words. Clear the arrayList dnd do it while true.
     */
    public void run() {
        try {
            BufferedReader dictionary = new BufferedReader(new FileReader("en-dictionary.txt"));
            String line;

            while ((line = dictionary.readLine()) != null) {
                words.add(line);
            }
            dictionary.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String letters = readLine("Enter 3 letters:  ");
            if (checkIsHereAreThreeLetters(letters)) {
                println("There are no 3 letters, please try again.");
            } else if (!checkIfThereAreNumbers(letters)) {
                println("Here are the numbers. Please try again");
            } else {
                findWords(letters);
                println(result);
                result.clear();
            }
        }
    }

    /**
     * We get three letters as string on the entrance and return the word which includes this letters in order.*/
    private ArrayList findWords(String letters) {

        // Entry letters to lowercase.
        letters = letters.toLowerCase();

        /*
        * 1. Declare first value from string as a separate letter;
        * 2. Declare second value from string as a separate letter;
        * 3. Declare third value from string as a separate letter;*/
        String firstLetter = String.valueOf(letters.charAt(0));
        String secondLetter = String.valueOf(letters.charAt(1));
        String thirdLetter = String.valueOf(letters.charAt(2));

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).toLowerCase(); // Each word in dictionary to lowercase.

            // Find the letter index in word.
            int indexFirstLetter = word.indexOf(firstLetter);
            int indexSecondLetter = word.indexOf(secondLetter);
            int indexThirdLetter = word.indexOf(thirdLetter);

            // Compare the indexes, if letter in order, add it to array.
            if (indexFirstLetter >= 0 && indexSecondLetter > indexFirstLetter && indexThirdLetter > indexSecondLetter) {
                result.add(words.get(i));
            }
        }
        return result;
    }

    /**
     * Check if user input not the letters.
     * <P>
     * If user input another value, not the letter, we should notify the user.
     */
    private boolean checkIfThereAreNumbers(String letters) {
        String letterLowerCase = letters.toLowerCase();

        Pattern pattern = Pattern.compile("\\p{javaLowerCase}[a-z]");
        Matcher matcher = pattern.matcher(letterLowerCase);
        return matcher.lookingAt();
    }

    /**
     * Check length of the input string.
     * <P>
     * Because the task only has 3 letters, the program can't accept more values.
     * <P>
     * So we return true or false, and if false we should notify the user.*/
    private boolean checkIsHereAreThreeLetters(String letters) {
        return letters.length() != 3;
    }
}
