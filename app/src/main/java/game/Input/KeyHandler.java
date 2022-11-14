package game.Input;

import java.awt.event.*;

import game.Kernel;
import game.Input.State.GameState;
import game.Input.State.State;

public class KeyHandler implements KeyListener{

    private State currentState = new GameState();
    private Kernel kernel;

    public void changeState(State state){
        this.currentState = state;
    }

    public void setKernel(Kernel kernel){
        this.kernel = kernel;
    }

    public Kernel getKernel(){return kernel;}

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_Z:
                currentState.up(this);
                break;
            case KeyEvent.VK_S:
                currentState.down(this);
                break;
            case KeyEvent.VK_Q:
                currentState.left(this);
                break;
            case KeyEvent.VK_D:
                currentState.right(this);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_Z:
            break;
            case KeyEvent.VK_S:
            break;
            case KeyEvent.VK_Q:
            break;
            case KeyEvent.VK_D:
            break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
