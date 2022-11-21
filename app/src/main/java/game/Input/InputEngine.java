package game.Input;

import java.awt.event.*;

import game.Kernel;
import game.Gameplay.State.GameState;

public class InputEngine implements KeyListener{

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

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> currentState.up(kernel);
            case KeyEvent.VK_RIGHT -> currentState.right(kernel);
            case KeyEvent.VK_DOWN -> currentState.down(kernel);
            case KeyEvent.VK_LEFT -> currentState.left(kernel);
            case KeyEvent.VK_P -> currentState.pause(kernel);
            case KeyEvent.VK_ESCAPE -> currentState.escape(kernel);
            default -> System.out.println(e.getKeyCode());
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
