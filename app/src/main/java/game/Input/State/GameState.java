package game.Input.State;

import game.Graphic.Displayable;
import game.Input.InputEngine;

public class GameState implements State {

    @Override
    public void up(InputEngine input) {
        input.getKernel().movePlayer(0);
        input.getKernel().getGraphicEngine().rotate(input.getKernel().getPlayer().getComponent(Displayable.class), 270);
    }

    @Override
    public void right(InputEngine input) {
        input.getKernel().movePlayer(270);
        input.getKernel().getGraphicEngine().rotate(input.getKernel().getPlayer().getComponent(Displayable.class), 0);
    }

    @Override
    public void down(InputEngine input) {
        input.getKernel().movePlayer(180);
        input.getKernel().getGraphicEngine().rotate(input.getKernel().getPlayer().getComponent(Displayable.class), 90);
    }

    @Override
    public void left(InputEngine input) {
        input.getKernel().movePlayer(90);
        input.getKernel().getGraphicEngine().rotate(input.getKernel().getPlayer().getComponent(Displayable.class), 180);
    }

    @Override
    public void pause(InputEngine input) {
        input.changeState(new MainMenuState());
        
    }

    
}
