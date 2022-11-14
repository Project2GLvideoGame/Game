package game.event;

import game.GameObject;
import game.physics.Coordinate;


public class MoveEvent implements game.event.Event{

    final GameObject GameObject;
    final Coordinate origin, destination;

    public MoveEvent(GameObject GameObject, Coordinate origin, Coordinate destination) {
        this.GameObject = GameObject;
        this.origin = origin;
        this.destination = destination;
    }

    public GameObject getGameObject() {
        return this.GameObject;
    }

    public Coordinate getDestination() {
        return this.destination;
    }

    public Coordinate getOrigin() {
        return this.origin;
    }


}
