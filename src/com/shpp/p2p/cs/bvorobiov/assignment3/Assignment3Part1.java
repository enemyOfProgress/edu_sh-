package com.shpp.p2p.cs.bvorobiov.assignment3;


import com.shpp.cs.a.console.TextProgram;

import java.awt.geom.Arc2D;
import java.net.Proxy;

public class Assignment3Part1 extends TextProgram {

    // Here we record the number of days a person has achieved his cardio and blood pressure goal
    private int daysForCardioTraining;
    private int daysForBloodPressureTraining;

    // The variables indicate the minimum number of days per week in which a person should train
    private int trainingDaysMinimumCardio = 5;
    private int trainingDaysMinimumBloodPressure = 3;

    // This variable is needed to compare at each iteration whether a person has enough training per day
    private double trainingMinutesPerDay;

    // Number of days per week, use for cycle on request, how many people trained per day
    private int[] week = new int[]{1, 2, 3, 4, 5, 6, 7};

    // Variable for loop to substitute the day of the week from array above
    private int dayOfWeek;

    // Minimum minutes for training to reach the goal for the day
    private static final int MINIMUM_MINUTES_FOR_CARDIO = 30;
    private static final int MINIMUM_MINUTES_FOR_BLOOD_PRESSURE = 40;

    // Maximum and minimum number of minutes per day
    private static final int MAXIMUM_MINUTES_PER_DAY = 1440;
    private static final int MINIMUM_MINUTES_PER_DAY = 0;

    // Training long 8 Hours
    private static final int EXCESSIVE_TRAINING_MINUTES = 480;

    // For the training day counter
    private static final int ONE_DAY_TRAINING = 1;

    /** The method tells the person that the training duration is only accepted in minutes
     Next we consider whether the person has achieved the goal in certain days of the week
     And draw up a report on his training
     */
    public void run() {
        greeting();
        checkIsAllGoalReached();
        createHealthReport();
    }

    // The method includes two reports: cardio and blood pressure
    private void createHealthReport() {
        createCardioHealthReport();
        createBloodPressureReport();
    }

    /**
     * Lower is two method to reporting the person about his training and goals
     * The first one creates report about training for blood pressure
     * If person didn't train enough for blood pressure health, report him difference between training days and goal
     *
     * The second having same logic but for cardiovascular training
     * */
    private void createBloodPressureReport() {
        if (daysForBloodPressureTraining >= trainingDaysMinimumBloodPressure){
            println("Blood pressure:\n  "
                    + "Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("Blood pressure:\n  "
                    + "You needed to train hard for at least "
                    + (trainingDaysMinimumBloodPressure - daysForBloodPressureTraining)
                    + " more day(s) a week!");
        }
    }

    private void createCardioHealthReport() {
        if (daysForCardioTraining >= trainingDaysMinimumCardio){
            println("Cardiovascular health:\n  "
                    + "Great job! You've done enough exercise for cardiovascular health.");
        } else {
            println("Cardiovascular health:\n  "
                    + "You needed to train hard for at least "
                    + (trainingDaysMinimumCardio - daysForCardioTraining)
                    + " more day(s) a week!");
        }
    }

    /** The method describes how we will count the number of days in which a person has trained:
     > 30 min for cardio and > 40 min for blood pressure
     At each iteration we ask how many minutes the prison training, and if this number is in the range >30/40 and <1440
     Then we record it as a day with a workout
     If the user lies to us and enters an incorrect number, please enter the correct number
     */
    private void checkIsAllGoalReached() {

        for (int i = 0; i < week.length; i++) {

            dayOfWeek = week[i];

            trainingMinutesPerDay = readDouble("How many minutes did you do on day " + dayOfWeek + "?");
            reportIfUserTryToCheat();
            askUserAboutTraining();
        }
    }

    private void askUserAboutTraining() {
        if (trainingMinutesPerDay >= MINIMUM_MINUTES_FOR_CARDIO && trainingMinutesPerDay < MAXIMUM_MINUTES_PER_DAY) {
            if (trainingMinutesPerDay >= MINIMUM_MINUTES_FOR_BLOOD_PRESSURE){
                daysForBloodPressureTraining += ONE_DAY_TRAINING;
            }
            messageForTrainedMan();
            daysForCardioTraining += ONE_DAY_TRAINING;
        }
    }

    private void reportIfUserTryToCheat() {
        while (trainingMinutesPerDay < MINIMUM_MINUTES_PER_DAY || trainingMinutesPerDay > MAXIMUM_MINUTES_PER_DAY) {
            println("Don't cheat. This is an unreal number of minutes per day. Now enter a truthful number.");
            trainingMinutesPerDay = readDouble("How many minutes did you do on day " + dayOfWeek + "?");
        }
    }

    // Message for training fans
    private void messageForTrainedMan() {
        if (trainingMinutesPerDay > EXCESSIVE_TRAINING_MINUTES){
            println("You must be very trained.");
        }
    }

    // Remind the user to enter values in minutes
    private void greeting() {
        println("Hello. Remember that the program accepts values ONLY in minutes, so do not enter values in hours, because all calculations will take place only in minutes.");
    }
}




