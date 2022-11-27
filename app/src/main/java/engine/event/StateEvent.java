package engine.event;

import engine.input.State;

public class StateEvent implements Event {

    State newState;

    public StateEvent(State newState) {
        this.newState = newState;
    }

    public State getNewState() {
        return newState;
    }
}
