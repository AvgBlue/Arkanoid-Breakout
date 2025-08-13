package gamesetting;

import geometry.Point;
import interfaces.Collidable;

/**
 * The type Collision info.
 *

 */
public class CollisionInfo {

    private Collidable collisionObject;

    private Point collisionPoint;


    /**
     * the constructor of GameEnvironmen.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
