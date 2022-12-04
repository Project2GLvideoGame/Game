package game.entity.enemies;

import engine.Component;
import engine.physics.Physic;



    /**
     *  A basic enemies
     **/
public class Crab extends Enemies{
    private final int id;

    /**
     * Create a basic enemies
     * @param id id of the enemies
     * @param components Componnets for an enemies
     */
    public Crab(int id, Component... components){
        super(components);
        this.id = id;
        this.getComponent(Physic.class).setSpeed(5);
        this.getComponent(Physic.class).setDirection(270);
    }

    @Override
    public int getPoint() {
        return 15;
    }

    @Override
    public void fire() {
    }

    /**
     * @return Id of an enemies
     */
    public int getID(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Crab_ID " + id;
    }
    
}