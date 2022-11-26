package game.entity.enemies;

import engine.Component;
import engine.GameObject;
import engine.graphic.Displayable;

public abstract class Enemies extends GameObject {

    public Enemies(Component ... components){
        super(components);
    }

    public abstract void fire();
    public abstract int getPoint();
    public void died(){
        getComponent(Displayable.class).setVisibility(false);
    }
    
}
