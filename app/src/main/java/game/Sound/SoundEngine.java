package game.Sound;

import game.Engine;
import game.Event.EventsManager;

import java.util.ArrayList;
import java.util.List;

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
