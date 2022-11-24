package game;

import engine.GameObject;
import engine.Kernel;
import engine.graphic.Displayable;
import engine.physics.Physic;

public class Game {

    private final Kernel kernel;

    public Game() throws Exception {
        kernel = new Kernel();
    }

    public void launch() {
        GameObject player = new GameObject(new Physic(100, 100, 60, 64),
                new Displayable(100, 100, 64, 64, 6, "/player/pacman_run1.png", "/player/pacman_run2.png", "/player/pacman_run3.png", "/player/pacman_run4.png"));

        GameObject wall = new GameObject(new Physic(350, 300, 50, 150),
                new Displayable(350, 300, 50, 150, "/wall.jpg"));

        graphicEngine.addDisplayable(player.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(player.getComponent(Physic.class));
        player.getComponent(Physic.class).setSpeed(3);

        graphicEngine.addDisplayable(wall.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wall.getComponent(Physic.class));

        kernel.addGameObject(player);
        kernel.addGameObject(wall);
    }
}

