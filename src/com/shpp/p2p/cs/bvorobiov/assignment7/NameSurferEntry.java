package com.shpp.p2p.cs.bvorobiov.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */

    // String for name from string
    private String name;

    // Array for decades from string
    int[] decades = new int[NDECADES];

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {

        // Turn string to array and split it by space. Then pull out the name by 0 index.
        String[] lineValues = line.split(" ");
        name = lineValues[0];

        // Processing the String array and add to array decades values of rank.
        for (int i = 0; i < lineValues.length; i++){
            if (i != lineValues.length-1){
                decades[i] = Integer.parseInt(lineValues[i+1]);
            }
        }
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return decades[decade];
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String testString = getName() + " " + Arrays.toString(decades);
        return testString;
    }
}

