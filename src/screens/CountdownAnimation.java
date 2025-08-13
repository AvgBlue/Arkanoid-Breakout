package screens;

import biuoop.DrawSurface;
import interfaces.Animation;
import interfaces.Sprite;
import sprites.Block;
import geometry.Point;
import gamesetting.SpriteCollection;
import gamesetting.Const;

import java.awt.Color;


/**
 * The type Countdown animation.
 *

 */
public class CountdownAnimation implements Animation {

    private static final int SECONDS_TO_FRAME = 60;

    private static final int TEXT_SIZE = 50;
    private static final double LINE_X_RATIO = 1.0 / 2.0;
    private static final double LINE_Y_RATIO = 1.0 / 2.0;
    private static final Point BLOCK_START_POINT = new Point(Const.SCREEN_WIDTH * LINE_X_RATIO - 17,
            Const.SCREEN_HEIGHT * LINE_Y_RATIO - 50);
    private static final double BLOCK_WIDTH = 60;
    private static final double BLOCK_HEIGHT = 60;
    private static final Block BLOCK = new Block(BLOCK_START_POINT, BLOCK_WIDTH, BLOCK_HEIGHT, Color.darkGray);

    private boolean stop;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sprite background;
    private int forFrame;
    private int count;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param background   the background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.forFrame = (int) (numOfSeconds * SECONDS_TO_FRAME / countFrom);
        this.count = countFrom;
        this.background = background;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (this.background != null) {
            this.background.drawOn(d);
        }
        this.gameScreen.drawAllOn(d);
        BLOCK.drawOn(d);
        switch (count) {
            case 1:
                d.setColor(Color.green);
                break;
            case 2:
                d.setColor(Color.yellow);
                break;
            case 3:
                d.setColor(Color.red);
                break;
            default:
                d.setColor(Color.white);
                break;
        }
        d.drawText((int) (d.getWidth() * LINE_X_RATIO), (int) (d.getHeight() * LINE_Y_RATIO),
                "" + this.count, TEXT_SIZE);
        this.forFrame -= 1;
        if (this.forFrame <= 0) {
            this.forFrame = (int) (numOfSeconds * SECONDS_TO_FRAME / countFrom);
            this.count -= 1;
        }
        if (count <= 0) {
            this.stop = true;
        }

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
