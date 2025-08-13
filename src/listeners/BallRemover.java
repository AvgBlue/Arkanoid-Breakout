package listeners;

import gamesetting.Counter;
import interfaces.HitListener;
import interfaces.Collectables;
import sprites.Ball;
import sprites.Block;

/**
 * The type Ball remover.
 *

 */
public class BallRemover implements HitListener {
    private Collectables gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel           the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(Collectables gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
