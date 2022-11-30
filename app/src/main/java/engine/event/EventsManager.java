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

    public <T extends Event> void unsubscribe(Engine subscriber, Class<T> eventType){
        List<Engine> l = subscriptions.get(eventType) ;
        if(l==null) return;
        l.remove(subscriber);
    }

    public <T extends Event> void submit(T event){
        List<Engine> subscribers = subscriptions.get(event.getClass());
        if(subscribers==null) return;
        for (Engine engine : subscribers) {
            engine.notifyEvent(event);
        }
    }

    
}
