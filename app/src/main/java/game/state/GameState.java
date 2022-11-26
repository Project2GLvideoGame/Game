package game.state;

import engine.input.State;
import game.Game;

public class GameState implements State {

    @Override
    public void up(Game game) {
        game.player.shoot();
    }

    @Override
    public void right(Game game) {
        game.player.setSpeed(game.player.getSpeed());
        game.player.setDirection(270);
    }

    @Override
    public void down(Game game) {
        
    }

    @Override
    public void left(Game game) {
        game.player.setSpeed(game.player.getSpeed());
        game.player.setDirection(90);
    }

    @Override
    public void pause(Game game) {
        game.changeState(new MainMenuState());
    }

    @Override
    public void escape(Game game) {

    }

    
}
