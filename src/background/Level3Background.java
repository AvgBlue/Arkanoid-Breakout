package background;

import biuoop.DrawSurface;
import gamesetting.Collector;
import gamesetting.Const;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import sprites.Block;

import java.awt.Color;


/**
 * The type Level 3 background.
 *

 */
public class Level3Background extends Collector implements Sprite {
    private static final Block BACKGROUND = new Block(Const.ORIGIN, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT,
            Color.red);
    private static final int NUMBER_OF_CUBES = 360 * 7;
    private static final int CUBE_SIZE = 10;
    /**
     * The constant SPACE_FROM_THE_TOP.
     */
    private int offset;

    public Level3Background() {
        super();
        this.offset = 0;
    }

    /**
     * draw the Background on the drawSurface.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        BACKGROUND.drawOn(d);
        for (int i = 0; i < NUMBER_OF_CUBES; i++) {
            getSprites().removeOne();
        }
        for (int i = 0; i < NUMBER_OF_CUBES; i++) {
            Point startPoint = Velocity.fromAngleAndSpeed(i - this.offset, 0.1 * i).applyToPoint(
                    Const.CENTER_OF_THE_SCREEN);
            Block newBlock = new Block(startPoint, CUBE_SIZE, CUBE_SIZE, Color.black);
            getSprites().addSprite(newBlock);
        }
        getSprites().drawAllOn(d);
    }

    /**
     * Tells the Background that time is moving.
     */
    @Override
    public void timePassed() {
        this.offset++;
        if (this.offset == 360) {
            this.offset = 0;
        }
    }
}
