package background;

import biuoop.DrawSurface;
import gamesetting.Collector;
import gamesetting.Const;
import geometry.Point;
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
    // Reduce points significantly by stepping angle; keeps look but faster
    private static final int TOTAL_DEGREES = 360 * 7;
    private static final int ANGLE_STEP_DEG = 3; // draw every 3 degrees
    private static final int POINTS = TOTAL_DEGREES / ANGLE_STEP_DEG; // 840 points
    private static final int CUBE_SIZE = 10;
    private static final int CIRCLE_RADIUS = CUBE_SIZE / 2;
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
        // Draw directly for performance: avoid per-frame Sprite/Block creation
        d.setColor(Color.BLACK);
        Point center = Const.CENTER_OF_THE_SCREEN;
        double cx = center.getX();
        double cy = center.getY();
        int prevX = 0;
        int prevY = 0;
        boolean hasPrev = false;
        for (int k = 0; k < POINTS; k++) {
            int angleDeg = k * ANGLE_STEP_DEG - this.offset;
            double angleRad = Math.toRadians(angleDeg);
            double radius = 0.1 * (k * ANGLE_STEP_DEG);
            int x = (int) Math.round(cx + Math.cos(angleRad) * radius);
            int y = (int) Math.round(cy + Math.sin(angleRad) * radius);
            if (hasPrev) {
                d.drawLine(prevX, prevY, x, y);
            }
            prevX = x;
            prevY = y;
            hasPrev = true;
        }
    }

    /**
     * Tells the Background that time is moving.
     */
    @Override
    public void timePassed() {
        this.offset = (this.offset + 1) % 360;
    }
}
