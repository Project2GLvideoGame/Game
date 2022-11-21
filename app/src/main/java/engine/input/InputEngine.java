package engine.input;

import java.awt.event.*;

import engine.Kernel;
import game.state.GameState;

public class InputEngine implements KeyListener{

    private static State currentState = new GameState();
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
        System.out.println(getCurrentState());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentState.up(kernel);
                break;
            case KeyEvent.VK_RIGHT:
                currentState.right(kernel);
                break;
            case KeyEvent.VK_DOWN:
                currentState.down(kernel);
                break;
            case KeyEvent.VK_LEFT:
                currentState.left(kernel);
                break;
            case KeyEvent.VK_P:
                currentState.pause(kernel);
                break;
            case KeyEvent.VK_ESCAPE:
                currentState.escape(kernel);
                break;
            default:
                System.out.println(e.getKeyCode());
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

    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
