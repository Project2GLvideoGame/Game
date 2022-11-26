package game.entity.enemies;

import engine.Component;
import engine.physicsAAA.Physic;

public class Crab extends Enemies{
    
    public Crab(Component ... components){
        super(components);
        this.getComponent(Physic.class).setSpeed(1);
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
}