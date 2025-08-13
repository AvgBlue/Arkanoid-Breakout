package background;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Line;
import geometry.Velocity;
import interfaces.Sprite;
import listeners.BlockRemover;
import sprites.Block;
import gamesetting.Const;
import gamesetting.Counter;
import biuoop.KeyboardSensor;
import sprites.Ball;
import level.Level4;
import listeners.BallRemover;
import gamesetting.Collector;

import java.awt.Color;
import java.util.List;


/**
 * The type Level 4 background.
 *

 */
public class Level4Background extends Collector implements Sprite {
    private static final Block BACKGROUND = new Block(Const.ORIGIN, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT,
            Color.black);
    private static final int NUMBER_OF_BULLETS = 5;
    private int angle;
    private int charge;
    private static final Block GRAY_BAR = new Block(new Point(100, 300), 50, 200, Color.lightGray);

    /**
     * Instantiates a new Level 4 background.
     *
     * @param level the level
     */
    public Level4Background(Level4 level) {
        super();
        this.angle = -90;
        this.charge = 0;
        List<Block> borders = level.borders();
        for (Block block : borders) {
            getEnvironment().addCollidable(block);
        }
        BlockRemover blockRemover = new BlockRemover(this, new Counter(0));
        BallRemover ballRemover = new BallRemover(this, new Counter(0));
        List<Block> blocks = level.blocks();
        for (Block block : blocks) {
            block.addHitListener(blockRemover);
            block.addHitListener(ballRemover);
            getEnvironment().addCollidable(block);
        }
    }

    /**
     * draw the Background on the drawSurface.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        BACKGROUND.drawOn(d);
        GRAY_BAR.drawOn(d);

        Block redBar = new Block(new Point(100, 300), 50, this.charge, Color.blue);
        redBar.drawOn(d);
        d.setColor(Color.blue);
        Line line = new Line(Const.BALL_START_POINT,
                Velocity.fromAngleAndSpeed(this.angle, 100).applyToPoint(Const.BALL_START_POINT));
        line.drawOn(d);

        getSprites().drawAllOn(d);

    }

    /**
     * Tells the Background that time is moving.
     */
    @Override
    public void timePassed() {
        getSprites().notifyAllTimePassed();
        this.charge++;
        if (charge == 200) {
            for (int i = 0; i < NUMBER_OF_BULLETS; i++) {
                getSprites().removeOne();
            }
            for (int i = 0; i < NUMBER_OF_BULLETS; i++) {
                Ball ball = new Ball(Const.BALL_START_POINT, Const.BALL_SIZE, Color.blue);
                ball.setVelocity(Velocity.fromAngleAndSpeed(this.angle, 5 + i));
                ball.setGameEnvironment(getEnvironment());
                getSprites().addSprite(ball);
            }
            charge = 0;
        }
        if (Const.getGameKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.angle++;
        }
        if (Const.getGameKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            this.angle--;
        }
        this.angle = (int) clamp(this.angle, -130, -50);
    }

    /**
     * make number be a number between min and max.
     *
     * @param num the number we clamp
     * @param min the minimum the number can reach
     * @param max the maximun the number can reach
     * @return the new number
     */
    private double clamp(double num, double min, double max) {
        return Math.max(Math.min(num, max), min);
    }


}
