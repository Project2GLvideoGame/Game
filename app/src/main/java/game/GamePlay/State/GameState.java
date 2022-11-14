package game.gameplay.State;

import game.Kernel;
import game.input.State;

public class GameState implements State {

    @Override
    public void up(Kernel kernel) {
        kernel.movePlayer(0);
    }

    @Override
    public void right(Kernel kernel) {
        kernel.movePlayer(270);
    }

    @Override
    public void down(Kernel kernel) {
        kernel.movePlayer(180);
    }

    @Override
    public void left(Kernel kernel) {
        kernel.movePlayer(90);
    }

    @Override
    public void pause(Kernel kernel) {

    }
    @Override
    public void escape(Kernel kernel) {

    }

    
}
