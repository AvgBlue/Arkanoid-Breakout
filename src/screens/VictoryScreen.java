package screens;

import biuoop.DrawSurface;
import gamesetting.Const;
import gamesetting.Counter;
import interfaces.Animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import sprites.Block;
import sprites.FireWork;
import geometry.Point;


/**
 * The type Pause screen.
 *

 */
public class VictoryScreen implements Animation {
    private static final Point FIRE_WORK_POINT1 = new Point(100, 100);
    private static final Point FIRE_WORK_POINT2 = new Point(Const.SCREEN_WIDTH - 100, 100);
    private static final Point FIRE_WORK_POINT3 = new Point(100, Const.SCREEN_HEIGHT - 100);
    private static final Point FIRE_WORK_POINT4 = new Point(Const.SCREEN_WIDTH - 100, Const.SCREEN_HEIGHT - 100);
    private static final int FIRE_WORK_LIFE_TIME = 250;

    private static final Block BACKGROUND = new Block(Const.ORIGIN, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT,
            Color.black);
    private static final double FIRST_LINE_X_RATIO = 1.0 / 4.0;
    private static final double FIRST_LINE_Y_RATIO = 1.0 / 2.0;
    private static final double SECOND_LINE_X_RATIO = 1.0 / 5.0;
    private static final double SECOND_LINE_Y_RATIO = 9.0 / 16.0;
    private static final double LINE_X_RATIO = 1.0 / 5.0;
    private static final double LINE_Y_RATIO = 1.0 / 2.0;
    private static final Point BLOCK_START_POINT = new Point(Const.SCREEN_WIDTH * LINE_X_RATIO - 30,
            Const.SCREEN_HEIGHT * LINE_Y_RATIO - 32);
    private static final double BLOCK_WIDTH = 550;
    private static final double BLOCK_HEIGHT = 90;
    private static final Block BLOCK = new Block(BLOCK_START_POINT, BLOCK_WIDTH, BLOCK_HEIGHT, Color.WHITE);
    private boolean stop;
    private Counter currentScore;
    private int timing;
    private int colorSelect;
    private List<FireWork> fireWorkList;
    private int fireWorkTiming;


    /**
     * Instantiates a new Victory screen.
     *
     * @param currentScore the current score
     */
    public VictoryScreen(Counter currentScore) {
        super();
        this.stop = false;
        this.currentScore = currentScore;
        this.timing = 0;
        this.colorSelect = 0;
        this.fireWorkList = new ArrayList<>();
        this.fireWorkTiming = 0;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        BACKGROUND.drawOn(d);
        fireShow(d);
        BLOCK.drawOn(d);
        this.timing++;
        if (this.timing >= 10) {
            this.timing = 0;
            d.setColor(Const.COLOR_WHEEL[this.colorSelect]);
            this.colorSelect++;
            if (this.colorSelect == Const.COLOR_WHEEL.length) {
                this.colorSelect = 0;
            }
        }
        d.setColor(Const.COLOR_WHEEL[this.colorSelect]);
        d.drawText((int) (d.getWidth() * FIRST_LINE_X_RATIO), (int) (d.getHeight() * FIRST_LINE_Y_RATIO),
                "You Win! Your score is " + this.currentScore.getValue(), 32);
        d.setColor(Color.BLACK);
        d.drawText((int) (d.getWidth() * SECOND_LINE_X_RATIO), (int) (d.getHeight() * SECOND_LINE_Y_RATIO),
                "press space to close the game", 32);
    }

    /**
     * make a fireWork show.
     *
     * @param d the DrawSurface.
     */
    private void fireShow(DrawSurface d) {
        switch (this.fireWorkTiming) {
            case 0:
                this.fireWorkList.add(new FireWork(Const.CENTER_OF_THE_SCREEN, FIRE_WORK_LIFE_TIME));
                break;
            case 50:
                this.fireWorkList.add(new FireWork(FIRE_WORK_POINT1, FIRE_WORK_LIFE_TIME));
                break;
            case 100:
                this.fireWorkList.add(new FireWork(FIRE_WORK_POINT2, FIRE_WORK_LIFE_TIME));
                break;
            case 150:
                this.fireWorkList.add(new FireWork(FIRE_WORK_POINT3, FIRE_WORK_LIFE_TIME));
                break;
            case 200:
                this.fireWorkList.add(new FireWork(FIRE_WORK_POINT4, FIRE_WORK_LIFE_TIME));
                break;
            default:
                break;
        }
        for (FireWork fireWork : fireWorkList) {
            fireWork.drawOn(d);
            fireWork.timePassed();
        }
        if (this.fireWorkTiming != 201) {
            this.fireWorkTiming++;
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
