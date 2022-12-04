package game.ai;

import engine.event.Event;

/**
 * AI abstract class for intelligent algotithm
 */
public abstract class AI {


    /**
     * Apply event on an Intelligent object
     * @param event Event to check to update an Intelligent Object
     */
    abstract void apply(Event event);
    

    /**
     * Apply Intelligent on an Intelligent object if collision
     * @param intelligent Object to check collision with
     * @param currentTime End time
     * @param previousTime Start time
     */
    abstract void apply(Intelligent intelligent, long currentTime, long previousTime);


}
