package engine.event;

import engine.GameObject;

public class PlayerShootEvent implements Event{

    private final GameObject gameObject;
    public PlayerShootEvent(GameObject go){
        this.gameObject = go;
    }

    public GameObject getGameObject(){return this.gameObject;}
}
