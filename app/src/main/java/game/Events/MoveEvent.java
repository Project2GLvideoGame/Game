package game.Events;

import game.GameObject;
import game.Physic.Coordinate;


public class MoveEvent implements game.Events.Event{

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
