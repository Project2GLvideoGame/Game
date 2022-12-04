package engine.sound;


import junit.framework.TestCase;
import org.junit.Test;

import engine.event.EventsManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class SoundEngineTest {


     EventsManager eventsManager = new EventsManager();
     SoundEngine soundEngine = new SoundEngine(eventsManager);
     Soundable soundable = new Soundable(new Track("test","shoot.wav"));

    @Test
    public void testAddSoundableObject() {
       int size = soundEngine.soundables.size();
       soundEngine.addSoundableObject(soundable);
       assertEquals(size+1,soundEngine.soundables.size());
       soundEngine.addSoundableObject(soundable);
       assertEquals(size+2,soundEngine.soundables.size());
       soundEngine.addSoundableObject(soundable);
       assertNotEquals(size, soundEngine.soundables.size());



    }
    @Test
    public void testRemoveSoundableObject() {
        int size = soundEngine.soundables.size();
        // on ne peut pas supprimer quand c'est vide
        soundEngine.removeSoundableObject(soundable);
        assertEquals(0,soundEngine.soundables.size());
        assertNotEquals(-1, soundEngine.soundables.size());    
        soundEngine.addSoundableObject(soundable);
        soundEngine.removeSoundableObject(soundable);
        assertEquals(size,soundEngine.soundables.size());
    }
}