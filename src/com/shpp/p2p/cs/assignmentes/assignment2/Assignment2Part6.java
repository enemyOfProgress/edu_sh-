package com.shpp.p2p.cs.bvorobiov.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    // Set the program window size
    public static final double APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    // Specify the position of the figure on the x-axis and on the y-axis
    public int X_AXIS = 30;
    public int Y_AXIS = 100;

    // Number of circle in caterpillar
    public int NUM_CATERPILLAR_CIRCLE = 15;

    // Specify the circle diameter
    public double DIAMETER = 150;

    // Space between circles is 60% of diameter
    public double SPACE_BETWEEN_CIRCLES = DIAMETER * 0.6;

    public void run() {

        // Drawing the caterpillar
        drawTheCaterpillar();
    }

    private void drawTheCaterpillar() {

        // Circles will be drawn as much as specified in the NUM_CATERPILLAR_CIRCLE variable
        for (int i = 0; i < NUM_CATERPILLAR_CIRCLE; i++) {

            // In this case, the i variable takes its value from the NUM_CATERPILLAR_CIRCLE
            // Because in the second condition we specify that it is smaller than this value and will increase with each cycle
            drawCaterpillar(i);
        }
    }

    // In general, this method describes how exactly will be drawn mugs for the caterpillar,
    // at what points on the axes they will be located and their diameter
    private void drawCaterpillar(int caterpillar_circles) {

        // We declare a variable to be used for each odd-numbered circle
        // It is equal to the value we put in the variable Y_AXIS, which is responsible for the point on the axis Y of the figure
        int oddCaterpillarCircleLocation = Y_AXIS;

        // This condition says that if the circle is even, draw it offset by a quarter of the value of the basic Y_AXIS
        // If odd - leave it unchanged
        if (caterpillar_circles % 2 == 0) {
            oddCaterpillarCircleLocation -= Y_AXIS/4;
        }

        // Specify the position of the figure on the x-axis and the y-axis
        int xAxisLocation = (X_AXIS + X_AXIS/4);
        int yAxisLocation = oddCaterpillarCircleLocation;

        // Here we just draw our circle
        GOval caterpillar_circle = new GOval(0, 0);
        caterpillar_circle.setColor(Color.orange);
        caterpillar_circle.setFilled(true);
        caterpillar_circle.setFillColor(Color.green);
        caterpillar_circle.setLocation(xAxisLocation * caterpillar_circles, yAxisLocation);
        caterpillar_circle.setSize(DIAMETER - SPACE_BETWEEN_CIRCLES, DIAMETER - SPACE_BETWEEN_CIRCLES);
        add(caterpillar_circle);
    }
}





