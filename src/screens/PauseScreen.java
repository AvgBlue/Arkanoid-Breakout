package screens;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import gamesetting.SpriteCollection;
import interfaces.Animation;
import interfaces.Sprite;
import sprites.Block;
import geometry.Point;
import gamesetting.Const;


/**
 * The type Pause screen.
 *

 */
public class PauseScreen implements Animation {


    private static final int TEXT_SIZE = 32;
    private static final double LINE_X_RATIO = 1.0 / 5.0;
    private static final double LINE_Y_RATIO = 1.0 / 2.0;
    private static final Point BLOCK_START_POINT = new Point(Const.SCREEN_WIDTH * LINE_X_RATIO - 30,
            Const.SCREEN_HEIGHT * LINE_Y_RATIO - 30);
    private static final double BLOCK1_WIDTH = 550;
    private static final double BLOCK1_HEIGHT = 50;
    private static final double BLOCK2_WIDTH = 550;
    private static final double BLOCK2_HEIGHT = 50;
    private static final Block BLOCK1 = new Block(BLOCK_START_POINT, BLOCK1_WIDTH, BLOCK1_HEIGHT, Color.WHITE);
    private static final Block BLOCK2 = new Block(BLOCK_START_POINT, BLOCK2_WIDTH, BLOCK2_HEIGHT, Color.WHITE);
    private boolean stop;
    private SpriteCollection sprites;
    private Sprite background;
    private List<String> description;

    /**
     * Instantiates a new Pause screen.
     *
     * @param sprites     the sprites
     * @param background  the background
     * @param description the description
     */
    public PauseScreen(SpriteCollection sprites, Sprite background, List<String> description) {
        this.stop = false;
        this.sprites = sprites;
        this.background = background;
        this.description = description;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        this.sprites.drawAllOn(d);
        BLOCK1.drawOn(d);
        d.drawText((int) (d.getWidth() * LINE_X_RATIO), (int) (d.getHeight() * LINE_Y_RATIO),
                "paused -- press space to continue", TEXT_SIZE);
        writeDescription(d);
    }

    /**
     * Write description.
     *
     * @param d the d
     */
    public void writeDescription(DrawSurface d) {
        Point startPoint = new Point(Const.SCREEN_WIDTH * LINE_X_RATIO - 30,
                Const.SCREEN_HEIGHT - 100 - 50 * this.description.size());
        Block block = new Block(startPoint, BLOCK2_WIDTH, BLOCK2_HEIGHT * this.description.size(), Color.WHITE);
        block.drawOn(d);
        for (int i = 0; i < this.description.size(); i++) {
            d.setColor(Color.red);
            d.drawText((int) (d.getWidth() * LINE_X_RATIO),
                    (int) (startPoint.getY() + BLOCK2_HEIGHT * (i + 1) - 12),
                    this.description.get(i), TEXT_SIZE);
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
