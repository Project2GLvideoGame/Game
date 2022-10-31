package Input.State;

import Input.InputEngine;

public class GameState implements State {

    @Override
    public void up(InputEngine input) {
        // TODO Auto-generated method stub
        System.out.println("UP");
        
    }

    @Override
    public void right(InputEngine input) {
        // TODO Auto-generated method stub
        System.out.println("DOWN");

        
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
        // TODO Auto-generated method stub
        System.out.println("PAUSE");

        
    }
    
}
