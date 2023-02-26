package com.shpp.p2p.cs.bvorobiov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    /**
     * Main method.
     * <P>
     * Create a variable for filename and ask user for index column.
     * <P>
     * Put its parameters in extractColumn method.*/
    public void run() {
        String filename = "123.csv";
        int columnIndex = readInt("Which column you want to see? ");
        extractColumn(filename, columnIndex);
    }

    /**
     * Try to do code, and if file isn't present in directory — return null.
     * <P>
     * The "try" do:
     * <P>
     * 1. Read file and put in method fieldsIn each line.
     * <P>
     * 2. Add to ArrayList result cleared of excess values columns.
     * <P>
     * 3. Print the result to user.*/
    private ArrayList<String> extractColumn(String filename, int columnIndex) {

        try {
            BufferedReader dishes = new BufferedReader(new FileReader(filename));
            String line;
            ArrayList<String> result = new ArrayList<>();
            while ((line = dishes.readLine()) != null) {
                fieldsIn(line);
                result.add(fieldsIn(line).get(columnIndex));
            }
            println(result);

        } catch (IOException e) {
            println("Error! File was not found!");
            return null;
        }

        return null;
    }

    /**
     * fieldsIn method is return cleared of excess values' column from csv file.
     * <P>
     * The loop select each chars to commas and add each value to string.
     * <P>
     * If there are no double quotes, add it strings to arrayList, clear the string.
     * <P>
     * If there are double quotes, need to check if the comma is separator or not.
     * <P>
     * The while loop is counting each double quotes and add values to the string.
     * <P>
     * If count value is odd, then the next commas isn't separator.
     * <P>
     * And if the count value is even, then the next commas is separator. In this case add string to arrayList and clean the string.*/
    private ArrayList<String> fieldsIn(String line) {

        // Array to save all values separated commas
        ArrayList<String> rows = new ArrayList<>();

        // Buffer for captured value before separator
        String capturedString = "";

        // Count of doubleQuotes
        int countQuotes = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ',') {
                capturedString += line.charAt(i);
            } else if (line.charAt(i) == ',') {
                rows.add(capturedString);
                capturedString = "";
            }
            if (line.charAt(i) == '"' && i != line.length() - 1) {
                countQuotes++;
                i++;
                while (true) {
                    if (i == line.length() - 1) {
                        break;
                    }
                    if (line.charAt(i) == '"') {
                        countQuotes++;
                    }
                    if (line.charAt(i) == ',' && countQuotes % 2 != 0) {
                        capturedString += line.charAt(i);
                        i++;
                    } else if (line.charAt(i) == ',' && countQuotes % 2 == 0) {
                        rows.add(capturedString);
                        capturedString = "";
                        countQuotes = 0;
                        break;
                    } else {
                        capturedString += line.charAt(i);
                        i++;
                    }

                }
            }
        }

        rows.add(capturedString);

        clearFromJunk(rows);

        return rows;
    }

    /**
     * Clean the arrayList from junk and return values as it in excel file:
     * <P>
     * 1. Create the array and input in values from arrayList rows.
     * <P>
     * 2. Replace each open and ending double quotes to nothing.
     * <P>
     * 3. Then replace all 2 double quotes to 1 double quotes.
     * <P>
     * 4. Clear the arrayList rows and overwrite purified values.*/
    private ArrayList<String> clearFromJunk(ArrayList<String> rows) {

        String[] allRows = new String[rows.size()];

        for (int i = 0; i < allRows.length; i++) {
            allRows[i] = rows.get(i).replaceAll("^\"|\"$", "");
            allRows[i] = allRows[i].replaceAll("\"\"", "\"");
        }

        rows.clear();

        for (int i = 0; i < allRows.length; i++) {
            rows.add(allRows[i]);
        }

        return rows;
    }

}



