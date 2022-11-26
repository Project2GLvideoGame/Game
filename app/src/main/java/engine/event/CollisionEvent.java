package engine.event;

import java.util.List;

import engine.GameObject;
import engine.physicsAAA.Collision;
import engine.physicsAAA.Coordinate;

public class CollisionEvent  implements Event{

    final GameObject gameObject;
    final  List<Collision> collisions;
    Coordinate lastCoordinate;

    public CollisionEvent(GameObject gameObject, List<Collision> collisions, Coordinate lastCoordinate) {
        this.gameObject = gameObject;
        this.collisions = collisions;
        this.lastCoordinate = lastCoordinate;
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public List<Collision> getCollision() {
        return this.collisions;
    }

    public Coordinate getLastCoordinate() {
        return this.lastCoordinate;
    }
}

