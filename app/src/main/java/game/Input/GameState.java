package game.Input;

import game.Graphic.Displayable;
import game.Kernel;

/**
 * À créer par le concepteur du gameplay dans la partie gameplay pour autant d'états de jeu qu'il existe.
 */
public class GameState implements State {

    @Override
    public void up(Kernel kernel) {
        kernel.movePlayer(0);
        kernel.getGraphicEngine().rotate(kernel.getPlayer().getComponent(Displayable.class), 270);
    }

    @Override
    public void right(Kernel kernel) {
        kernel.movePlayer(270);
        kernel.getGraphicEngine().rotate(kernel.getPlayer().getComponent(Displayable.class), 0);
    }

    @Override
    public void down(Kernel kernel) {
        kernel.movePlayer(180);
        kernel.getGraphicEngine().rotate(kernel.getPlayer().getComponent(Displayable.class), 90);
    }

    @Override
    public void left(Kernel kernel) {
        kernel.movePlayer(90);
        kernel.getGraphicEngine().rotate(kernel.getPlayer().getComponent(Displayable.class), 180);
    }

    @Override
    public void escape(Kernel kernel) {
        kernel.changeState(new MainMenuState());
    }

    @Override
    public void p(Kernel kernel) {
        // Mettre en pause le jeu
    }
}
