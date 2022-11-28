package game.entity;

import engine.GameObject;
import engine.graphic.Displayable;
import engine.physics.Physic;
import engine.physics.collisionReaction.IgnoreReaction;

public class PlayerShoot extends GameObject{
    private static int graphicShootSize = 25;
    private static int physicShootSize = 1;
    public PlayerShoot(int x, int y){
        super(new Physic(x+graphicShootSize/2, y+graphicShootSize/2, physicShootSize, physicShootSize, new IgnoreReaction()),
            new Displayable(x, y, graphicShootSize, graphicShootSize, 10, "/player/shoot/playerShoot1.png", "/player/shoot/playerShoot2.png", "/player/shoot/playerShoot3.png", "/player/shoot/playerShoot4.png")
            );
        getComponent(Physic.class).setSpeed(4);
    }
}
