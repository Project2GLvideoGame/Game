package game.state;

import game.Game;
import engine.input.State;

/**
 * À créer par le concepteur du gameplay dans la partie gameplay pour autant d'états de jeu qu'il existe.
 */
public class MainMenuState implements State {

    @Override
    public void up(Game game) {

    }

    @Override
    public void right(Game game) {

    }

    @Override
    public void down(Game game) {

    }

    @Override
    public void left(Game game) {

    }

    @Override
    public void escape(Game game) {

    }

    @Override
    public void p(Game game) {
        game.changeState(new GameState());
        System.out.println("Main ");


    }
}
