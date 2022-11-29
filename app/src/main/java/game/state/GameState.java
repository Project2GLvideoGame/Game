package game.state;

import engine.Kernel;
import engine.graphic.Displayable;
import engine.input.State;
import game.Game;
import game.entity.PlayerShoot;

public class GameState implements State {

    @Override
    public void upPressed(Game game) {
    }
    
    @Override
    public void upReleased(Game game) {
        Displayable playerGraphic = game.player.getComponent(Displayable.class);
        Kernel kernel = Kernel.getInstance();
        PlayerShoot ps = new PlayerShoot(playerGraphic.getX()+playerGraphic.getWidth()/2, playerGraphic.getY()-100);
        kernel.addGameObject(ps);
        //System.out.println("release");
    }

    @Override
    public void downPressed(Game game) {

    }

    public void downReleased(Game game) {
    }

    @Override
    public void rightPressed(Game game) {
        //System.out.println("right");
        game.player.setSpeed(4);
        game.player.setDirection(270);
    }

    @Override
    public void rightReleased(Game game) {
        game.player.setSpeed(0);
    }

    @Override
    public void leftPressed(Game game) {
        game.player.setSpeed(4);
        game.player.setDirection(90);
    }

    @Override
    public void leftReleased(Game game) {
        game.player.setSpeed(0);
    }

    @Override
    public void pPressed(Game game) {
        //System.out.println("pause");
        game.changeState(new MainMenuState());
    }

    @Override
    public void pReleased(Game game) {

    }

    @Override
    public void escapePressed(Game game) {

    }

    @Override
    public void escapeReleased(Game game) {

    }
}
