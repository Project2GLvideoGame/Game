package game.Input.State;

import game.Input.KeyHandler;

public class GameState implements State {

    @Override
    public void up(KeyHandler input) {
        input.getKernel().movePlayer(0);
    }

    @Override
    public void right(KeyHandler input) {
        input.getKernel().movePlayer(270);
    }

    @Override
    public void down(KeyHandler input) {
        input.getKernel().movePlayer(180);
    }

    @Override
    public void left(KeyHandler input) {
        input.getKernel().movePlayer(90);
    }

    @Override
    public void pause(KeyHandler input) {
        //input.changeState(new MainMenuState()); 
    }

    
}
