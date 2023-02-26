package com.shpp.p2p.cs.bvorobiov.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

import static acm.util.MediaTools.beep;

public class BreakoutExt extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns.
     */
    private static final int NTURNS = 3;

    /**
     * Pause for ball animation.
     */
    private static final double PAUSE_TIME = 1000.0 / 120;

    /**
     * Delta x and y for moving ball.
     */
    private double vx, vy;
    /**
     * Random number for dela x.
     */
    private RandomGenerator rgen = RandomGenerator.getInstance();

    /**
     * Counter for bricks.
     */
    private int bricksLeft = NBRICK_ROWS * NBRICKS_PER_ROW;

    /**
     * Creates the elemnts of game:
     * <p>
     * 1. Create paddle and set the parameters;
     * <p>
     * 2. Create ball and set the parameters;
     * <p>
     * 3. Create brick and set the parameters;
     * <p>
     * 4. Create first message for user.
     */
    private GRect paddle;
    private GOval ball;
    private GLabel ifReady;

    /**
     * 1. Count of user scores;
     * <p>
     * 2. Count user lives;
     * <p>
     * 3. Counter for lives.
     */
    GLabel countScores = null;
    GLabel countLives = null;
    private int numOfLives = NTURNS;

    /**
     * The main method that start the game.
     */
    public void run() {
        createWallAndPaddle();
        createCountScoresLabel();
        countLives();
        askUser();
        createBall();
        addMouseListeners();
        playTheGame();
        endGameIfLose();
    }


    /**
     * The method includes elements of game as bricks for wall, wall and paddle.
     */
    private void createWallAndPaddle() {
        createWall();
        createPaddle();
    }

    private void createCountScoresLabel() {
        countScores = new GLabel("Scores: ", 10, 30);
        countScores.setLocation(countScores.getWidth() / 4, countScores.getHeight());
        add(countScores);
    }

    private void countLives() {
        countLives = new GLabel("Lives: ", 10, 30);
        countLives.setLocation(countScores.getWidth() / 4, countScores.getHeight() + countLives.getHeight());
        add(countLives);

    }

    /**
     * The method builds wall of bricks.
     * <p>
     * There is we have rows, columns and colors of line.
     */
    private void createWall() {
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0, colorOfBricks = 0; col < NBRICKS_PER_ROW; col++, colorOfBricks++) {
                createBricks(col, row, colorOfBricks);
            }
        }
    }

    /**
     * Setup how exactly our bricks should be located, they size and colors.
     */
    private void createBricks(int rows, int cols, int colorOfBricks) {
        double positionX = BRICK_WIDTH + BRICK_SEP;

        Color[] colors = new Color[10];
        colors[0] = Color.red;
        colors[1] = Color.red;
        colors[2] = Color.orange;
        colors[3] = Color.orange;
        colors[4] = Color.yellow;
        colors[5] = Color.yellow;
        colors[6] = Color.green;
        colors[7] = Color.green;
        colors[8] = Color.cyan;
        colors[9] = Color.cyan;

        GRect brick = new GRect(0, 0);
        brick.setLocation(positionX * cols, BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * rows);
        brick.setFilled(true);
        brick.setColor(colors[colorOfBricks]);
        brick.setSize(BRICK_WIDTH, BRICK_HEIGHT);
        add(brick);
    }

    /**
     * Just create a paddle and setup it parameters.
     */
    private void createPaddle() {
        double positionX = 0;
        double positionY = getHeight() - PADDLE_Y_OFFSET;

        paddle = new GRect(0, 0);
        paddle.setLocation(positionX, positionY);
        paddle.setFilled(true);
        paddle.setFillColor(Color.black);
        paddle.setSize(PADDLE_WIDTH, PADDLE_HEIGHT);
        add(paddle);
    }

    /**
     * Here is first message for user before stat the game.
     */
    private void askUser() {
        // The values 150 and 50 it's not a magic numbers, it's need just for location label
        ifReady = new GLabel("Click left button mouse if ready");
        ifReady.setColor(Color.red);
        ifReady.setFont(new Font("Halo", Font.BOLD, 18));
        ifReady.setLocation(getWidth() / 2.0 - ifReady.getWidth() / 2.0, getHeight() / 2.0 - ifReady.getHeight());
        add(ifReady);
    }

    /**
     * Just create a ball and setup it parameters.
     */
    private void createBall() {
        double positionX = getWidth() / 2.0 - BALL_RADIUS;
        double positionY = getHeight() / 2.0 - BALL_RADIUS;

        ball = new GOval(0, 0);
        ball.setLocation(positionX, positionY);
        ball.setFilled(true);
        ball.setFillColor(Color.black);
        ball.setSize(BALL_RADIUS * 2, BALL_RADIUS * 2);
        add(ball);
    }

    /**
     * Override standard method.
     * <p>
     * 1. The method looks for mouse x position and give the coordinates to paddle;
     * <p>
     * 2. Update paddle position by mouse x position;
     * <p>
     * 3. If paddle x position is less than 0 or higher than width canvas, just stop moving paddle.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        double mousePositionX = e.getX() - paddle.getWidth() / 2;

        paddle.setLocation(mousePositionX, getHeight() - PADDLE_Y_OFFSET);

        if (paddle.getX() <= 0) {
            paddle.setLocation(0, getHeight() - PADDLE_Y_OFFSET);
        } else if (paddle.getX() + paddle.getWidth() >= getWidth()) {
            paddle.setLocation(getWidth() - paddle.getWidth(), getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /**
     * The method start the game
     * <p>
     * 1. User has 3 attempts;
     * <p>
     * 2. Firstly game waiting for click user;
     * <p>
     * 3. Then removes message for user;
     * <p>
     * 4. Then starts round and creates the ball;
     * <p>
     * 5. If user won (destroyed all bricks) the game is ended.
     */
    private void playTheGame() {
        for (int lives = 0; lives < NTURNS; lives++) {
            waitForClick();
            remove(ifReady);
            startRounds();
            createBall();
            if (checkIfWin()) {
                break;
            }
        }
    }

    /**
     * The method starts rounds and:
     * <p>
     * 1. Setups the delta x and y for ball;
     * <p>
     * 2. While true ball moves by delta x and y;
     * <p>
     * 4. If there is wall or paddle inf front of ball — reversing delta x and y;
     * <p>
     * 5. If there is brick — destroy brick and reversed delta x and y.
     */
    private void startRounds() {
        setupBallMoving();
        countLives.setLabel("Lives: " + (numOfLives));
        while (true) {
            getCollidingObject();

            ball.move(vx, vy);

            pause(PAUSE_TIME);

            countScores.setLabel(
                    "Score: " + ((NBRICKS_PER_ROW * NBRICK_ROWS) - bricksLeft)
            );

            if (checkIfLoseTry()) {
                numOfLives -= 1;
                remove(ball);
                break;
            }

            if (checkIfWin()) {
                endGameIfWin();
                break;
            }

            checkIfWall();

            checkIfPaddle();
        }
    }

    /**
     * Use rgen for determine random ball delta x.
     */
    private void setupBallMoving() {
        vy = 3;
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
    }

    /**
     * The method checks is there wall in front of ball and if its true — reversing delta x or y
     */
    private void checkIfWall() {
        if (ball.getX() + ball.getWidth() >= getWidth()) {
            vx = -vx;
        } else if (ball.getX() <= 0) {
            vx = -vx;
        } else if (ball.getY() + ball.getHeight() >= getHeight()) {
            vy = -vy;
        } else if (ball.getY() <= 0) {
            vy = -vy;
        } else if (ball.getY() <= 0) {
            remove(ball);
        }
    }

    /**
     * If ball hit the paddle — reversing delta x and y.
     */
    private void checkIfPaddle() {
        GObject collider = getCollidingObject();
        if (collider == countScores || collider == countLives) {
        } else if (collider == paddle) {
            if (ball.getY() + 2 * BALL_RADIUS > paddle.getY() && ball.getY() < paddle.getY()){
                vy = -vy;
                vx = -vx;
            }
            vy += 0.1;
            vy = -vy;
        } else if (collider != null) {
            remove(collider);
            beep();
            bricksLeft -= 1;
            vy = -vy;
        }
    }

    /**
     * The method checks if the ball hits something.
     * <p>
     * And if its not null — return the ball x and y.
     */
    private GObject getCollidingObject() {

        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
        } else if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
            return (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS));
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {
            return (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS));
        } else {
            return null;
        }
    }

    /**
     * The method describes if user lose the game
     * <p>
     * 1. Removes all elements;
     * <p>
     * 2. Shows message for user;
     * <p>
     * 3. Waits for click;
     * <p>
     * 4. And if user want, starts again.
     */
    private void endGameIfLose() {
        removeAll();
        messageIfLose();
        waitForClick();
        removeAll();
        bricksLeft = 100;
        numOfLives = NTURNS;
        run();
    }

    /**
     * Checks if a user loses try — if ball x >= height of the canvas — user losing the try.
     */
    private boolean checkIfLoseTry() {
        return ball.getY() + ball.getHeight() >= getHeight();
    }

    /**
     * The messages if user lose the game.
     */
    private void messageIfLose() {
        GLabel gameOver = new GLabel("GAME OVER");

        gameOver.setColor(Color.RED);
        gameOver.setFont(new Font("San-serif", Font.BOLD, 36));
        gameOver.setLocation(getWidth() / 2.0 - gameOver.getWidth() / 2.0, gameOver.getHeight());
        add(gameOver);

        GLabel scores = new GLabel("Your score is " + ((NBRICK_ROWS * NBRICKS_PER_ROW) - bricksLeft));
        scores.setColor(Color.BLUE);
        scores.setFont(new Font("San-serif", Font.BOLD, 18));
        scores.setLocation((getWidth() / 2.0 - scores.getWidth() / 2.0), gameOver.getHeight() + scores.getHeight());
        add(scores);

        GLabel playAgain = new GLabel("Click mouse button if you want play again");
        playAgain.setColor(Color.BLUE);
        playAgain.setFont(new Font("San-serif", Font.BOLD, 18));
        playAgain.setLocation(getWidth() / 2.0 - playAgain.getWidth() / 2.0, gameOver.getHeight() + scores.getHeight() + playAgain.getHeight());
        add(playAgain);
    }

    /**
     * The method describes if user won the game.
     * <p>
     * 1. Removes all elements;
     * <p>
     * 2. Shows message for winner;
     * <p>
     * 3. Waits for click;
     * <p>
     * 4. And starts again if user want to play.
     */
    private void endGameIfWin() {
        removeAll();
        messageIfWin();
        waitForClick();
        removeAll();
        bricksLeft = 100;
        run();
    }

    /**
     * Check count of bricks. If there are 0 bricks — user won the game.
     */
    private boolean checkIfWin() {
        return bricksLeft == 0;
    }

    /**
     * The messages if user won the game.
     */
    private void messageIfWin() {

        GLabel playerWin = new GLabel("You win!");
        playerWin.setColor(Color.RED);
        playerWin.setFont(new Font("San-serif", Font.BOLD, 36));
        playerWin.setLocation((getWidth() / 2.0 - playerWin.getWidth() / 2.0), playerWin.getHeight());
        add(playerWin);

        GLabel scores = new GLabel("Your score is " + (NBRICK_ROWS * NBRICKS_PER_ROW));
        scores.setColor(Color.BLUE);
        scores.setFont(new Font("San-serif", Font.BOLD, 18));
        scores.setLocation((getWidth() / 2.0 - scores.getWidth() / 2.0), playerWin.getHeight() + scores.getHeight());
        add(scores);

        GLabel playAgain = new GLabel("Click mouse button if you want play again");
        playAgain.setColor(Color.BLUE);
        playAgain.setFont(new Font("San-serif", Font.BOLD, 18));
        playAgain.setLocation(getWidth() / 2.0 - playAgain.getWidth() / 2, playerWin.getHeight() + scores.getHeight() + playAgain.getHeight());
        add(playAgain);
    }

}
