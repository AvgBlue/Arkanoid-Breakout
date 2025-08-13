package geometry;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.List;

/**
 * The type Line.
 *

 */
public class Line implements Sprite {
    private static final double EPSILON = 0.00001;
    private double x1;
    private double y1;
    private double y2;
    private double x2;
    private Point start;
    private Point end;
    //if the line is not a function the linear flag is true
    private boolean linearFlag;
    private double slope;
    private double intercept;


    /**
     * the constructor of geometry.geometry.Line with two points.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
        //set the slope,intercept and linearFlag component.
        setLinear();
    }

    /**
     * the constructor of geometry.geometry.Line with two points.
     *
     * @param x1 the x Component of the first point
     * @param y1 the y Component of the first point
     * @param x2 the y Component of the second point
     * @param y2 the y Component of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        //set the slope,intercept and linearFlag component.
        setLinear();
    }

    /**
     * set the Linear function the line represent.
     * if the function is x=constant the linearFlag is raised.
     */
    private void setLinear() {
        this.linearFlag = doubleEquals(this.x1, this.x2);
        if (!linearFlag) {
            this.slope = (this.y2 - this.y1) / (this.x2 - this.x1);
            this.intercept = this.y1 - this.slope * this.x1;
        }
    }

    /**
     * return the length of the line.
     *
     * @return the length
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * return the middle point of a line.
     *
     * @return the middle point.
     */
    public Point middle() {
        return new Point((this.x1 + this.x2) / 2, (this.y1 + this.y2) / 2);
    }

    /**
     * return the component start geometry.geometry.Point of the line.
     *
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * return the component end geometry.geometry.Point of the line.
     *
     * @return the end point.
     */
    public Point end() {
        return this.end;
    }


    /**
     * returns true if the lines intersect, false otherwise.
     *
     * @param other the other line we compare to
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        //if the lines have an intersection point the line intersect.
        if (intersectionWith(other) != null) {
            return true;
        }
        //If the two lines are vertical then check their x.
        if (this.linearFlag && other.getFlag() && this.x1 != other.start().getX()) {
            boolean isSelfTemp = this.isInDomain(other.start) || this.isInDomain(other.end);
            boolean isOtherTemp = other.isInDomain(this.start) || other.isInDomain(this.end);
            //Checks if the lines contain each other.
            return isSelfTemp || isOtherTemp;
        }
        //Checks if the lines contain each other.
        boolean result = this.isOnLinear(other.start) && this.isOnLinear(other.end)
                && (this.isInDomain(other.start) || this.isInDomain(other.end));
        result = result || (other.isOnLinear(this.start) && other.isOnLinear(this.end)
                && (other.isInDomain(this.start) || other.isInDomain(this.end)));
        return result;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other the other line we compare to
     * @return the intersection point or null.
     */
    public Point intersectionWith(Line other) {
        //Check with the lines are vertical
        if (this.linearFlag && other.getFlag()) {
            return null;
        }
        //Checks if any of the borrowers are vertical, and defines a possible intersection point
        Point intersecPoint = null;
        if (this.linearFlag) {
            //Calculates the intersection points
            intersecPoint = new Point(this.x1, other.getSlope() * this.x1 + other.getIntercept());
        } else if (other.getFlag()) {
            //Calculates the intersection points
            intersecPoint = new Point(other.start().getX(), this.slope * other.start().getX() + this.intercept);
        } else if (doubleEquals(this.slope, other.getSlope())) {
            //If the slope is equal then there is no point of intersection
            return null;
        } else {
            //Calculates the intersection points
            double newPointXTemp = (other.getIntercept() - this.intercept) / (this.slope - other.getSlope());
            double newPointYTemp = this.slope * newPointXTemp + this.intercept;
            intersecPoint = new Point(newPointXTemp, newPointYTemp);
        }


        //Returns with the points are in the domain created the two lines
        if (isInDomain(intersecPoint) && other.isInDomain(intersecPoint)) {
            return intersecPoint;
        }
        return null;
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other the other line we compare to
     * @return true or false.
     */
    public boolean equals(Line other) {
        boolean isStart = doubleEquals(this.start.getX(), other.start().getX())
                && doubleEquals(this.start.getY(), other.start().getY());
        boolean isEnd = doubleEquals(this.end.getX(), other.end().getX())
                && doubleEquals(this.end.getY(), other.end().getY());
        if (isStart && isEnd) {
            return true;
        }
        isStart = doubleEquals(this.start.getX(), other.end().getX())
                && doubleEquals(this.start.getY(), other.end().getY());
        isEnd = doubleEquals(this.end.getX(), other.start().getX())
                && doubleEquals(this.end.getY(), other.start().getY());
        return isStart && isEnd;
    }

    /**
     * return the slope component.
     *
     * @return the slope component
     */
    public Double getSlope() {
        return this.slope;
    }

    /**
     * return the intercept component.
     *
     * @return the intercept component
     */
    public double getIntercept() {
        return this.intercept;
    }

    /**
     * return the linearFlag component.
     *
     * @return the linearFlag component
     */
    public boolean getFlag() {
        return this.linearFlag;
    }

    /**
     * true if the point is in the domain created my the line,false otherwise.
     *
     * @param p3 the point we compare to
     * @return ture or false
     */
    public boolean isInDomain(Point p3) {

        boolean isX = (doubleEqualsOrLess(this.x1, p3.getX()) && doubleEqualsOrLess(p3.getX(), this.x2)
                || doubleEqualsOrLess(this.x2, p3.getX()) && doubleEqualsOrLess(p3.getX(), this.x1));
        boolean isY = (doubleEqualsOrLess(this.y1, p3.getY()) && doubleEqualsOrLess(p3.getY(), this.y2)
                || doubleEqualsOrLess(this.y2, p3.getY()) && doubleEqualsOrLess(p3.getY(), this.y1));
        return isX && isY;
    }


    /**
     * true if the point is on the linear function created my the line,false otherwise.
     *
     * @param p3 the point we compare to
     * @return ture or false
     */
    public boolean isOnLinear(Point p3) {
        if (linearFlag) {
            return doubleEquals(p3.getX(), this.x1);
        }
        return doubleEquals(this.slope * p3.getX() + this.intercept, p3.getY());
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
     * compers two double up to the epsilon.
     *
     * @param a the first number
     * @param b the second number
     * @return the result ture or false
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /**
     * impluman <= for double with doubleEquals.
     *
     * @param a the first number
     * @param b the second number
     * @return true or false
     */
    public static boolean doubleEqualsOrLess(double a, double b) {
        return doubleEquals(a, b) || a < b;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the geometry.geometry.Rectangle
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> collisionPointList = rect.intersectionPoints(this);
        if (!collisionPointList.isEmpty()) {
            Point collisionPoint = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
            for (Point point : collisionPointList) {
                if (point.distance(this.start()) < collisionPoint.distance(this.start())) {
                    collisionPoint = point;
                }
            }
            if (!collisionPoint.equals(new Point(Double.MAX_VALUE, Double.MAX_VALUE))) {
                return collisionPoint;
            }
        }
        return null;
    }

    /**
     * return a new line where the start of this line is the end of the new line and the other way.
     *
     * @return the revers line
     */
    public Line reverseLine() {
        return new Line(this.end(), this.start());
    }

    /**
     * draw the line.
     *
     * @param d the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int x1 = round(this.start().getX());
        int y1 = round(this.start().getY());
        int x2 = round(this.end().getX());
        int y2 = round(this.end().getY());
        d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void timePassed() {
    }

    /**
     * return true if the point is on the line or false if not.
     *
     * @param p the point
     * @return true or false
     */
    public boolean isOnLine(Point p) {
        return this.isOnLinear(p) && this.isInDomain(p);
    }


}
