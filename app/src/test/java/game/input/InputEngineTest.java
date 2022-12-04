package game.input;

import static org.junit.Assert.*;

import engine.event.EventsManager;
import engine.event.StateEvent;
import engine.input.InputEngine;
import game.Game;
import game.state.GameOverState;
import game.state.GameState;
import org.junit.Test;

public class InputEngineTest {

    EventsManager eventsManager = new EventsManager();
    Game game = new Game(eventsManager);
    InputEngine inputEngine = new InputEngine(game, eventsManager);
    GameState gameState = new GameState();
    GameOverState gameOverState = new GameOverState();

    @Test
    public void testChangeState1() {
        inputEngine.changeState(gameState);
        assertEquals(gameState, inputEngine.getCurrentState());
        inputEngine.changeState(gameOverState);
        assertEquals(gameOverState, inputEngine.getCurrentState());
    }

    @Test
    public void testChangeState2() {
        inputEngine.changeState(gameOverState);
        assertNotEquals(gameState, inputEngine.getCurrentState());
        inputEngine.changeState(gameState);
        assertEquals(gameState, inputEngine.getCurrentState());
    }

    @Test
    public void testStateEvent1() {
        eventsManager.subscribe(inputEngine, StateEvent.class);
        eventsManager.submit(new StateEvent(gameOverState));
        inputEngine.update();
        assertEquals(gameOverState.getClass(), inputEngine.getCurrentState().getClass());
    }

    @Test
    public void testStateEvent2() {
        eventsManager.subscribe(inputEngine, StateEvent.class);
        eventsManager.submit(new StateEvent(gameState));
        inputEngine.update();
        assertNotEquals(gameOverState.getClass(), inputEngine.getCurrentState().getClass());
    }
}