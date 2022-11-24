package game.state;

import engine.input.State;
import game.Game;

public class GameState implements State {

    @Override
    public void up(Game game) {
        game.player.setPlayerSpeed(game.player.getSpeed());
        game.player.setPlayerDirection(0);
    }

    @Override
    public void right(Game game) {
        game.player.setPlayerSpeed(game.player.getSpeed());
        game.player.setPlayerDirection(270);
    }

    @Override
    public void down(Game game) {
        game.player.setPlayerSpeed(game.player.getSpeed());
        game.player.setPlayerDirection(180);
    }

    @Override
    public void left(Game game) {
        game.player.setPlayerSpeed(game.player.getSpeed());
        game.player.setPlayerDirection(90);
    }

    @Override
    public void pause(Game game) {
        System.out.println("game");
        game.changeState(new MainMenuState());
    }

    @Override
    public void escape(Game game) {

    }

    
}
