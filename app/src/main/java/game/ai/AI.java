package game.ai;

import engine.event.Event;

public abstract class AI {

    abstract void apply(Event event);
    
    abstract void apply(Intelligent intelligent, long currentTime, long previousTime);


}
