package com.shpp.p2p.cs.bvorobiov.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class NameSurferDataBase implements NameSurferConstants {

    /* Constructor: NameSurferDataBase(filename) */

    // Create Array list of object for create database.
    private ArrayList<NameSurferEntry> dataBase = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {

        // Just read the file and add to array list each line from file.
        try {
            BufferedReader statistics = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = statistics.readLine()) != null) {
                dataBase.add(new NameSurferEntry(line));
            }
            statistics.close();
        } catch (IOException e) {
            System.out.println(("Error! File was not found!"));
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {

        // Create string array for find the string that associated with entry name.
        String[] getNameFromEntryString;

        // Processing each values from database and find associated string.
        for (int i = 0; i < dataBase.size(); i++) {
            getNameFromEntryString = dataBase.get(i).toString().split(" ");
            if (name.toLowerCase().equals(getNameFromEntryString[0].toLowerCase())) {
                return dataBase.get(i);
            }
        }
        return null;
    }
}

