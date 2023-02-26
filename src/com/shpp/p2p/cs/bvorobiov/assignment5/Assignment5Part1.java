package com.shpp.p2p.cs.bvorobiov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        /*Variables for:
         * 1. Checks for syllables;
         * 2. String word to lowercase;
         * 3. Counter for syllables.*/
        String symbols = "aeiouy";
        word = word.toLowerCase();
        int countSyllables = 0;

        if (checkIfStringIsEmpty(word)) {
            println("There is empty string. Please, try again.");
            run();
        }

        if (checkIsThereAreNumbersInString(word)) {
            println("Number isn't a letter. Please try again");
            return 0;
        }

        /*
        * Main cycle which defines syllables in the word.*/
        for (int i = 0; i < word.length(); i++) {

            boolean contains = symbols.contains(String.valueOf(word.charAt(i)));

            /*
            * Checks if here is a last symbols in word.
            * If last symbol is "e" counter + 0.
            * If it's not "e" and penultimate not a syllable counter +1.
            * Then break cycle for not to catch an error.*/
            if (i == word.length() - 1) {
                if (word.charAt(i) == 'e'){
                    countSyllables += 0;
                } else if (contains && !symbols.contains(String.valueOf(word.charAt(i-1)))){
                    countSyllables +=1;
                }
                break;
            }

            /*
            * Checks if here is the syllable in word of each iteration.
            * If i-symbol is contains and the next too, and it's not a penultimate symbol counter + 0.
            * Else if the letter is surrounded by consonants, then counter +1. */
            if (contains && symbols.contains(String.valueOf(word.charAt(i+1))) && i != word.length() - 2) {
                countSyllables += 0;
            } else if (contains) {
                countSyllables += 1;
            }
        }

        if (countSyllables == 0){
            return 1;
        }

        return countSyllables;
    }

    /**
     * The method checks if string is empty, because if user enter the empty string the program was crashed.
     * <P>
     * So in this case the method helps escape from crash.
     */
    private boolean checkIfStringIsEmpty(String word) {
        return word.length() == 0;
    }

    /**
     * Numbers isn't a word and their doesn't have syllables.
     * <P>
     * In this case we can't calculate the syllables, so we need in this check.
     * <P>
     * So the method check if string includes numbers.
     */
    private boolean checkIsThereAreNumbersInString(String word) {
        String numbers = "1234567890";
        for (int i = 0; i < word.length(); i++) {
            if (numbers.contains(String.valueOf(word.charAt(i)))) {
                return true;
            }
        }
        return false;
    }
}
