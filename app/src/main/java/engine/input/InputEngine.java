package engine.input;

import java.awt.event.*;

import game.Game;
import game.state.GameState;

public class InputEngine implements KeyListener{

    private State currentState = new GameState();
    private Game game;

    public void changeState(State state){
        this.currentState = state;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){return game;}

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentState.up(game);
                break;
            case KeyEvent.VK_RIGHT:
                currentState.right(game);
                break;
            case KeyEvent.VK_DOWN:
                currentState.down(game);
                break;
            case KeyEvent.VK_LEFT:
                currentState.left(game);
                break;
            case KeyEvent.VK_P:
                currentState.pause(game);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escape(game);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                game.player.setSpeed(0);
                break;
            case KeyEvent.VK_RIGHT:
                game.player.setSpeed(0);
                break;
            case KeyEvent.VK_DOWN:
                game.player.setSpeed(0);
                break;
            case KeyEvent.VK_LEFT:
                game.player.setSpeed(0);
                break;
            case KeyEvent.VK_P:
                currentState.pause(game);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escape(game);
                break;
            default:
                break;
        }

    }

    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
