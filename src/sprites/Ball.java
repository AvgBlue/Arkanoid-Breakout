package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import interfaces.Collectables;
import biuoop.DrawSurface;
import gamesetting.CollisionInfo;
import gamesetting.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

/**
 * The type gameObject.sprites.Ball.
 */
public class Ball implements Sprite {

    private Point position;
    private int size;
    private Color color;

    private Velocity velocity;

    private GameEnvironment gameEv;

    /**
     * the constructor of gameObject.sprites.Ball.
     * with the geometry.geometry.Point center as the start locaion.
     *
     * @param center of the ball used as it location.
     * @param r      the radios of the ball.
     * @param color  color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.position = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * the constructor of gameObject.sprites.Ball.
     * with a (x,y) as the start location
     *
     * @param x     the x Component of the start location.
     * @param y     the y Component of the start location.
     * @param r     the radios of the ball.
     * @param color color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * return the x Component of the position of the ball.
     *
     * @return the x Component of the position of the ball
     */
    public int getX() {
        return (int) Math.round(this.position.getX());
    }

    /**
     * return the position as point.
     *
     * @return the position as point.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * return the y Component of the position of the ball.
     *
     * @return the y Component of the position of the ball
     */
    public int getY() {
        return (int) Math.round(this.position.getY());
    }

    /**
     * return the size of the ball.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * return the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) Math.round(this.position.getX()), (int) Math.round(this.position.getY()), this.size);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) Math.round(this.position.getX()), (int) Math.round(this.position.getY()), this.size);

    }


    /**
     * Tells the ball that time is moving.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * set the geometry.Velocity given as the geometry.Velocity to the ball.
     *
     * @param v geometry.Velocity we set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the Color given as the color to the ball.
     *
     * @param color color we set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * set the geometry.Velocity given as the geometry.Velocity to the ball.
     *
     * @param dx the dx Component of the geometry.Velocity
     * @param dy the dy Component of the geometry.Velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * return the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * make the ball move one step with his velocity.
     * if the ball going to jump out of bound it will correct for it.
     */
    public void moveOneStep() {
        this.position = makeOutside(this.position);
        Point nextPoint = this.getVelocity().applyToPoint(this.position);
        Line trajectory = new Line(this.position, nextPoint);
        CollisionInfo infoChan = this.gameEv.getClosestCollision(trajectory);
        if (infoChan != null) {
            nextPoint = makeNoClip(nextPoint, infoChan.collisionPoint(), infoChan.collisionObject());
            nextPoint = makeOutside(nextPoint);
            this.velocity = infoChan.collisionObject().hit(this, infoChan.collisionPoint(), this.velocity);
            setPosition(nextPoint);
            return;
        }
        setPosition(this.getVelocity().applyToPoint(this.position));
    }

    /**
     * set the position of the ball to (x,y).
     *
     * @param x the x Component of the point
     * @param y the y Component of the point
     */
    public void setPosition(double x, double y) {
        setPosition(new Point(x, y));
    }

    /**
     * set the position of the ball to the location of the point given.
     *
     * @param point the location we set.
     */
    public void setPosition(Point point) {
        this.position = point;
    }

    private Point makeNoClip(Point nextPoint, Point collisionPoint, Collidable object) {
        ArrayList<Line> sides = object.getCollisionRectangle()
                .pointOnSide(collisionPoint);
        for (Line side : sides) {
            Integer sideNum = object.getCollisionRectangle().getSideNumber(side);
            switch (sideNum) {
                case 0:
                    nextPoint = new Point(nextPoint.getX(), side.start().getY() - this.size);
                    break;
                case 1:
                    nextPoint = new Point(side.start().getX() + this.size, nextPoint.getY());
                    break;
                case 2:
                    nextPoint = new Point(nextPoint.getX(), side.start().getY() + this.size);
                    break;
                case 3:
                    nextPoint = new Point(side.start().getX() - this.size, nextPoint.getY());
                    break;
                default:
                    break;
            }
        }
        return nextPoint;
    }

    /**
     * Sets game environment.
     *
     * @param gameEv the game ev
     */
    public void setGameEnvironment(GameEnvironment gameEv) {
        this.gameEv = gameEv;
    }

    /**
     * add the block to the game.
     *
     * @param g the game
     */
    public void addToGame(Collectables g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(Collectables g) {
        g.removeSprite(this);
    }


    /**
     * return a point out side of any interfaces.Collidable.
     *
     * @param point the point
     * @return the point.
     */
    public Point makeOutside(Point point) {
        Collidable inside = this.gameEv.isInRectangle(point);
        if (inside != null) {
            point = pointOutsideRectangle(inside.getCollisionRectangle(), point);
        }
        return point;
    }

    /**
     * Returns the point that out of the geometry.geometry.Rectangle that is near point p.
     *
     * @param rect the geometry.geometry.Rectangle.
     * @param p    the point.
     * @return the point outside the geometry.geometry.Rectangle.
     */
    public Point pointOutsideRectangle(Rectangle rect, Point p) {
        List<Line> linesToSide = new ArrayList<>();
        linesToSide.add(new Line(p, new Point(p.getX(), rect.getCorner(0).getY())));
        linesToSide.add(new Line(p, new Point(rect.getCorner(0).getX(), p.getY())));
        linesToSide.add(new Line(p, new Point(p.getX(), rect.getCorner(2).getY())));
        linesToSide.add(new Line(p, new Point(rect.getCorner(2).getX(), p.getY())));
        Line minLine = new Line(0, 0, Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        for (Line line : linesToSide) {
            if (line.start().distance(line.end()) < minLine.start().distance(minLine.end())) {
                minLine = line;
            }
        }
        return minLine.end();
    }
}
