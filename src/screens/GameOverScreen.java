package screens;

import biuoop.DrawSurface;
import gamesetting.Counter;
import interfaces.Animation;

/**
 * The type Pause screen.
 *

 */
public class GameOverScreen implements Animation {

    private static final double FIRST_LINE_X_RATIO = 1.0 / 5.0;
    private static final double FIRST_LINE_Y_RATIO = 1.0 / 2.0;
    private static final double SECOND_LINE_X_RATIO = 1.0 / 5.0;
    private static final double SECOND_LINE_Y_RATIO = 3.0 / 4.0;

    private boolean stop;
    private Counter currentScore;


    /**
     * Instantiates a new Game over screen.
     *
     * @param currentScore the current score
     */
    public GameOverScreen(Counter currentScore) {
        this.stop = false;
        this.currentScore = currentScore;
    }
    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText((int) (d.getWidth() * FIRST_LINE_X_RATIO), (int) (d.getHeight() * FIRST_LINE_Y_RATIO),
                "GameOver x_X Your score is " + this.currentScore.getValue(), 32);
        d.drawText((int) (d.getWidth() * SECOND_LINE_X_RATIO), (int) (d.getHeight() * SECOND_LINE_Y_RATIO),
                "press space to close the game", 32);
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
