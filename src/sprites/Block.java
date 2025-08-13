package sprites;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

import geometry.Point;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Sprite;
import interfaces.HitNotifier;
import geometry.Rectangle;
import geometry.Line;
import geometry.Square;
import interfaces.Collectables;

import java.util.ArrayList;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rect;
    private Color color;
    private double width;
    private double height;


    /**
     * Instantiates a new gameObject.sprites.Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.rect.square().setColor(color);
        this.width = width;
        this.height = height;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * return the block's geometry.geometry.Rectangle.
     *
     * @return the block's geometry.geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * return the new geometry.Velocity after hitting the block.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the point where the collision happened.
     * @param currentVelocity the current geometry.Velocity
     * @return the new geometry.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        ArrayList<Line> sideHit = this.rect.pointOnSide(collisionPoint);
        if (sideHit.isEmpty()) {
            this.notifyHit(hitter);
            return currentVelocity;
        }
        if (sideHit.size() == 2) {
            Point intersectionPoint = sideHit.get(0).intersectionWith(sideHit.get(1));
            int cornerNumber = this.rect.getCornerNumber(intersectionPoint);
            this.notifyHit(hitter);
            return reflectionInCorner(cornerNumber, currentVelocity);
        } else if (sideHit.get(0).getFlag()) {
            currentVelocity.reflectDx();
            this.notifyHit(hitter);
            return currentVelocity;
        } else {
            currentVelocity.reflectDy();
            this.notifyHit(hitter);
            return currentVelocity;
        }
    }

    /**
     * draw the block on the DrawSurface.
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        Square square = this.getCollisionRectangle().square();
        d.setColor(this.color);
        Point start = square.start();
        d.fillRectangle(round(start.getX()), round(start.getY()),
                round(this.width), round(this.height));
        d.setColor(Color.BLACK);
        d.drawRectangle(round(start.getX()), round(start.getY()),
                round(this.width), round(this.height));
    }

    /**
     * do nothing for this assigment.
     */
    public void timePassed() {
        //I need to have something in for the checkstyle.
        return;
    }

    /**
     * implant what happened when the ball hit a corner.
     *
     * @param cornerNumber    the number of the corner.
     * @param currentVelocity the current geometry.Velocity.
     * @return a new geometry.Velocity;
     */
    private Velocity reflectionInCorner(int cornerNumber, Velocity currentVelocity) {
        switch (cornerNumber) {
            case 0:
                if (currentVelocity.isGoDown()) {
                    currentVelocity.reflectDy();
                }
                if (currentVelocity.isGoRight()) {
                    currentVelocity.reflectDx();
                }
                break;
            case 1:
                if (currentVelocity.isGoDown()) {
                    currentVelocity.reflectDy();
                }
                if (currentVelocity.isGoLeft()) {
                    currentVelocity.reflectDx();
                }
                break;
            case 2:
                if (currentVelocity.isGoUp()) {
                    currentVelocity.reflectDy();
                }
                if (currentVelocity.isGoLeft()) {
                    currentVelocity.reflectDx();
                }
                break;
            case 3:
                if (currentVelocity.isGoUp()) {
                    currentVelocity.reflectDy();
                }
                if (currentVelocity.isGoRight()) {
                    currentVelocity.reflectDx();
                }
                break;
            default:
                break;
        }
        return currentVelocity;
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

    /**
     * add the block to the game.
     *
     * @param g the game
     */
    public void addToGame(Collectables g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(Collectables g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets hit listeners.
     *
     * @return the hit listeners
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * Center point.
     *
     * @return the point
     */
    public Point center() {
        return this.rect.square().getLine().middle();
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets position.
     *
     * @param point the point
     */
    public void setPosition(Point point) {
        this.rect = new Rectangle(point, this.width, this.height);
    }
}
