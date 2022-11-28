package engine.input;

import java.awt.event.*;
import java.util.List;

import engine.Engine;
import engine.event.Event;
import engine.event.EventsManager;
import engine.event.StateEvent;
import game.Game;
import game.state.GameState;

public class InputEngine extends Engine<State> implements KeyListener {

    private State currentState = new GameState();
    private Game game;

    public InputEngine(Game game, EventsManager eventsManager) {
        super(eventsManager);
        this.game = game;
    }

    public void update() {
        List<StateEvent> stateEvents = getEvents(StateEvent.class);
        if(stateEvents != null) {
            for (StateEvent stateEvent : stateEvents) {
                changeState(stateEvent.getNewState());
            }
        }
    }

    private void changeState(State state) {
        this.currentState = state;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){return game;}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentState.upPressed(game);
                break;
            case KeyEvent.VK_RIGHT:
                currentState.rightPressed(game);
                break;
            case KeyEvent.VK_DOWN:
                currentState.downPressed(game);
                break;
            case KeyEvent.VK_LEFT:
                currentState.leftPressed(game);
                break;
            case KeyEvent.VK_P:
                currentState.pPressed(game);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escapePressed(game);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                currentState.upReleased(game);
                break;
            case KeyEvent.VK_RIGHT:
                currentState.rightReleased(game);
                break;
            case KeyEvent.VK_DOWN:
                currentState.downReleased(game);
                break;
            case KeyEvent.VK_LEFT:
                currentState.leftReleased(game);
                break;
            case KeyEvent.VK_P:
                currentState.pReleased(game);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escapeReleased(game);
                break;
            default:
                break;
        }
    }

}
