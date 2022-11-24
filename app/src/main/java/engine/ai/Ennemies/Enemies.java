package engine.ai.Ennemies;

import engine.Component;
import engine.GameObject;

public abstract class Enemies extends GameObject {

    public Enemies(Component ... components){
        super(components);
    }

    public abstract void fire();
    public abstract int getPoint();
    
}
