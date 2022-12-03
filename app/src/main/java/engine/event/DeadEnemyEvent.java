package engine.event;

import engine.GameObject;

public class DeadEnemyEvent implements Event {
    GameObject gameObject;

    public DeadEnemyEvent(GameObject gameObject) {
        this.gameObject =gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
