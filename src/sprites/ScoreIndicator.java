package sprites;

import biuoop.DrawSurface;
import geometry.Point;
import gamesetting.Counter;
import java.awt.Color;

/**
 * The type Score indicator.
 *

 */
public class ScoreIndicator extends Block {
    private static final double WIDTH_RATIO_SCORE = 4.0 / 10.0;
    private static final double WIDTH_RATIO_NAME = 6.0 / 10.0;
    private static final double HEIGHT_RATIO = 5.0 / 6.0;
    private static final double SIZE_RATIO = 7.0 / 8.0;

    private String levelName;

    private Counter currentScore;

    /**
     * Instantiates a new Score indicator.
     *
     * @param upperLeft    the upper left
     * @param width        the width
     * @param height       the height
     * @param color        the color
     * @param currentScore the current score
     * @param levelName    the level name
     */
    public ScoreIndicator(Point upperLeft, double width, double height, Color color,
                          Counter currentScore, String levelName) {
        super(upperLeft, width, height, color);
        this.currentScore = currentScore;
        this.levelName = levelName;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.black);
        d.drawText(round(super.getWidth() * WIDTH_RATIO_SCORE), round(super.getHeight() * HEIGHT_RATIO),
                "score: " + currentScore.getValue(), round(super.getHeight() * SIZE_RATIO));
        d.drawText(round(super.getWidth() * WIDTH_RATIO_NAME), round(super.getHeight() * HEIGHT_RATIO),
                "LEVEL NAME: " + this.levelName, round(super.getHeight() * SIZE_RATIO));
    }

    /**
     * round the number.
     *
     * @param num the number we round
     * @return ture or false
     */
    private int round(double num) {
        return (int) Math.round(num);
    }
}
