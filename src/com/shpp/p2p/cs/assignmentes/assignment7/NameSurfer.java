package com.shpp.p2p.cs.bvorobiov.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */


import com.shpp.cs.a.simple.SimpleProgram;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    // That the user can input the name.
    private JTextField textFieldForEntryName;

    // Create button that creates graph.
    private JButton graphButton;

    // Create button that clears graph.
    private JButton clearButton;

    // Just a label for text field.
    private JLabel labelForTextField;

    // For error window.
    private JFrame frame;

    // The length of text field
    private static final int NUM_COL = 10;

    // Object for create graph.
    private NameSurferGraph graph;

    // Object for processing string that associated with entry name.
    public NameSurferEntry entryData;

    // Create object that processing the file with names.
    NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);


    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        // Create label for text field.
        labelForTextField = new JLabel("Name: ");
        add(labelForTextField, NORTH);

        // Create text field and add action command if user press enter after input name.
        textFieldForEntryName = new JTextField(NUM_COL);
        textFieldForEntryName.setActionCommand("EnterPressed");
        textFieldForEntryName.addActionListener(this);
        add(textFieldForEntryName, NORTH);

        // Create button that creates graph after click it.
        graphButton = new JButton("Graph");
        add(graphButton, NORTH);

        // Create button that clears all graph.
        clearButton = new JButton("Clear");
        add(clearButton, NORTH);

        // Create object that creates graph after than user entry name.
        graph = new NameSurferGraph();
        add(graph);

        this.addActionListeners();
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {

        // Create string that associated with entered button.
        String cmd = e.getActionCommand();

        if (cmd.equals("Clear")){
            graph.clear();
            graph.update();
        } else {
             entryData = dataBase.findEntry(textFieldForEntryName.getText());
            if (entryData != null){
                graph.addEntry(entryData);
            } else {
                JOptionPane.showMessageDialog(frame,"The name wasn't found. Please try again.");
            }
        }
    }
}
