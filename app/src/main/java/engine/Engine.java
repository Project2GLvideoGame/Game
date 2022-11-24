package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.event.Event;
import engine.event.EventsManager;

public abstract class Engine {

    protected EventsManager eventsManager;
    Map<Class<? extends Event>,List<? extends Event>> events = new HashMap<>();
    
    public Engine(EventsManager eventsManager) {
        this.eventsManager = eventsManager;
    }

    protected <T extends Event> void submit(T event){
        eventsManager.submit(event);
    }


    public <T extends Event> void notifyEvent(T event){
        List<T> l = (List<T>)events.get(event.getClass());
        if(l==null){
            l = new ArrayList<>();
            l.add(event);
            events.put(event.getClass(),l);
        }else{
            l.add(event);
        }
    }

    protected <T extends Event> List<T> getEvents(Class<T> class1){
        return (List<T>)events.get(class1);
    }
    
}
