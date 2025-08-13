package background;

import biuoop.DrawSurface;
import gamesetting.Collector;
import gamesetting.Const;
import geometry.Velocity;
import interfaces.HitListener;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;

import java.awt.Color;


/**
 * The type Level 2 background.
 *

 */
public class Level2Background extends Collector implements Sprite, HitListener {
    private static final int TIME_REMAINS_ON_THE_SCREEN = 100;
    private static final Color LILAC = new Color(200, 162, 200);
    private static final Block BACKGROUND = new Block(Const.ORIGIN, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT, LILAC);
    private int timer;


    /**
     * Instantiates a new Level 2 background.
     */
    public Level2Background() {
        super();
        this.timer = 0;
    }

    /**
     * draw the Background on the drawSurface.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        BACKGROUND.drawOn(d);
        getSprites().drawAllOn(d);
    }

    /**
     * Tells the Background that time is moving.
     */
    @Override
    public void timePassed() {
        getSprites().notifyAllTimePassed();
        if (timer == 0) {
            getSprites().removeOne();
        } else {
            this.timer--;
        }

    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.timer = TIME_REMAINS_ON_THE_SCREEN;
        for (int i = 0; i < 180; i++) {
            Ball ball = new Ball(beingHit.center(), 2, beingHit.getColor());
            ball.setVelocity(Velocity.fromAngleAndSpeed(2 * i, i % 5 + 1));
            ball.setGameEnvironment(getEnvironment());
            getSprites().addSprite(ball);
        }
    }
}
