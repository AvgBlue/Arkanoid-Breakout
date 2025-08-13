package geometry;

import java.awt.Color;

/**
 * The type geometry.geometry.Square.
 *

 */
public class Square {
    private Line diagonal;
    private Color color;
    private Point[] corners;

    /**
     * the constructor of square.
     * from the point (x1,x2) to the point (x2,y2).
     *
     * @param x1    the x1 Component of the first point
     * @param y1    the y1 Component of the first point
     * @param x2    the x2 Component of the second point
     * @param y2    the y2 Component of the second point
     * @param color the color of the square
     */
    public Square(double x1, double y1, double x2, double y2, Color color) {
        this(x1, y1, x2, y2);
        this.color = color;
    }

    /**
     * the constructor of square.
     * from the point start to the point end.
     *
     * @param start the first point
     * @param end   the second point
     * @param color the color of the square
     */
    public Square(Point start, Point end, Color color) {
        this(start, end);
        this.color = color;
    }

    /**
     * the constructor of square.
     * from the point (x1,x2) to the point (x2,y2).
     * and set the corners of the square.
     *
     * @param x1 the x1 Component of the first point
     * @param y1 the y1 Component of the first point
     * @param x2 the x2 Component of the second point
     * @param y2 the y2 Component of the second point
     */
    public Square(double x1, double y1, double x2, double y2) {
        this.diagonal = new Line(x1, y1, x2, y2);
        this.corners = corners();
    }

    /**
     * the constructor of square.
     * from the point start to the point end.
     *
     * @param start the first point
     * @param end   the second point
     */
    public Square(Point start, Point end) {
        this.diagonal = new Line(start, end);
        this.corners = corners();
    }

    /**
     * set the corners of the square where 0 is the top left and moving clockwise.
     *
     * @return array of points
     */
    private Point[] corners() {
        Point[] array = new Point[4];
        array[0] = this.diagonal.start();
        array[1] = new Point(this.diagonal.end().getX(), this.diagonal.start().getY());
        array[2] = this.diagonal.end();
        array[3] = new Point(this.diagonal.start().getX(), this.diagonal.end().getY());
        return array;
    }

    /**
     * return the corners of the where corner zero is the start of the diagonal and moving clockwise.
     *
     * @param num the number of the corner we want.
     * @return array of points
     */
    public Point getCorner(int num) {
        return corners[num];
    }

    /**
     * return the top left corner.
     *
     * @return the top left corner.
     */
    public Point start() {
        double x = Math.min(this.diagonal.start().getX(), this.diagonal.end().getX());
        double y = Math.min(this.diagonal.start().getY(), this.diagonal.end().getY());
        return new Point(x, y);
    }

    /**
     * return the bottom right corner.
     *
     * @return the bottom right corner.
     */
    public Point end() {
        double x = Math.max(this.diagonal.start().getX(), this.diagonal.end().getX());
        double y = Math.max(this.diagonal.start().getY(), this.diagonal.end().getY());
        return new Point(x, y);
    }

    /**
     * return diagonal of the square.
     *
     * @return the bottom left corner.
     */
    public Line getLine() {
        return this.diagonal;
    }

    /**
     * return color of the square.
     *
     * @return color of the square.
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Sets color of the square.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
