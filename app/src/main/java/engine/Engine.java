package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.event.CollisionEvent;
import engine.event.Event;
import engine.event.EventsManager;
import game.ai.Intelligent;

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
        //if(events.get(class1)!=null && events.get(class1).get(0).getClass()==CollisionEvent.class) System.out.println(events.get(class1).size()+"  "+events.get(class1).get(0).getClass());
        return (List<T>)events.get(class1);
    }

    // public void addComponent(Type component) {
    //     gameObjects.add(component);
    // }
    
    // public void addAssociatedComponent(GameObject gameObject){
    //     if(gameObject.getComponent(sample.getClass()) == null) return;
    //     else gameObjects.add(gameObject.getComponent(sample.getClass()));
    // }

    // public void removeAssociatedComponent(GameObject gameObject){
    //     gameObjects.remove(gameObject.getComponent(sample.getClass()));
    // }

    // public void addComponent(Type component) {
    //     gameObjects.add(component);
    // }
    
}
