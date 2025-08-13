package level;

import gamesetting.Const;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import sprites.Block;
import background.Level4Background;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 4.
 */
public class Level4 extends EmptyLevel {

    /**
     * The constant SPACE_FROM_THE_TOP.
     */
    public static final int SPACE_FROM_THE_TOP = 100 + (int) Const.GAME_ORIGIN.getY();
    private static final int NUMBER_OF_ROWS = 5;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 75;

    private static final Velocity INITIAL_BALL_VELOCITY1 = Velocity.fromAngleAndSpeed(70, -4);
    private static final Velocity INITIAL_BALL_VELOCITY2 = Velocity.fromAngleAndSpeed(20, -4);
    private static final Velocity INITIAL_BALL_VELOCITY3 = Velocity.fromAngleAndSpeed(10, -4);

    private static List<Block> blockList = null;
    private Level4Background background;

    /**
     * Instantiates a new Level 4.
     *

     */
    public Level4() {
        this.background = new Level4Background(this);
        blocks();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(INITIAL_BALL_VELOCITY1);
        list.add(INITIAL_BALL_VELOCITY2);
        list.add(INITIAL_BALL_VELOCITY3);
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
        return "Blue Wednesday";
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
        if (blockList != null) {
            return blockList;
        }
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Const.MAX_NUMBER_IN_A_ROW; j++) {
                Point startPoint = new Point(Const.BORDER_SIZE + Const.BLOCK_WIDTH * j,
                        SPACE_FROM_THE_TOP + Const.BLOCK_HIGHT * i);
                Block newBlock = new Block(startPoint, Const.BLOCK_WIDTH,
                        Const.BLOCK_HIGHT, Const.COLOR_WHEEL[(i + j) % Const.COLOR_WHEEL.length]);
                list.add(newBlock);
            }
        }
        blockList = list;
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
        list.add("1) Aim the line");
        list.add("2) Wait for the bar to fill up");
        list.add("3) Fire!!!");
        return list;
    }
}
