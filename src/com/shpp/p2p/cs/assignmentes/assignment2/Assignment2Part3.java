package com.shpp.p2p.cs.bvorobiov.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* TODO: Replace these file comments with a description of what your program
 * does.
 */

public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     *
     * (Yes, I know that actual pawprints have four toes.
     * Just pretend it's a cartoon animal. ^_^)
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    // The method draws paws at specified coordinates on the x-axis and the y-axis
    private void drawPawprint(double x, double y) {

        // Draw the heel, which will be placed on the given axis X and Y + values from variables
        drawHeel(x, y);

        // Draw three toe whose coordinates are constructed on the same logic as the drawing of the heel
        drawFirstToe(x, y);
        drawSecondToe(x, y);
        drawThirdToe(x, y);
    }

    // Draw toes
    // The method accepts parameters - coordinates on the x and y-axis on which to draw the toes
    // These parameters are specified in setLocation
    private void drawThirdToe(double x, double y) {

        // Two variables that are responsible for the position of the figure on the x-axis and the y-axis
        double xAxisLocation = x+THIRD_TOE_OFFSET_X;
        double yAxisLocation = y+THIRD_TOE_OFFSET_Y;

        GOval thirdToe = new GOval(0, 0);
        thirdToe.setFilled(true);
        thirdToe.setFillColor(Color.black);
        thirdToe.setLocation(xAxisLocation, yAxisLocation);
        thirdToe.setSize(TOE_WIDTH, TOE_HEIGHT);
        add(thirdToe);
    }

    private void drawSecondToe(double x, double y) {

        // Two variables that are responsible for the position of the figure on the x-axis and the y-axis
        double xAxisLocation = x+SECOND_TOE_OFFSET_X;
        double yAxisLocation = y+SECOND_TOE_OFFSET_Y;

        GOval secondToe = new GOval(0, 0);
        secondToe.setFilled(true);
        secondToe.setFillColor(Color.black);
        secondToe.setLocation(xAxisLocation, yAxisLocation);
        secondToe.setSize(TOE_WIDTH, TOE_HEIGHT);
        add(secondToe);
    }

    private void drawFirstToe(double x, double y) {

        // Two variables that are responsible for the position of the figure on the x-axis and the y-axis
        double xAxisLocation = x+FIRST_TOE_OFFSET_X;
        double yAxisLocation = y+FIRST_TOE_OFFSET_Y;

        GOval firstToe = new GOval(0, 0);
        firstToe.setFilled(true);
        firstToe.setFillColor(Color.black);
        firstToe.setLocation(xAxisLocation, yAxisLocation);
        firstToe.setSize(TOE_WIDTH, TOE_HEIGHT);
        add(firstToe);
    }

    // Draw heel
    // The method accepts parameters - coordinates on the x and y-axis on which to draw the heel
    // These parameters are specified in setLocation
    private void drawHeel(double x, double y) {

        // Two variables that are responsible for the position of the figure on the x-axis and the y-axis
        double xAxisLocation = x+HEEL_OFFSET_X;
        double yAxisLocation = y+HEEL_OFFSET_Y;

        GOval heel = new GOval(0, 0) ;
        heel.setFilled(true);
        heel.setFillColor(Color.black);
        heel.setLocation(xAxisLocation, yAxisLocation);
        heel.setSize(HEEL_WIDTH, HEEL_HEIGHT);
        add(heel);
    }
}


