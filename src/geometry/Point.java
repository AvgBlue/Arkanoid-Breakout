package geometry;


/**
 * The type Point.
 *

 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = 0.00001;

    /**
     * the constructor of geometry.geometry.Point.
     *
     * @param x the x Component of the point
     * @param y the y Component of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance between this point and the "other" point.
     *
     * @param other the point we calculate the distance to.
     * @return the distance .
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Compares the two points.
     *
     * @param other the point we compare to.
     * @return ture or false.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return doubleEquals(this.x, other.getX()) && doubleEquals(this.y, other.getY());
    }

    /**
     * return the x Component of the point.
     *
     * @return x x
     */
    public double getX() {
        return this.x;
    }

    /**
     * return the y Component of the point.
     *
     * @return y y
     */
    public double getY() {
        return this.y;
    }

    /**
     * compers two double up to the epsilon.
     *
     * @param a the first number
     * @param b the second number
     * @return the result ture or false
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }
}
