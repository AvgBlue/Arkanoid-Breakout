package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The type geometry.geometry.Rectangle.
 *

 */
public class Rectangle {
    //I already created in the previous exercise a class called square
    //that has most of the things I need for a geometry.geometry.Rectangle
    //I will use him for the implementation

    private Square square;

    private Line[] sides;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper Left point .
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.square = new Square(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.sides = sides();
    }


    /**
     * Return a (possibly empty) List of intersection points.
     * with the specified line.
     *
     * @param line the line
     * @return the array list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        Line[] sides = this.sides();
        for (Line side : sides) {
            if (side.intersectionWith(line) != null) {
                intersectionPoints.add(new Point(side.intersectionWith(line).getX(),
                        side.intersectionWith(line).getY()));
            }
        }
        return intersectionPoints;
    }


    /**
     * return the width of the geometry.geometry.Rectangle.
     *
     * @return the width of the geometry.geometry.Rectangle
     */
    public double getWidth() {
        return this.square.end().getX() - this.square.start().getX();
    }

    /**
     * return the width of the geometry.geometry.Rectangle.
     *
     * @return the height of the geometry.geometry.Rectangle
     */
    public double getHeight() {
        return this.square.end().getY() - this.square.start().getY();
    }


    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.square.start();
    }


    /**
     * return the corners of the geometry.geometry.Rectangle where 0 is the top left and moving clockwise.
     *
     * @param num the number of the corner we want.
     * @return the point of the corner
     */
    public Point getCorner(int num) {
        return this.square.getCorner(num);
    }


    /**
     * set the sides of the geometry.geometry.Rectangle where 0 is the top side and moving clockwise.
     *
     * @return array of geometry.geometry.Line
     */
    public Line[] sides() {
        Line[] array = new Line[4];
        for (int i = 0; i < 4; i++) {
            array[i] = new Line(this.square.getCorner(i), this.square.getCorner((i + 1) % 4));
        }
        return array;
    }

    /**
     * return the lines that make the geometry.geometry.Rectangle where 0 is the top side and moving clockwise.
     *
     * @param num the number of the side we want.
     * @return line side
     */
    public Line getSide(int num) {
        return this.sides[num];
    }

    /**
     * return on what side of the geometry.geometry.Rectangle the point is.
     * can return an empty arraylist if the point is not on any side.
     *
     * @param point the point
     * @return the array list
     */
    public ArrayList<Line> pointOnSide(Point point) {
        ArrayList<Line> returnArray = new ArrayList<Line>();
        for (Line line : this.sides) {
            if (line.isOnLinear(point) && line.isInDomain(point)) {
                returnArray.add(line);
            }
        }
        return returnArray;
    }

    /**
     * return the number of the line given or null if it not a side in the rectangle.
     *
     * @param side the side
     * @return number of the side.
     */
    public Integer getSideNumber(Line side) {
        for (int i = 0; i < 4; i++) {
            if (side.equals(sides[i])) {
                return i;
            }
        }
        return null;
    }

    /**
     * Gets the corner number.
     *
     * @param point the point
     * @return the corner number
     */
    public Integer getCornerNumber(Point point) {
        for (int i = 0; i < 4; i++) {
            if (point.equals(this.getCorner(i))) {
                return i;
            }
        }
        return null;
    }

    /**
     * get the square that represent geometry.geometry.Rectangle.
     *
     * @return the square
     */
    public Square square() {
        return square;
    }

    /**
     * Gets middle point.
     *
     * @return the middle point
     */
    public Point getMiddlePoint() {
        return this.square.getLine().middle();
    }

    /**
     * Is inside rectangle boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isInsideRectangle(Point p) {
        boolean isX = this.getUpperLeft().getX() < p.getX() && p.getX() < this.getCorner(2).getX();
        boolean isY = this.getUpperLeft().getY() < p.getY() && p.getY() < this.getCorner(2).getY();
        return isX && isY;
    }
}
