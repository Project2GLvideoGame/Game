package game.entity;

import engine.GameObject;
import engine.graphic.Displayable;

public class PlayerLifePoint extends GameObject{
    public PlayerLifePoint(){
        super(new Displayable(10, 10, 100, 25, "/player/lifePoint/LifePoint3.png"));
    }

    public void graphicPlayerLife(int lifePoint){
        if(lifePoint > 3) lifePoint = 3;
        if(lifePoint < 0) lifePoint = 0;
        getComponent(Displayable.class).setAsset("/player/lifePoint/LifePoint"+lifePoint+".png");
    }
}
