package engine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.Engine;
import game.ai.AIEngine;
import game.ai.Intelligent;

public class EventsManager {

    Map<Class<? extends Event>,List<Engine>> subscriptions = new HashMap<>();


    /**
     * une fois appelé, le moteur subscriber, recevra les event du type eventType
     * dans une liste qui lui est propre.
     * @param subscriber
     * @param eventType
     */
    public <T extends Event> void subscribe(Engine subscriber, Class<T> eventType){
        List<Engine> subscribers = subscriptions.get(eventType) ;
        if(subscribers==null){
            subscribers = new ArrayList<>();
            subscribers.add(subscriber);
            subscriptions.put(eventType,subscribers);
        }else{
            subscribers.add(subscriber);
        }
    }

    /**
     * une fois appelé, le moteur subscriber, ne recevra plus les event du type eventType
     * @param subscriber
     * @param eventType
     */
    public <T extends Event> void unsubscribe(Engine subscriber, Class<T> eventType){
        List<Engine> l = subscriptions.get(eventType) ;
        if(l==null) return;
        l.remove(subscriber);
    }

    /**
     * permet de soumettre un evenement,
     * tous les moteurs abonnés au type de l'evenement event
     * recevront cet event dans leur liste dédiée.
     * @param event
     */
    public <T extends Event> void submit(T event){
        List<Engine> subscribers = subscriptions.get(event.getClass());
        if(subscribers==null) return;
        for (Engine engine : subscribers) {
            engine.notifyEvent(event);
        }
    }

    
}
