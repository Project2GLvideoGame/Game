package game.entity;

import engine.GameObject;
import engine.graphic.Displayable;
import engine.physics.Physic;
import engine.physics.collisionReaction.DisappearReaction;
import engine.physics.collisionReaction.IgnoreReaction;

public class EnemyShoot extends GameObject{

    private static int graphicShootSize = 25;
    private static int physicShootSize = 1; //TODO mettre 1
    private static int speed = 5;
    

    public EnemyShoot(int x, int y){
        super(
            new Physic     (x+graphicShootSize/2, y+graphicShootSize/2, physicShootSize, physicShootSize, new IgnoreReaction()),
            new Displayable(x, y, graphicShootSize, graphicShootSize, 10, "/player/shoot/playerShoot1.png", "/player/shoot/playerShoot2.png", "/player/shoot/playerShoot3.png", "/player/shoot/playerShoot4.png")
            );
        getComponent(Physic.class).setSpeed(speed);
        getComponent(Physic.class).setDirection(180);
    }




}
