package com.shpp.p2p.cs.bvorobiov.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    // Set the program window size
    public static final double APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 800;

    /* The number of rows and columns in the grid, respectively. */
    private static final double NUM_ROWS = 5;
    private static final double NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 60;
    private static final double WIDTH_BOXES_IN_ROW = BOX_SIZE * NUM_COLS;
    private static final double HEIGHT_BOXES_IN_ROW = BOX_SIZE * NUM_ROWS;


    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;


    // The method draws a matrix of squares with the number of rows and columns that are specified in the variables NUM_ROWS and NUM_COLS
    public void run() {
            drawMatrix(NUM_ROWS, NUM_COLS);
    }

    // Method contains two cycles, main and nested
    // The main method draws rows and the nested columns.
    // It turns out that until the nested cycle is fully executed, the main will not go to the new iteration
    private void drawMatrix(double numberOfRows, double numberOfCols) {
        for (double i = 0; i < numberOfRows; i++){
            for (double j = 0; j < numberOfCols; j++){
                drawSquareCols(i, j);
            }
        }
    }

    // The method draws a square at the given coordinates in the axes X and Y
    // and multiplies the square by the values from the variables NUM_ROWS and NUM_COLS
    private void drawSquareCols(double numberOfRows, double numberOfCols) {

        // Two variables that are responsible for the arrangement of the violet on the x-axis and the y-axis
        double xAxisLocation = getWidth()/2 - WIDTH_BOXES_IN_ROW/2;
        double yAxisLocation = getHeight()/2 - HEIGHT_BOXES_IN_ROW/2;

        GRect squareForMatrix = new GRect(0, 0);
        squareForMatrix.setFilled(true);
        squareForMatrix.setFillColor(Color.black);
        squareForMatrix.setLocation((xAxisLocation) + BOX_SIZE * numberOfCols, (yAxisLocation) + BOX_SIZE * numberOfRows);
        squareForMatrix.setSize(BOX_SIZE - BOX_SPACING, BOX_SIZE - BOX_SPACING);
        add(squareForMatrix);
    }
}

