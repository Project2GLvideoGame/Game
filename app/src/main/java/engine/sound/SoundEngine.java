package engine.sound;

import java.util.ArrayList;
import java.util.List;

import engine.Engine;
import engine.event.*;

public class SoundEngine extends Engine{
    
    public List<Soundable> soundables = new ArrayList<>();

    public SoundEngine(EventsManager eventsManager) {
        super(eventsManager);
    }

    public void addSoundableObject(Soundable sound){
        soundables.add(sound);
    }

    public void removeSoundableObject(Soundable sound){
        soundables.remove(sound);
    }

    public void handleSoundEvent(){
        List<SoundEvent> soundEvents = getEvents(SoundEvent.class);
        if (soundEvents == null) return;
        for (SoundEvent event : soundEvents) {
            if (event.getGameObject().getComponent(Soundable.class) == null )
                continue;
            if (event.getGameObject().getComponent(Soundable.class).getClips().get(event.getTrackName()) == null)
                continue;
            event.getGameObject().getComponent(Soundable.class).playSoundEffect(event.getTrackName());
        }
        soundEvents.clear();

    }


    public void stopAllmusic(){
        for (Soundable soundable : soundables) {
            soundable.stopAllMusic();
        }
    }

    public void update(){
        handleSoundEvent();
    }




    
}
