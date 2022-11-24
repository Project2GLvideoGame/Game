package engine.event;

import engine.GameObject;


public class EnnemisCollisionEvent implements Event {

    final GameObject gameObject;
    final double newDirection;

    public EnnemisCollisionEvent(GameObject gameObject ,double newDirection) {
        this.gameObject = gameObject;
        this.newDirection = newDirection;        
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    public double getNewDirection(){
        return newDirection;
    }
}

