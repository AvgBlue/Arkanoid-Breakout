package gamesetting;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;

/**
 * The type interfaces.Sprite collection.
 *

 */
public class SpriteCollection {

    private ArrayList<Sprite> spriteArray;

    /**
     * Instantiates a new interfaces.Sprite collection.
     */
    public SpriteCollection() {
        this.spriteArray = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteArray.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spriteArray.remove(s);
    }

    /**
     * Remove one sprite.
     */
    public void removeOne() {
        if (this.spriteArray.size() != 0) {
            this.spriteArray.remove(this.spriteArray.get(0));
        }
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> runOn = new ArrayList<>(this.spriteArray);
        for (Sprite sprite : runOn) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> runOn = new ArrayList<>(this.spriteArray);
        for (Sprite sprite : runOn) {
            sprite.drawOn(d);
        }
    }
}
