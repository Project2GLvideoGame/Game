package engine.event;

import engine.GameObject;
import engine.sound.Track;

public class SoundEvent implements Event{
    GameObject gameObject;
    String trackName;

    public SoundEvent(GameObject gameObject, String trackName) {
        this.gameObject = gameObject;
        this.trackName = trackName;
    }

    public String getTrackName() {
        return trackName;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
