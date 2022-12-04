package game.entity;

import engine.GameObject;
import engine.event.CollisionEvent;
import engine.event.DestroyEvent;
import engine.graphic.Displayable;
import engine.physics.Physic;
import engine.physics.collisionReaction.DisappearReaction;
import engine.sound.Soundable;
import engine.sound.Track;

/**
 * Player missile
 **/
public class PlayerShoot extends GameObject{

    private static int graphicShootSize = 25;
    private static int physicShootSize = 1;
    
    /**
     * Create a player missile
     * @param x Player X coordinate
     * @param y Player Y coordinate
     */
    public PlayerShoot(int x, int y){
        super(new Physic(x+graphicShootSize/2, y+graphicShootSize/2, physicShootSize, physicShootSize, new DisappearReaction()),
            new Displayable(x+graphicShootSize/2, y+graphicShootSize/2, graphicShootSize, graphicShootSize, 10, "/player/shoot/playerShoot1.png", "/player/shoot/playerShoot2.png", "/player/shoot/playerShoot3.png", "/player/shoot/playerShoot4.png")
            ,new Soundable(new Track("shoot", "/audio/shoot.wav")));
        
        getComponent(Physic.class).setSpeed(4);
    }

}
