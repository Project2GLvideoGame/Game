package engine.event;

import engine.GameObject;

public class ShootEvent implements Event{
    GameObject gameObject;

    public ShootEvent(GameObject gameObject) {
        this.gameObject =gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
