package engine.sound;

import java.util.ArrayList;
import java.util.List;

import engine.Engine;
import engine.event.EventsManager;

public class SoundEngine extends Engine{
    
    public List<Soundable> soundables = new ArrayList<>();

    public void play(){

    }

    public SoundEngine(EventsManager eventsManager) {
        super(eventsManager);
    }

    public void addSoundableObject(Soundable sound){
        soundables.add(sound);
    }

    public void removeSoundableObject(Soundable sound){
        soundables.remove(sound);
    }

}
