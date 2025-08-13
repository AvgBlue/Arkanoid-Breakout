package geometry;


/**
 * The type geometry.Velocity.
 *

 */
public class Velocity {
    /**
     * The geometry.geometry.Point.
     */
    private double dx;
    private double dy;


    /**
     * the constructor of geometry.Velocity, with dx and dy Components.
     *
     * @param dx the dx Component of the point
     * @param dy the dy Component of the point
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the constructor of geometry.Velocity.
     * with between two points
     *
     * @param start the start point of the vector that the velocity represent
     * @param end   the start point of the vector that the velocity represent
     */
    public Velocity(Point start, Point end) {
        this.dx = end.getX() - start.getX();
        this.dy = end.getY() - start.getY();
    }

    /**
     * return the point the geometry.Velocity represent.
     *
     * @return return the point
     */
    public Point point() {
        return new Point(this.dx, this.dy);
    }

    /**
     * return a geometry.Velocity object created by the speed and the angle.
     *
     * @param angle the angel of the vector
     * @param speed the speed of the vector
     * @return return the point
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return (new Point(0, 0)).distance(new Point(dx, dy));
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan(this.dy / this.dx));
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(double speed) {
        Velocity velocity = fromAngleAndSpeed(getAngle(), speed);
        this.dx = velocity.getDx();
        this.dy = velocity.getDy();
    }

    /**
     * Reflect dx.
     */
    public void reflectDx() {
        this.dx = -this.dx;
    }

    /**
     * Reflect dy.
     */
    public void reflectDy() {
        this.dy = -this.dy;
    }


    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p of the position
     * @return return the new point position.
     */
    public Point applyToPoint(Point p) {
        double dx = (int) (this.dx * 1000) / 1000.0;
        double dy = (int) (this.dy * 1000) / 1000.0;
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * if the velocity move up.
     *
     * @return the boolean
     */
    public boolean isGoUp() {
        return this.dy < 0;
    }

    /**
     * if the velocity move down.
     *
     * @return the boolean
     */
    public boolean isGoDown() {
        return this.dy > 0;
    }

    /**
     * if the velocity move left.
     *
     * @return the boolean
     */
    public boolean isGoLeft() {
        return this.dx < 0;
    }

    /**
     * if the velocity move right.
     *
     * @return the boolean
     */
    public boolean isGoRight() {
        return this.dx > 0;
    }


}
