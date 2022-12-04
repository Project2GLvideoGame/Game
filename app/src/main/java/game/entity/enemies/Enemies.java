package game.entity.enemies;

import engine.Component;
import engine.GameObject;


    /**
     * Abstract class for defining an ennemy
     **/
public abstract class Enemies extends GameObject {

    /**
     * Create an enemies
     * @param components List of componnent for a gameObject Enemies
     */
    public Enemies(Component... components){
        super(components);
    }

    /**
     * Fire a missile
     */
    public abstract void fire();
    /**
     * @return Enemies reward point
     */
    public abstract int getPoint();
    
}
