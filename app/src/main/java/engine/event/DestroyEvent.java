package engine.event;

import engine.GameObject;

public class DestroyEvent implements Event{

    GameObject gameObject;

    public DestroyEvent(GameObject gameObject) {
        this.gameObject =gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

}