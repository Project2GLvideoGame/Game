package engine.sound;

import java.util.ArrayList;
import java.util.List;

import engine.Engine;
import engine.event.EventsManager;

public class SoundEngine extends Engine {
    
    public List<Soundable> soundables = new ArrayList<>();

    public void play(){
        for( Soundable soundables : soundables){
            soundables.loop();
            System.out.println(this.soundables.size());
        }
    }

    public SoundEngine(EventsManager eventsManager) {
        super(eventsManager);
    }

    public void addSoundableObject(Soundable sound){
        soundables.add(sound);
    }
}
