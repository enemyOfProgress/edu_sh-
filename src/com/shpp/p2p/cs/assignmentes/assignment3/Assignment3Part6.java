package com.shpp.p2p.cs.bvorobiov.assignment3;

import acm.graphics.GLabel;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * It is a screensaver.
 * The program draw label and move it from side to side of canvas.*/
public class Assignment3Part6 extends WindowProgram {

    // Constants that are responsible for the program window boundaries
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;

    // Dividing by half
    public static final int HALVE = 2;

    // Constants that respond to how fast the figure will move on the x-axis and the y-axis
    private static final double X_AXIS_MOVE_SPEED = 3;
    private static final double Y_AXIS_MOVE_SPEED = 3;

    // Constant, which is responsible for what will be deduced in GLabel
    private static final String LABEL = "SH++";
    private static final int FONT_SIZE = 56;

    // Constant, which is responsible for what color the text will have when moving in different directions
    private static final Color LABEL_COLOR = new Color(255, 255, 255);
    private static final Color COLOR_ONE = new Color(123, 199, 222);
    private static final Color COLOR_TWO = new Color(120, 19, 200);
    private static final Color COLOR_THREE = new Color(250, 33, 20);
    private static final Color COLOR_FOUR = new Color(10, 222, 28);

    // Use these variables to indicate which coordinate to move - X or Y
    private double deltaX = X_AXIS_MOVE_SPEED;
    private double deltaY = Y_AXIS_MOVE_SPEED;

    // Just a timer for exit from program after 5 sec
    private double timerValue = System.currentTimeMillis() + 5200;

    // Pause time between frames
    private static final double PAUSE_TIME = 1000.0 / 120;

    // The method just draws a label for screensaver and starts moving it
    public void run() {
        GLabel shLabel = createLabel(LABEL);
        add(shLabel);
        // If you need to sure that the animation works 5 second, just uncomment println
//        println("start date is "+System.currentTimeMillis());
        startScreensaver(shLabel);
//        println("stop date is "+ System.currentTimeMillis());
    }

    /**
     * Method starts the animation and do it 5 sec (thanks while cycle)
     * Logic of the method is simply:
     * When label reaches canvas boundary, it pushes from the border and moving reversed
     * Also label changes the color when it pushes from the border*/
    private void startScreensaver(GLabel shLabel) {
        while (System.currentTimeMillis() <= timerValue) {
            shLabel.move(deltaX, deltaY);
            pause(PAUSE_TIME);
            if (shLabel.getX() + shLabel.getWidth() >= getWidth()) {
                deltaX = -X_AXIS_MOVE_SPEED;
                shLabel.setColor(COLOR_ONE);
            } else if (shLabel.getY() >= getHeight()) {
                deltaY = -Y_AXIS_MOVE_SPEED;
                shLabel.setColor(COLOR_TWO);
            } else if (shLabel.getY() - shLabel.getHeight() / 2 <= 0) {
                deltaY = Y_AXIS_MOVE_SPEED;
                shLabel.setColor(COLOR_THREE);
            } else if (shLabel.getX() <= 0) {
                deltaX = X_AXIS_MOVE_SPEED;
                shLabel.setColor(COLOR_FOUR);
            }
        }
    }


    // Just crate the label for screensaver
    private GLabel createLabel(String label) {
        // Just set the label location to start
        int xAxisLocation = getWidth()/HALVE;
        int yAxisLocation = getHeight()/HALVE;

        GLabel shLabel = new GLabel(label);
        setBackground(Color.black);
        shLabel.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        shLabel.setColor(LABEL_COLOR);
        shLabel.setLocation(xAxisLocation, yAxisLocation);
        return shLabel;
    }

}
