package sprites;

import biuoop.DrawSurface;
import gamesetting.Const;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import gamesetting.Collector;

/**
 * The type Fire work.
 *

 */
public class FireWork extends Collector implements Sprite {
    private Point origin;
    private int lifeTime;
    private int timer;

    /**
     * Instantiates a new Fire work.
     *
     * @param origin   the origin
     * @param lifeTime the life time
     */
    public FireWork(Point origin, int lifeTime) {
        super();
        this.origin = origin;
        this.lifeTime = lifeTime;
        addNewFireWork();
    }

    private void addNewFireWork() {
        for (int i = 0; i < 360; i++) {
            Ball ball = new Ball(this.origin, Const.BALL_SIZE,
                    Const.COLOR_WHEEL[i % Const.COLOR_WHEEL.length]);
            ball.setVelocity(Velocity.fromAngleAndSpeed(i, i % Const.COLOR_WHEEL.length + 3));
            ball.addToGame(this);
            ball.setGameEnvironment(getEnvironment());
        }
    }

    private void removeAll() {
        for (int i = 0; i < 360; i++) {
            getSprites().removeOne();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        getSprites().drawAllOn(d);
    }

    @Override
    public void timePassed() {
        this.timer++;
        getSprites().notifyAllTimePassed();
        if (this.timer >= this.lifeTime) {
            this.timer = 0;
            removeAll();
            addNewFireWork();
        }
    }

}
