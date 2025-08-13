package background;

import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.LevelInformation;
import interfaces.Sprite;
import gamesetting.Const;
import sprites.Block;
import geometry.Point;
import java.util.List;


/**
 * The type Level 1 background.
 *

 */
public class Level1Background implements Sprite {
    private static final Block BACKGROUND = new Block(Const.ORIGIN, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT,
            Color.black);
    private LevelInformation level;

    /**
     * Instantiates a new Level 1 background.
     *
     * @param level the level
     */
    public Level1Background(LevelInformation level) {
        this.level = level;
    }

    /**
     * draw the Background on the drawSurface.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        BACKGROUND.drawOn(d);
        List<Block> blockList = this.level.blocks();
        for (Block block : blockList) {
            Point start = block.center();
            d.setColor(Color.blue);
            d.drawCircle((int) start.getX(), (int) start.getY(), 40);
            d.drawCircle((int) start.getX(), (int) start.getY(), 30);
            d.drawCircle((int) start.getX(), (int) start.getY(), 20);
            d.drawCircle((int) start.getX(), (int) start.getY(), 10);
            d.drawLine((int) start.getX() - 45, (int) start.getY(), (int) start.getX() + 45, (int) start.getY());
            d.drawLine((int) start.getX(), (int) start.getY() - 45, (int) start.getX(), (int) start.getY() + 45);
        }
    }

    /**
     * Tells the Background that time is moving.
     */
    @Override
    public void timePassed() {
    }
}
