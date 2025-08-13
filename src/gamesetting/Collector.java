package gamesetting;

import interfaces.Sprite;
import interfaces.Collidable;
import interfaces.Collectables;

/**
 * The type Collector.
 *

 */
public abstract class Collector implements Collectables {
    /**
     * The Sprites.
     */
    private final SpriteCollection sprites;
    /**
     * The Environment.
     */
    private final GameEnvironment environment;

    /**
     * Instantiates a new Collector.
     */
    public Collector() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Add a collectable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }


    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
