package engine.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.graphic.Displayable;
import engine.input.State;

public class StateEvent implements Event {

    State newState;
    Displayable[] displayables;

    public StateEvent(State newState, Displayable... displayables) {
        this.newState = newState;
        this.displayables = displayables;
    }

    public State getNewState() {
        return newState;
    }

    public Displayable[] getDisplayables(){
        return this.displayables;
    }


}
