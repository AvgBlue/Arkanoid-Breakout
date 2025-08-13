package level;

import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import sprites.Block;
import gamesetting.Const;
import background.Level3Background;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 3.
 *

 */
public class Level3 extends EmptyLevel {

    /**
     * The constant SPACE_FROM_THE_TOP.
     */
    public static final int SPACE_FROM_THE_TOP = 100 + (int) Const.GAME_ORIGIN.getY();
    private static final int NUMBER_IN_THE_FIRST_LINE = 15;
    private static final int NUMBER_OF_ROWS = 5;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 200;
    private static final int ANGLE_BETWEEN_BALLS = 10;
    private static final int NUMBER_OF_BALLS = 10;
    private static final Point WHEEL_START_POINT = new Point(100, 150);
    private Level3Background background;

    public Level3() {
        this.background = new Level3Background();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i <= NUMBER_OF_BALLS; i++) {
            list.add(Velocity.fromAngleAndSpeed(
                    -90 - (ANGLE_BETWEEN_BALLS * NUMBER_OF_BALLS / 2) + ANGLE_BETWEEN_BALLS * i, 4));
        }
        return list;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Charlotte And Tuesday";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = i + Const.MAX_NUMBER_IN_A_ROW - NUMBER_IN_THE_FIRST_LINE; j < Const.MAX_NUMBER_IN_A_ROW;
                 j++) {
                Point startPoint = new Point(Const.BORDER_SIZE + Const.BLOCK_WIDTH * j,
                        SPACE_FROM_THE_TOP + Const.BLOCK_HIGHT * i);
                Block newBlock = new Block(startPoint, Const.BLOCK_WIDTH, Const.BLOCK_HIGHT, Color.LIGHT_GRAY);
                list.add(newBlock);
            }
        }
        return list;
    }

    /**
     * a short discretion of the level.
     *
     * @return list of string
     */
    @Override
    public List<String> description() {
        List<String> list = new ArrayList<>(super.description());
        list.add("Beware of the spiral!!!");
        return list;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size() - 3;
    }
}
