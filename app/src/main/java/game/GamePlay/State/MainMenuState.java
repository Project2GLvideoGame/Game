package game.Gameplay.State;

import game.Kernel;
import game.Input.State;

/**
 * À créer par le concepteur du gameplay dans la partie gameplay pour autant d'états de jeu qu'il existe.
 */
public class MainMenuState implements State {

    @Override
    public void up(Kernel kernel) {

    }

    @Override
    public void right(Kernel kernel) {

    }

    @Override
    public void down(Kernel kernel) {

    }

    @Override
    public void left(Kernel kernel) {

    }

    @Override
    public void escape(Kernel kernel) {

        kernel.changeState(new GameState());
    }

    @Override
    public void pause(Kernel kernel) {

    }
}
