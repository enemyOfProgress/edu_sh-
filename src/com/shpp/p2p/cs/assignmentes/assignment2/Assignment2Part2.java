package com.shpp.p2p.cs.bvorobiov.assignment2;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.desktop.AppForegroundListener;

public class Assignment2Part2 extends WindowProgram {

    //DIAMETER is a constant that indicates the size of our circles
    public int DIAMETER = 200;

    //Set the program window size
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 500;

    /* Method description:
    * The method draws four circles in different 4 corners of the program window
    * and then applies a rectangle so that it closes 25% of each circle.
    * */
    public void run() {
        createIllusion();
    }

    // Creates ovals with rectangle
    private void createIllusion() {
        // Creating ovals
        leftUpOval();
        rightUpOval();
        leftLowOval();
        rightLowOval();

        // Create rectangle
        illusionRectangle();
    }

    // Create rectangle
    private void illusionRectangle() {
        // Specify the position of the figure on the x-axis and the y-axis
        int xAxisLocation = DIAMETER / 2;
        int yAxisLocation = DIAMETER / 2;

        // Specify the size of the figure on the x-axis and the y-axis
        int figureWidthWidth = getWidth() - DIAMETER;
        int figureHeight = getHeight() - DIAMETER;

        GRect rectangle = new GRect(0, 0);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.white);
        rectangle.setColor(Color.WHITE);
        rectangle.setLocation(xAxisLocation, yAxisLocation);
        rectangle.setSize(figureWidthWidth, figureHeight);
        add(rectangle);
    }

    // Creates ovals
    private void rightLowOval() {
        // Specify the position of the figure on the x-axis and the y-axis
        int xAxisLocation = getWidth() - DIAMETER;
        int yAxisLocation = getHeight() - DIAMETER;

        GOval rightLowerOval = new GOval(0, 0);
        rightLowerOval.setFilled(true);
        rightLowerOval.setFillColor(Color.BLACK);
        rightLowerOval.setLocation(xAxisLocation, yAxisLocation);
        rightLowerOval.setSize(DIAMETER,DIAMETER);
        add(rightLowerOval);
    }

    private void leftLowOval() {
        // Specify the position of the figure on the x-axis and the y-axis
        int xAxisLocation = 1;
        int yAxisLocation = getHeight() - DIAMETER;

        GOval leftLowerOval = new GOval(0, 0);
        leftLowerOval.setFilled(true);
        leftLowerOval.setFillColor(Color.BLACK);
        leftLowerOval.setLocation(xAxisLocation, yAxisLocation);
        leftLowerOval.setSize(DIAMETER, DIAMETER);
        add(leftLowerOval);
    }

    private void rightUpOval() {
        // Specify the position of the figure on the x-axis and the y-axis
        int xAxisLocation = getWidth()-DIAMETER;
        int yAxisLocation = 1;

        GOval rightUpperOval = new GOval(0, 0);
        rightUpperOval.setFilled(true);
        rightUpperOval.setFillColor(Color.BLACK);
        rightUpperOval.setLocation(xAxisLocation, yAxisLocation);
        rightUpperOval.setSize(DIAMETER, DIAMETER);
        add(rightUpperOval);
    }

    private void leftUpOval() {
        // Specify the position of the figure on the x-axis and the y-axis
        int xAxisLocation = 1;
        int yAxisLocation = 1;

        GOval leftUpperOval = new GOval(0, 0);
        leftUpperOval.setFilled(true);
        leftUpperOval.setFillColor(Color.BLACK);
        leftUpperOval.setLocation(xAxisLocation, yAxisLocation);
        leftUpperOval.setSize(DIAMETER, DIAMETER);
        add(leftUpperOval);
    }

}

