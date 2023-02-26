package com.shpp.p2p.cs.bvorobiov.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    // Create the object that keeps all associated string with the entry name.
    private final ArrayList<NameSurferEntry> allEntries = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        // You fill in the rest //
    }

    private void createBackground() {
        drawHorizontalLines();
        drawVerticalLines();
        drawDecadeLabels();

    }

    private void drawHorizontalLines() {
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE,
                getWidth(), GRAPH_MARGIN_SIZE);
        add(topLine);

        GLine botLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(botLine);
    }

    private void drawVerticalLines() {
        int sep = getWidth() / NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(i * sep, 0, i * sep, getHeight()));
        }
    }

    private void drawDecadeLabels() {
        int sep = getWidth() / NDECADES;
        int dec = START_DECADE;
        String decade;

        for (int i = 0; i < NDECADES; i++) {
            decade = Integer.toString(dec);
            add(new GLabel(decade), i * sep, getHeight());
            dec += 10;
        }
    }

    /**
     * The method get index of object NameSurferEntry from array list allEntries.
     * <p>
     * And draw graph, after the user input name in text field and press enter or graph button.
     */
    public void drawGraph(int indexOfEntry) {
        // Declare first and ends point for lines.
        double x1 = 0;
        double y1 = 0;
        double x2 = 0;
        double y2 = 0;

        // Separation between x1 and x2.
        int sep = getWidth() / NDECADES;

        // Space where graph is drawing — between top and bot line.
        double spaceForGraph = getHeight() - (GRAPH_MARGIN_SIZE * 2);

        // Create an object that get associated string with entry name to draw graph.
        NameSurferEntry associatedEntry = allEntries.get(indexOfEntry);

        // Create an object — line that would be drawing by x-s and y-s positions.
        GLine graphLine = new GLine(x1, y1, x2, y2);

        // Create an object — label that would be shown on graph by y position on eac dacade.
        GLabel nameAndPopularity = new GLabel("");

        for (int i = 0; i < NDECADES; i++) {
            // x1 and x2 is always have a distance from decade to decade.
            x1 = i * sep;
            x2 = (i + 1) * sep;

            // Draw graph by decades if decade values == 0 and else if decades value > 0
            if (i + 1 != NDECADES) {
                // Calculate values for y1 and y2 position.
                y1 = calculateY1Value(spaceForGraph, associatedEntry, i);
                y2 = calculateY2Value(spaceForGraph, associatedEntry, i);

                // Draw the label with name and popularity on each decade.
                nameAndPopularity = drawLabelWithNameAndPopularity(i, y1, sep,
                        selectColorForGraph(indexOfEntry), associatedEntry);

                // Draw lines by x-s and y-s position and set the color of line.
                graphLine = new GLine(x1, y1, x2, y2);
                graphLine.setColor(selectColorForGraph(indexOfEntry));
            }

            // Draw last label. If decade value == 0 change 0 on *, and else draw decade value.
            if (i == NDECADES - 1) {
                nameAndPopularity = drawLastLabelWithNameAndPopularity(i, sep, spaceForGraph, associatedEntry, selectColorForGraph(indexOfEntry));
            }

            add(graphLine);
            add(nameAndPopularity);
        }
    }

    /**
     * Selects and return a color for the graph, depending on the name entered and its order in the list
     */
    private Color selectColorForGraph(int colorIndex) {
        Color[] colorsForLines = new Color[4];
        colorsForLines[0] = Color.BLUE;
        colorsForLines[1] = Color.RED;
        colorsForLines[2] = Color.MAGENTA;
        colorsForLines[3] = Color.BLACK;
        int color = colorIndex % 4;
        return colorsForLines[color];
    }

    private boolean checkIfPopularityIsZero(NameSurferEntry associatedEntry, int i) {
        return associatedEntry.getRank(i) == 0;
    }

    /**
     * Check is popularity is 0 or > 0 and calculate y1 position for line.
     * <P>
     * In case is popularity > 0, need to calculate the proportions by which the line will be drawn.
     * <P>
     * For calculate proportions need to use formula: ((spaceForGraph / MAX_RANK) * associatedEntry.getRank(i)) + GRAPH_MARGIN_SIZE.
     * <P>
     * spaceForGraph = space between top line and bot line.
     * <P>
     * MAX_RANK = constant that equal 1000.
     * <P>
     * GRAPH_MARGIN_SIZE = constant that equal 20.
     * <P>
     * So in this case formula gives the value where line will be drawn and line don't cross top and bot lines.*/
    private double calculateY1Value(double spaceForGraph, NameSurferEntry associatedEntry, int i) {
        double y1;
        if (checkIfPopularityIsZero(associatedEntry, i)) {
            y1 = getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            y1 = ((spaceForGraph / MAX_RANK) * associatedEntry.getRank(i)) + GRAPH_MARGIN_SIZE;
        }
        return y1;
    }

    /**
     * Check is popularity 0 or > 0 and calculate y2 position for line.
     * <P>
     * Calculate is the same as for y1.*/
    private double calculateY2Value(double spaceForGraph, NameSurferEntry associatedEntry, int i) {
        double y2;
        double y2IfPopularityIsZero = getHeight() - GRAPH_MARGIN_SIZE;
        double y2IfPopularityNotZero = ((spaceForGraph / MAX_RANK) * associatedEntry.getRank(i + 1)) + GRAPH_MARGIN_SIZE;

        if (associatedEntry.getRank(i + 1) == 0) {
            y2 = y2IfPopularityIsZero;
        } else {
            y2 = y2IfPopularityNotZero;
        }
        return y2;
    }

    /**
     * Draw the label with name and popularity on each decade.
     * <P>
     * If popularity 0, popularity values is replaced by *.
     * <P>
     * In another case draw popularity values.
     * <P>
     * Position label by x in always as i * sep. And by y it's always y1.*/
    private GLabel drawLabelWithNameAndPopularity(int i, double y1, int sep, Color color, NameSurferEntry associatedEntry) {
        String valuesForNameAndRankLabel;
        GLabel nameAndRankLabel;

        if (checkIfPopularityIsZero(associatedEntry, i)) {
            valuesForNameAndRankLabel = associatedEntry.getName() + " " + "*";
        } else {
            valuesForNameAndRankLabel = associatedEntry.getName() + " " + associatedEntry.getRank(i);
        }

        nameAndRankLabel = new GLabel(valuesForNameAndRankLabel);
        nameAndRankLabel.setLocation((i) * sep, y1);
        nameAndRankLabel.setColor(color);

        return nameAndRankLabel;
    }

    /**
     * Draw last label, there are to cases:
     * <P>
     * If popularity is 0, label y position is always on bot line.
     * <P>
     * If popularity > 0, label y position is always on y1.*/
    private GLabel drawLastLabelWithNameAndPopularity(int i, int sep, double spaceForGraph, NameSurferEntry associatedEntry, Color color) {
        String valuesForNameAndRankLabel;
        GLabel nameAndRankLabel;
        double x = i * sep;
        double y;

        if (checkIfPopularityIsZero(associatedEntry, i)) {
            valuesForNameAndRankLabel = associatedEntry.getName() + " " + "*";
            y = getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            valuesForNameAndRankLabel = associatedEntry.getName() + " " + associatedEntry.getRank(i);
            y = (spaceForGraph / MAX_RANK) * associatedEntry.getRank(i) + GRAPH_MARGIN_SIZE;
        }

        nameAndRankLabel = new GLabel(valuesForNameAndRankLabel);
        nameAndRankLabel.setLocation(x, y);
        nameAndRankLabel.setColor(color);

        return nameAndRankLabel;
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        allEntries.clear();
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        allEntries.add(entry);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        createBackground();

        // The loop is draw graph for each index in arrays.
        for (int i = 0; i < allEntries.size(); i++) {
            drawGraph(i);
        }
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
