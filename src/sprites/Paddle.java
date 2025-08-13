package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import gamesetting.GameLevel;
import geometry.Rectangle;
import gamesetting.Const;


/**
 * The type Collision info.
 *

 */
public class Paddle implements Sprite, Collidable {

    private static final int SCREEN_WIDTH = Const.SCREEN_WIDTH;

    private static final int THE_FIRST_ANGLE = 30;
    private static final int THE_SECOND_ANGLE = 60;
    private static final int THE_FOURTH_ANGLE = 120;
    private static final int THE_FIFTH_ANGLE = 150;

    private Rectangle rect;
    private Color color;
    private double width;
    private Point position;
    private biuoop.KeyboardSensor keyboard;
    private double speed;


    /**
     * Instantiates a new gameObject.sprites.Paddle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param speed     the speed
     * @param color     the color
     */
    public Paddle(Point upperLeft, double width, double speed, Color color) {
        this.width = width;
        this.position = upperLeft;
        this.rect = createRect();
        this.color = color;
        this.speed = speed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double newX = this.position.getX() - this.speed;
        newX = clamp(newX, Const.BORDER_SIZE, SCREEN_WIDTH - Const.BORDER_SIZE - this.width);
        this.position = new Point(newX, this.position.getY());
        this.rect = createRect();
    }


    /**
     * Move right.
     */
    public void moveRight() {
        double newX = this.position.getX() + this.speed;
        newX = clamp(newX, Const.BORDER_SIZE, SCREEN_WIDTH - Const.BORDER_SIZE - this.width);
        this.position = new Point(newX, this.position.getY());
        this.rect = createRect();
    }


    /**
     * Checks which key is pressed on the keyboard and moves the gameObject.sprites.Paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * create a geometry.geometry.Rectangle for the paddle.
     *
     * @return the geometry.geometry.Rectangle.
     */
    private Rectangle createRect() {
        return new Rectangle(this.position, this.width, Const.PADDLE_HEIGHT);
    }

    /**
     * draw the paddle on the DrawSurface.
     *
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(round(position.getX()), round(position.getY()),
                round(this.width), round(Const.PADDLE_HEIGHT));
        d.setColor(Color.BLACK);
        d.drawRectangle(round(position.getX()), round(position.getY()),
                round(this.width), round(Const.PADDLE_HEIGHT));
    }


    /**
     * get the collision geometry.geometry.Rectangle.
     *
     * @return the geometry.geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * return the new geometry.Velocity after hitting the paddle.
     *
     * @param hitter          the hitter ball
     * @param collisionPoint  the point where the collision happened.
     * @param currentVelocity the current geometry.Velocity
     * @return the new geometry.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int region = 0;
        double[] regions = this.regions();
        for (int i = 0; i < 5; i++) {
            if (regions[i] <= collisionPoint.getX() && collisionPoint.getX() <= regions[i + 1]) {
                region = i;
            }
        }
        switch (region) {
            case 0:
                currentVelocity = Velocity.fromAngleAndSpeed(THE_FIRST_ANGLE, -currentVelocity.getSpeed());
                break;
            case 1:
                currentVelocity = Velocity.fromAngleAndSpeed(THE_SECOND_ANGLE, -currentVelocity.getSpeed());
                break;
            case 2:
                currentVelocity.reflectDy();
                break;
            case 3:
                currentVelocity = Velocity.fromAngleAndSpeed(THE_FOURTH_ANGLE, -currentVelocity.getSpeed());
                break;
            case 4:
                currentVelocity = Velocity.fromAngleAndSpeed(THE_FIFTH_ANGLE, -currentVelocity.getSpeed());
                break;
            default:
                break;
        }
        return currentVelocity;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
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
     * Sets keyboard.
     *
     * @param keyboard the keyboard
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Regions double [ ].
     *
     * @return the double [ ]
     */
    public double[] regions() {
        double[] regions = new double[6];
        for (int i = 0; i < 6; i++) {
            regions[i] = this.position.getX() + (i * this.width) / 5;
        }
        return regions;
    }


}
