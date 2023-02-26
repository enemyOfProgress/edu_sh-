package com.shpp.p2p.cs.bvorobiov.assignment2;

import acm.graphics.GFillable;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {

    // Set the program window size
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;

    public void run() {
        /*
        *   First we enter the values to variables:
        *   Figure height
        *   Figure width
        *   X-axis arrangement
        *   Y-axis arrangement
        */
        int figureHeight = 300;
        int figureWidth = 100;
        int xAxisLocationFigure = getWidth()/2 - (figureWidth + figureWidth/2);
        int yAxisLocationFigure = getHeight()/2 - figureHeight/2;

        // In the bottom right corner, write "Flag of Côte d'Ivoire"
        createPhrase();

        // Draw three rectangles that represent the flag
        add(drawRect(Color.orange, xAxisLocationFigure, yAxisLocationFigure, figureWidth, figureHeight));
        add(drawRect(Color.white, xAxisLocationFigure + figureWidth, yAxisLocationFigure, figureWidth, figureHeight));
        add(drawRect(Color.green, xAxisLocationFigure + figureWidth*2, yAxisLocationFigure, figureWidth, figureHeight));

    }

    // The calculation of the position of the phrase is based on the width and height of the window
    // As well as the width and height of the string block
    private void createPhrase() {
        GLabel countryName = new GLabel("Flag of Côte d'Ivoire");
        countryName.setFont("Verbana-20");
        countryName.setLocation(getWidth() - countryName.getWidth(), getHeight() - countryName.getHeight()/4);
        add(countryName);
    }

    // The method returns a rectangle with the specified parameters
    private GRect drawRect(Color color, int xAxis, int yAxis, int figureWidth, int figureHeight) {
        GRect flag = new GRect(xAxis, yAxis, figureWidth, figureHeight);
        fillRect(flag, color);
        return flag;
    }

    // The method specifies that the rectangle will be filled in - true
    // And that the triangle will use color to paint its background
    private void fillRect(GFillable rect, Color color) {
        rect.setFilled(true);
        rect.setFillColor(color);
    }
}

