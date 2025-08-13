package gamesetting;

import geometry.Point;
import interfaces.Collidable;
import geometry.Line;
import java.util.ArrayList;


/**
 * The type gamesetting.Game environment.
 *

 */
public class GameEnvironment {

    private ArrayList<Collidable> arrayList;

    /**
     * the constructor of GameEnvironmen.
     */
    public GameEnvironment() {
        this.arrayList = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the interfaces.
     */
    public void addCollidable(Collidable c) {
        this.arrayList.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.arrayList.remove(c);
    }


    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables.
     * in this collection, return null. Else, return the information.
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the ball
     * @return the closest collision or null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo infoChan = null;
        ArrayList<Collidable> runOn = new ArrayList<>(this.arrayList);
        for (Collidable collidable : runOn) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint == null) {
                continue;
            }
            if (infoChan == null) {
                infoChan = new CollisionInfo(collisionPoint, collidable);
            } else if (trajectory.start().distance(collidable.getCollisionRectangle().getMiddlePoint())
                    < trajectory.start().distance(
                    infoChan.collisionObject().getCollisionRectangle().getMiddlePoint())) {
                infoChan = new CollisionInfo(collisionPoint, collidable);
            }
        }
        return infoChan;
    }

    /**
     * Returns the interfaces.Collidable where the point is.
     * or null if a point is not in any interfaces.Collidable
     *
     * @param p the p
     * @return the collidable
     */
    public Collidable isInRectangle(Point p) {
        for (Collidable collidable : this.arrayList) {
            if (collidable.getCollisionRectangle().isInsideRectangle(p)) {
                return collidable;
            }
        }
        return null;
    }

}
