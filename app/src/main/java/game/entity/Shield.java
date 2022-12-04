package game.entity;

import engine.GameObject;
import engine.graphic.Displayable;
import engine.physics.Physic;

public class Shield extends GameObject{
    private int lifePoint = 10;
    public Shield(int x, int y){
        super(
            new Displayable(x, y, 120, 120, "/walls/Wall1.png"),
            new Physic(x, y, 120, 60)
        );
    }

    public void takeDamage(){
        this.lifePoint--;
        if(this.lifePoint == 8) getComponent(Displayable.class).setAsset("/walls/Wall2.png");
        if(this.lifePoint == 5) getComponent(Displayable.class).setAsset("/walls/Wall3.png");
        if(this.lifePoint == 2) getComponent(Displayable.class).setAsset("/walls/Wall4.png");
    }

    public boolean isDestroyed(){
        return this.lifePoint <= 0;
    }
}
