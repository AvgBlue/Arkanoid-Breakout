package level;

import gamesetting.Const;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import sprites.Block;

import java.util.Random;
import java.awt.Color;

import background.Level2Background;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 2.
 *

 */
public class Level2 extends EmptyLevel {

    public static final int SPACE_FROM_THE_TOP = 200 + (int) Const.GAME_ORIGIN.getY();
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 400;
    private static final int ANGLE_BETWEEN_BALLS = 10;

    private Level2Background bg;
    private int numberOfBalls;

    /**
     * Instantiates a new Level 2.
     */
    public Level2() {
        this.bg = new Level2Background();
        Random rand = new Random();
        this.numberOfBalls = 2 + rand.nextInt(3);
    }

    /**
     * The initial velocity of each ball.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i <= this.numberOfBalls; i++) {
            list.add(Velocity.fromAngleAndSpeed(
                    -90 - (ANGLE_BETWEEN_BALLS * this.numberOfBalls / 2) + ANGLE_BETWEEN_BALLS * i, 4));
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
        return "Hey Monday";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return bg;
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
        for (int i = 0; i < Const.MAX_NUMBER_IN_A_ROW; i++) {
            Point startPoint = new Point(Const.BORDER_SIZE + Const.BLOCK_WIDTH * i,
                    SPACE_FROM_THE_TOP);
            Color color = Const.COLOR_WHEEL[i % Const.COLOR_WHEEL.length];
            Block newBlock = new Block(startPoint, Const.BLOCK_WIDTH, Const.BLOCK_HIGHT, color);
            newBlock.addHitListener(this.bg);
            list.add(newBlock);
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
        List<String> list = new ArrayList<String>(super.description());
        list.add("Every block will explode");
        list.add("enjoy the show");
        return list;
    }
}
