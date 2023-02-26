package com.shpp.p2p.cs.bvorobiov.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {

    // Set the program window size
    public static final double APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    // Set the height and width of the brick
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_WIDTH = 30;

    // Set value of bricks in rows and columns
    private static final int BRICK_IN_ROWS = 17;
    private static int BRICK_IN_COLS = 17;

    // Set spacing between bricks
    private static final int SPACING_BETWEEN_BRICKS = 5;

    // Dividing by half
    private static final int HALVE = 2;

    // Set value width and height of pyramid
    private int widthWall = (BRICK_WIDTH * BRICK_IN_COLS) + SPACING_BETWEEN_BRICKS;
    private int heightWall = (BRICK_HEIGHT * BRICK_IN_ROWS)- SPACING_BETWEEN_BRICKS + BRICK_HEIGHT;

    // Method draws pyramid
    public void run() {
        drawThePyramid(BRICK_IN_ROWS, BRICK_IN_COLS);
    }

    // The method describes exactly how our bricks will look and where to place on the canvas
    private void drawTheBrick(int brickRow, int brickCol) {
        // Two variables that are responsible for the arrangement of the violet on the x-axis and the y-axis
        double xAxisLocation = getWidth()/HALVE - widthWall/HALVE;
        double yAxisLocation = getHeight() - heightWall;

        GRect brickForPyramid = new GRect(0, 0);
        brickForPyramid.setFilled(true);
        brickForPyramid.setFillColor(Color.orange);
        brickForPyramid.setLocation((xAxisLocation) + BRICK_WIDTH * brickRow, (yAxisLocation) + BRICK_HEIGHT * brickCol);
        brickForPyramid.setSize(BRICK_WIDTH-SPACING_BETWEEN_BRICKS, BRICK_HEIGHT-SPACING_BETWEEN_BRICKS);
        add(brickForPyramid);
    }

    /*
    * Pyramid Building Cycle
    * The main cycle lays the bricks on the x-axis, it is responsible for the number of rows
    * Naturally, to build a pyramid we need as many rows as columns on the first iteration
    * The nested cycle responsible how many bricks we put in each row
    * After iteration of the nested cycle, we take one brick each in the main cycle
    * Also we, that each next line is located in the center we subtract the width of the brick, then on each edge will be indented into half brick
    * */
    private void drawThePyramid(int brickInRows, int brickInCols) {
       for (int i = brickInRows; i > 0; i--){
           for (int k = 0; k < brickInCols; k++){
               drawTheBrick(k, i);
           }
           widthWall -= BRICK_WIDTH;
           brickInCols -= 1;
       }
    }
}

