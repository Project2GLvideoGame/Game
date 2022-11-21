package game.IA;

import game.GameObject;

public abstract class Enemies extends GameObject {

    public abstract void fire();
    public abstract int getPoint();
    public abstract String getImagePath();
    
}
