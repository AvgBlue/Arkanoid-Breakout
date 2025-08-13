package level;

import geometry.Point;
import geometry.Velocity;
import background.Level1Background;
import interfaces.Sprite;
import sprites.Block;
import gamesetting.Const;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 *

 */
public class Level1 extends EmptyLevel {

    /**
     * The constant SPACE_FROM_THE_TOP.
     */
    public static final int SPACE_FROM_THE_TOP = 200 + (int) Const.GAME_ORIGIN.getY();
    /**
     * The constant BLOCK_SIZE.
     */
    public static final int BLOCK_SIZE = 20;
    private static final int PADDLE_SPEED = 20;
    private static final int PADDLE_WIDTH = 200;
    private static final int NUMBER_OF_BALLS = 10;
    private static final Block BLOCK1 = new Block(new Point(200, Const.BALL_START_POINT.getY()),
            BLOCK_SIZE, BLOCK_SIZE, Color.red);
    private static final Block BLOCK2 = new Block(new Point(200, Const.BALL_START_POINT.getY() - 200),
            BLOCK_SIZE, BLOCK_SIZE, Color.red);

    /**
     * The initial velocity of each ball.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        List<Block> blockList = blocks();
        for (Block block : blockList) {
            Velocity velocity = new Velocity(Const.BALL_START_POINT, block.center());
            velocity = new Velocity(velocity.getDx() / 100, velocity.getDy() / 100);
            list.add(velocity);
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
        return "Remembering Sunday";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return new Level1Background(this);
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
        for (int i = 0; i < NUMBER_OF_BALLS; i += 2) {
            Point startPoint = new Point(Const.CENTER_OF_THE_SCREEN.getX() + 50 * (NUMBER_OF_BALLS / 2 - i),
                    SPACE_FROM_THE_TOP - 10 * i);
            Block block = new Block(startPoint, BLOCK_SIZE, BLOCK_SIZE, Color.red);
            list.add(block);
        }
        list.add(BLOCK1);
        list.add(BLOCK2);
        return list;
    }

    /**
     * a short discretion of the level.
     *
     * @return list of string
     */
    @Override
    public List<String> description() {
        List<String> list = new ArrayList<String>();
        list.add("secret cheat: press C to cheat");
        list.add("to get firework and win");
        return list;
    }
}
