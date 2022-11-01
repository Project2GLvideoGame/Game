package game.Input.State;

import game.Input.InputEngine;

public class MainMenuState implements State {

    @Override
    public void up(InputEngine input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void right(InputEngine input) {
        // TODO Auto-generated method stub
        
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
