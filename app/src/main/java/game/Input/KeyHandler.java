package game.Input;

import java.awt.event.*;

import game.Kernel;
import game.Input.State.GameState;
import game.Input.State.State;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    private State currentState = new GameState();
    private Kernel kernel;

    public void changeState(State state){
        this.currentState = state;
    }

    public Kernel getKernel(){return kernel;}

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_Z:
                upPressed = true;
            break;
            case KeyEvent.VK_S:
                downPressed = true;
            break;
            case KeyEvent.VK_Q:
                leftPressed = true;
            break;
            case KeyEvent.VK_D:
                rightPressed = true;
            break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_Z:
                upPressed = false;
            break;
            case KeyEvent.VK_S:
                downPressed = false;
            break;
            case KeyEvent.VK_Q:
                leftPressed = false;
            break;
            case KeyEvent.VK_D:
                rightPressed = false;
            break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
