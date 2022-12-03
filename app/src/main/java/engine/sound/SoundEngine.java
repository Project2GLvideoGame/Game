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
    public void handleCollisionEvents(){
        List<CollisionEvent> collisionEvents = getEvents(CollisionEvent.class);
        if (collisionEvents == null) return;
        for (CollisionEvent event : collisionEvents) {
            if( event.getGameObject().getComponent(Soundable.class) == null || collisionEvents.size() == 0)
                continue;
            if(event.getGameObject().getComponent(Soundable.class).getClips().get(CollisionEvent.class.getName())==null)
                continue;
            System.out.println(event.getGameObject().getClass().getName());
            event.getGameObject().getComponent(Soundable.class).playSoundEffect(CollisionEvent.class.getName());

        }
        collisionEvents.clear();

    }
    public void handleDestroyEvents(){
        List<DestroyEvent> destroyEvents = getEvents(DestroyEvent.class);
        if (destroyEvents == null) return;
        for (DestroyEvent event : destroyEvents) {
            if( event.getGameObject().getComponent(Soundable.class) == null || destroyEvents.size() == 0)
                continue;
            if(event.getGameObject().getComponent(Soundable.class).getClips().get(DestroyEvent.class.getName())==null)
                continue;
            System.out.println(event.getGameObject().getClass().getName());
            event.getGameObject().getComponent(Soundable.class).playSoundEffect(DestroyEvent.class.getName());

        }
        destroyEvents.clear();

    }

    public void handleDeadEnemyEvents(){
        List<DeadEnemyEvent> deadEnemyEvents = getEvents(DeadEnemyEvent.class);
        if (deadEnemyEvents == null) return;
        for (DeadEnemyEvent event : deadEnemyEvents) {
            if( event.getGameObject().getComponent(Soundable.class) == null || deadEnemyEvents.size() == 0)
                continue;
            if(event.getGameObject().getComponent(Soundable.class).getClips().get(DeadEnemyEvent.class.getName())==null)
                continue;
            System.out.println(event.getGameObject().getClass().getName());
            event.getGameObject().getComponent(Soundable.class).playSoundEffect(DeadEnemyEvent.class.getName());

        }
        deadEnemyEvents.clear();

    }

    public void handleShootEvents(){
        List<ShootEvent> shootEvents = getEvents(ShootEvent.class);
        if (shootEvents == null) return;
        for (ShootEvent event : shootEvents) {
            if( event.getGameObject().getComponent(Soundable.class) == null || shootEvents.size() == 0)
                continue;
            if(event.getGameObject().getComponent(Soundable.class).getClips().get(ShootEvent.class.getName())==null)
                continue;
            System.out.println(event.getGameObject().getClass().getName());
            event.getGameObject().getComponent(Soundable.class).playSoundEffect(ShootEvent.class.getName());

        }
        shootEvents.clear();

    }



    public void stopAllmusic(){
        for (Soundable soundable : soundables) {
            soundable.stopAllMusic();
            
        }
    }

    public void update(){
        handleCollisionEvents();
        handleDestroyEvents();
        handleDeadEnemyEvents();
        handleShootEvents();

    }

}
