package game.entity;

import engine.GameObject;
import engine.graphic.Displayable;
import engine.physicsAAA.Physic;

public class PlayerShoot extends GameObject{
    private static int shootSize = 20;
    public PlayerShoot(int x, int y){
        super(new Physic(x, y, shootSize, shootSize),
            new Displayable(x, y, shootSize, shootSize, 10, "/player/shoot/playerShoot1.png", "/player/shoot/playerShoot2.png", "/player/shoot/playerShoot3.png", "/player/shoot/playerShoot4.png")
            );
        getComponent(Physic.class).setSpeed(4);
    }
}
