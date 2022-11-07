package game.Input.State;

import game.Input.InputEngine;

public class GameState implements State {

    @Override
    public void up(InputEngine input) {
        input.getKernel().movePlayer(0);
    }

    @Override
    public void right(InputEngine input) {
        input.getKernel().movePlayer(270);
    }

    @Override
    public void down(InputEngine input) {
        input.getKernel().movePlayer(180);
    }

    @Override
    public void left(InputEngine input) {
        input.getKernel().movePlayer(90);
    }

    @Override
    public void pause(InputEngine input) {
        input.changeState(new MainMenuState());
        
    }

    
}
