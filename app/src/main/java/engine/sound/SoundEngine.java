package engine.sound;

import java.util.ArrayList;
import java.util.List;

import engine.Engine;
import engine.event.*;

public class SoundEngine extends Engine{
    
    public List<Soundable> soundables = new ArrayList<>();
    
    /**
     * Constructeur de la classe SoundEngine
     * @param eventsManager
     * Recevra la liste des évènements auquels il est abonné
     */
    public SoundEngine(EventsManager eventsManager) {
        super(eventsManager);
    }
    /**
     * Ajouter un soundable à la liste sound
     * @param sound
     */
    public void addSoundableObject(Soundable sound){
        soundables.add(sound);
    }
    /** Supprimer un soundable de la liste sound
     * @param sound
     */
    public void removeSoundableObject(Soundable sound){
        soundables.remove(sound);
    }

    public void handleSoundEvent(){
        List<SoundEvent> soundEvents = getEvents(SoundEvent.class);
        if (soundEvents == null) return;
        for (int i = 0; i < soundEvents.size(); i++) {
            SoundEvent event = soundEvents.get(i);
            if (event.getGameObject().getComponent(Soundable.class) == null )
                continue;
            if (event.getGameObject().getComponent(Soundable.class).getClips().get(event.getTrackName()) == null)
                continue;
            event.getGameObject().getComponent(Soundable.class).playSoundEffect(event.getTrackName());
        }
        soundEvents.clear();

    }

    /**
     * Arreter la musique de tous les objets
     */
    public void stopAllmusic(){
        for (Soundable soundable : soundables) {
            soundable.stopAllMusic();
        }
    }
    /**
     * Traite les evenements soundEvent
     */
    public void update(){
        handleSoundEvent();
    }




    
}
