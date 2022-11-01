package game.Input.State;

import game.Input.InputEngine;

public class GameState implements State {

    @Override
    public void up(InputEngine input) {
        
    }

    @Override
    public void right(InputEngine input) {
    }

    @Override
    public void down(InputEngine input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void left(InputEngine input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause(InputEngine input) {
        input.changeState(new MainMenuState());
        
    }

    
}
