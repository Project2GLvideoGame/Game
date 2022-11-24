package gameplay;

import game.GameObject;
import game.Graphic.Displayable;
import game.Kernel;
import game.Physics.Physic;

import javax.imageio.ImageIO;

public class Gameplay {

    private final Kernel kernel;

    public Gameplay() throws Exception {
        kernel = new Kernel();
    }

    public static void main(String[] args) {
        //Gameplay work
        GameObject player = new GameObject(new Physic(100, 100, 60, 64),
                new Displayable(ImageIO.read(getClass().getResource("/pacman_idle.png")),
                        100, 100, 64, 64));
        GameObject wall = new GameObject(new Physic(350, 300, 50, 150),
                new Displayable(ImageIO.read(getClass().getResource("/wall.jpg")),
                        350, 300, 50, 150));

        //Player
        graphicEngine.addDisplayable(player.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(player.getComponent(Physic.class));
        player.getComponent(Physic.class).setSpeed(3);

        //Wall
        graphicEngine.addDisplayable(wall.getComponent(Displayable.class));
        physicalEngine.addPhysicalObject(wall.getComponent(Physic.class));

        gameObjects.add(player);
        gameObjects.add(wall);
    }
}

