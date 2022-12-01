package game.entity.enemies;

import engine.Component;
import engine.physics.Physic;

public class Crab extends Enemies{
    private final int id;
    public Crab(int id, Component... components){
        super(components);
        this.id = id;
        this.getComponent(Physic.class).setSpeed(5);
        this.getComponent(Physic.class).setDirection(270);
    }

    @Override
    public int getPoint() {
        return 5;
    }

    @Override
    public void fire() {
        // TODO Auto-generated method stub
    }

    public int getID(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Crab_ID " + id;
    }
    
}