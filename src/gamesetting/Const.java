package gamesetting;

import geometry.Point;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * The type Const.
 *

 */
public class Const {
    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 600;
    /**
     * The constant BORDER_SIZE.
     */
    public static final int BORDER_SIZE = 20;

    /**
     * The constant FRAME_SPER_SECOND.
     */
    public static final int FRAME_SPER_SECOND = 60;

    /**
     * The constant ORIGIN.
     */
    public static final geometry.Point ORIGIN = new Point(0, 0);

    /**
     * The constant GAME_ORIGIN.
     */
    public static final Point GAME_ORIGIN = new Point(0, BORDER_SIZE);

    /**
     * The constant MAX_NUMBER_IN_A_ROW.
     */
// 800-20-20=760 the width we have to for blocks
    // 760/20=38 the number of blocks we have in a row
    public static final int MAX_NUMBER_IN_A_ROW = 20;

    /**
     * The constant BLOCK_WIDTH.
     */
    public static final int BLOCK_WIDTH = 38;

    /**
     * The constant BLOCK_HIGHT.
     */
    public static final int BLOCK_HIGHT = 20;

    /**
     * The constant CENTER_OF_THE_SCREEN.
     */
    public static final Point CENTER_OF_THE_SCREEN = new Point(
            (double) (SCREEN_WIDTH) / 2, (double) (SCREEN_HEIGHT) / 2);

    /**
     * The constant BALL_SIZE.
     */
    public static final int BALL_SIZE = 5;

    /**
     * The constant PADDLE_HEIGHT.
     */
    public static final double PADDLE_HEIGHT = 20;
    /**
     * The constant PADDLE_Y.
     */
    public static final double PADDLE_Y = SCREEN_HEIGHT - 2 * BORDER_SIZE;
    /**
     * The constant PADDLE_START_POINT.
     */
    public static final Point PADDLE_START_POINT = new Point(
            (double) (SCREEN_WIDTH) / 2, SCREEN_HEIGHT - 2 * BORDER_SIZE);

    /**
     * The constant BALL_START_POINT.
     */
    public static final Point BALL_START_POINT = new Point(CENTER_OF_THE_SCREEN.getX(), PADDLE_START_POINT.getY() - 50);

    /**
     * The constant CLEAR_LEVEL_BONUS.
     */
    public static final int CLEAR_LEVEL_BONUS = 100;

    /**
     * The constant COLOR_WHEEL.
     */
    public static final Color[] COLOR_WHEEL = {Color.GREEN, Color.cyan, Color.RED, Color.MAGENTA, Color.ORANGE};

    /**
     * The constant keyboard.
     */
    private static KeyboardSensor keyboard;

    /**
     * Sets game keyboard.
     *
     * @param ky the ky
     */
    public static void setGameKeyboard(KeyboardSensor ky) {
        keyboard = ky;
    }

    /**
     * Gets game keyboard.
     *
     * @return the game keyboard
     */
    public static KeyboardSensor getGameKeyboard() {
        return keyboard;
    }

}
